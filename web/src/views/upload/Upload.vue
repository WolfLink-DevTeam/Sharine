<script setup lang="ts">
import ProgressBar from "@/components/ProgressBar.vue";
import ToggleButton from "@/components/ToggleButton.vue";
import CategoryDropdownButton from "@/components/CategoryDropdownButton.vue";
import BasicButton from "@/components/BasicButton.vue";
import "@/commons/global.css";
import {useSystemStore} from "@/store/system";
import {Ref, ref} from "vue";
import {qiniuService} from "@/services/QiniuService";
import * as qiniu from 'qiniu-js'
import {videoService} from "@/services/VideoService";
import {categoryService} from "@/services/CategoryService";
import {VideoType} from "@/models/VideoType";

const progress = ref("0")
const uploadStatus = ref(false)
const subscription: Ref<any> = ref(null)
const videoType: Ref<VideoType> = ref(VideoType.ORIGINAL)
function toggleType() {
    if(videoType.value === VideoType.ORIGINAL) videoType.value = VideoType.REPRINT
    else videoType.value = VideoType.ORIGINAL
}
async function selectImageFile(){
    new Promise((resolve, reject) => {
        // 创建一个文件输入元素
        const input = document.createElement('input');
        input.type = 'file';
        input.accept = 'image/*'; // 只接受图片文件

        // 当用户选择文件后，解析文件并返回
        input.onchange = () => {
            if (input.files && input.files.length > 0) {
                resolve(input.files[0])
            } else {
                resolve(null)
            }
        };
        // 当用户取消文件选择时，返回 null
        input.onclose = () => resolve(null);

        // 模拟点击文件输入元素
        input.click();
    }).then(data => {
        if(data === null) return
        console.log("处理文件")
        uploadCover(data as File)
    })
}


const coverUrl: Ref<string> = ref("")
const videoFrames: Ref<Array<string>> = ref(["","",""])

const categoryIndex = ref(0)

const title = ref("")
const content = ref("")
const videoUrl = ref("")
const key = ref("")
const hash = ref("")

const canUpload = ref(false)
async function getVideoCovers(videoUrl: string) {
    let video = document.createElement('video');
    video.onloadedmetadata = function() {
        const duration = video.duration
        // 封面
        coverUrl.value = qiniuService.getVideoFrameUrl(videoUrl,1)
        videoFrames.value[0] = qiniuService.getVideoFrameUrl(videoUrl,2)
        videoFrames.value[1] = qiniuService.getVideoFrameUrl(videoUrl,3)
        videoFrames.value[2] = qiniuService.getVideoFrameUrl(videoUrl,4)
    };
    video.onerror = function() {
        console.error("Can't get duration, video can't be loaded.");
    };
    video.src = videoUrl;

}
async function notifyServer() {
    if(!useSystemStore().isLogin) {
        alert("用户未登录！")
        return
    }
    if(title.value.length <= 4) {
        alert("标题过短！")
        return
    }
    if(title.value.length >= 26) {
        alert("标题过长！")
        return
    }
    if(content.value)
    if(!canUpload.value) {
        alert("请等待视频封面获取完毕")
        return
    }
    if(videoUrl.value.length < 0) {
        alert("视频URL获取失败，请重新尝试")
        return
    }
    // 通知后端校验
    videoService.uploadVideo(
        key.value,
        hash.value,
        categoryService.list.value[categoryIndex.value].id,
        title.value,
        content.value,
        videoUrl.value,
        coverUrl.value,
        videoType.value
    ).then(pack => {
        if(pack.code === 0) {
            alert("投稿成功")
            uploadStatus.value = false
            title.value = ""
            content.value = ""
            progress.value = "0"
        }
        else alert("投稿失败！请重新尝试")
    })

}
async function uploadCover(file: File) {

    qiniuService.getQiniuUploadInfo(file.name).then(pack => {
        qiniu.upload(file,pack.data.key,pack.data.token,{},{
            region: qiniu.region.z2
        }).subscribe({
            next(res: any){},
            error(err: any){},
            complete(res: any){
                const imgKey = res.key
                qiniuService.getDownloadUrl(imgKey).then(pack => {
                    coverUrl.value = pack.data
                    console.log(coverUrl.value)
                })
            }
        })
    })

}
async function upload(file:File) {

    qiniuService.getQiniuUploadInfo(file.name).then(pack => {
        key.value = pack.data.key
        const token = pack.data.token
        const observable = qiniu.upload(file,key.value,token,{},{
            region: qiniu.region.z2
        })
        subscription.value = observable.subscribe({
            next(res: any){
                progress.value = res.total.percent.toFixed(2)
            },
            error(err: any){
            },
            complete(res: any){
                hash.value = res.hash
                uploadStatus.value = true
                qiniuService.getDownloadUrl(key.value).then(pack => {
                    videoUrl.value = pack.data
                    // 获取视频封面
                    getVideoCovers(pack.data)
                    canUpload.value = true
                })
            }
        })
    })
}
// 定义 emits
const fileInput = ref<HTMLInputElement | null>(null);
const selectFile = () => {
    fileInput.value?.click();
};
const handleFile = (event: Event) => {
    const inputElement = event.target as HTMLInputElement;
    const rawFile = inputElement.files?.item(0);
    if (!rawFile) return;
    upload(rawFile);
};
function cancelUpload() {
    subscription.value?.unsubscribe()
    progress.value = "0"
    subscription.value = null
}
function onCategorySelected(index: number) {
    categoryIndex.value = index
}
</script>

<template>
    <div class="upload" v-if="useSystemStore().isLogin">
        <div class="upload-page">
            <input type="file" accept="video/mp4" ref="fileInput" @change="handleFile" style="display: none" />
            <ProgressBar class="progress-bar" :progress="progress"/>
            <a-row class="row-1" v-if="uploadStatus">
                <a-col :span="7" class="cover"><a-image style="border-radius: 1rem;height: 100%;width: 100%" :src="coverUrl" alt="" class="box-shadow-blur" @click="selectImageFile"/></a-col>
                <a-col :span="17" style="justify-content: center;align-items: center;display: flex;flex-direction: column;padding: 1rem">
                    <a-row style="width: 100%;height: 100%;align-items: center">
                        <span style="font-size: 1.4rem;font-family: SHS-Bold,serif;" class="text-shadow-focus">视频抽帧</span>
                        <span style=";font-size: 0.9rem;font-family: SHS-Light,serif;color: #7C7C7C;margin-left: 0.7rem" class="text-shadow-focus">下列封面来自视频内容抽帧，可选择其作为视频封面。</span>
                    </a-row>
                    <a-row justify="space-between" style="width: 100%;height: 100%">
                        <a-col :span="7" style="justify-content: center;align-items: end;display: flex"><img class="small-cover box-shadow-blur" :src="videoFrames[0]" alt="" @click="coverUrl = videoFrames[0]"/></a-col>
                        <a-col :span="7" style="justify-content: center;align-items: end;display: flex"><img class="small-cover box-shadow-blur" :src="videoFrames[1]" alt="" @click="coverUrl = videoFrames[1]"/></a-col>
                        <a-col :span="7" style="justify-content: center;align-items: end;display: flex"><img class="small-cover box-shadow-blur" :src="videoFrames[2]" alt="" @click="coverUrl = videoFrames[2]"/></a-col>
                    </a-row>
                </a-col>
            </a-row>
            <a-row class="row-2"><a-input class="box-shadow-blur" show-count:maxlength="28"
                                          placeholder="请输入视频标题" v-model:value="title"
            /></a-row>
            <a-row class="row-3"><a-textarea class="box-shadow-blur" show-count:maxlength="28" :rows="6"
                                             placeholder="请输入视频简介" v-model:value="content"
                                             :auto-size="{ minRows: 6, maxRows: 6 }"
            /></a-row>
            <a-row class="row-4"><ToggleButton :video-type="videoType" @type-toggle="toggleType" class="box-shadow-blur" style="height: 3.2rem;width: 12rem"/></a-row>
            <a-row class="row-5"><CategoryDropdownButton @category-select="onCategorySelected" :select-category-index="categoryIndex" class="box-shadow-blur" style="height: 3.2rem;width: 12rem"/></a-row>
            <a-row class="row-6">
                <BasicButton @click="selectFile" height="3.2rem" width="10rem" class="box-shadow-blur" v-show="subscription === null">选择文件</BasicButton>
                <BasicButton height="3.2rem" width="10rem" class="box-shadow-blur" v-show="subscription !== null && !uploadStatus" @click="cancelUpload">取消上传</BasicButton>
                <BasicButton v-show="uploadStatus" class="box-shadow-blur" width="10rem" height="3.2rem" style="font-family: SHS-Bold,serif" @click="notifyServer">立即投稿</BasicButton>
            </a-row>
        </div >
    </div>
    <div v-else style="width: 100%;height: 100%;display: flex;justify-content: center;align-items: center;flex-direction: column">
        <img src="@/assets/not-login.png">
        <span style="font-size: 3rem;font-family: SHS-Bold,serif;letter-spacing: 0.4rem">请登录后查看哦~</span>
    </div>
</template>

<style lang="less" scoped>
.upload{
    overflow-y: scroll;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .upload-page{
        width: 100%;
        height: 90%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        //overflow-y: scroll;
        .progress-bar {
            width: 60%;
            height: 15%;
        }
    }
}
.upload::-webkit-scrollbar {
    width: 0;
}
.cover {
    padding: 1rem;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    object-fit: cover;
}
.small-cover {
    height: 7rem;
    width: 11rem;
    border-radius: 0.7rem;
}
.row-1 {
    height: 12rem;
    width: 60%;
}
.row-2 {
    margin-top: 1rem;
    width: 60%;
    height: 3rem;
}
.row-3 {
    margin-top: 1rem;
    width: 60%;
    height: 9rem;
}
.row-4 {
    align-items: center;
    margin-top: 1rem;
    width: 60%;
    height: 4rem;
}
.row-5 {
    align-items: center;
    margin-top: 1rem;
    width: 60%;
    height: 4rem;
}
.row-6 {
    align-items: center;
    margin-top: 1rem;
    width: 60%;
    height: 4rem;
    justify-content: center;
}
</style>
