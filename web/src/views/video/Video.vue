<template>
    <div style="height: 100%;width: 100%;display: flex">
        <div style="margin-left: 4rem;height: 100%;width: 70%;justify-content: center;align-items: center;display: flex;flex-direction: column">
            <span id="videoTitle">
                <span id="title" class="text-shadow-focus">{{video.title}}</span>
                <span id="subTitle">
<!--                    TODO-->
                    <span class="titleTag box-shadow-focus" style="margin-right: 1rem;justify-content: center;display: flex"><img src="@/assets/ui-icon/view-count.png" style="height: 1.5rem;width: 1.5rem;margin-right: 0.5rem">{{video.viewCount}}</span>
                    <span class="titleTag box-shadow-focus" style="justify-content: center;display: flex"><img src="@/assets/ui-icon/date-icon.png" style="height: 1.5rem;width: 1.5rem;margin-right: 0.5rem">{{ dateFormat(video.createTime) }}</span>
                </span>
            </span>
            <div class="box-shadow-focus" style="width: 60vw;height: 0.15rem;background: #555D8B;margin-bottom: 1rem;border-radius: 1rem"/>
            <div class="box-shadow-focus" style="border-radius: 1rem;width: 60vw" @wheel="onWheel">
                <video-player
                    style="height: 60vh;width: 100%"
                    :src="video.url"
                    poster=""
                    autoplay="true"
                    controls
                    :loop="true"
                    :volume="0.6"/>
                <span id="videoActions">
                <BasicButton class="actionBtn" width="8rem" height="3rem" @click="btnFavorite">
                    <img class="actionImg" src="@/assets/ui-icon/like-icon.png" alt="" v-if="!hasFavorite">
                    <img class="actionImg" src="@/assets/ui-icon/filled-like-icon.png" alt="" v-else>
                    <span class="actionText">{{video.favoriteCount}}</span>
                </BasicButton>
                <BasicButton class="actionBtn" width="8rem" height="3rem" @click="btnBookmark">
                    <img class="actionImg" src="@/assets/ui-icon/bookmark-icon.png" alt="" v-if="!hasBookmark">
                    <img class="actionImg" src="@/assets/ui-icon/filled-bookmark-icon.png" alt="" v-else>
                    <span class="actionText">{{video.bookmarkCount}}</span>
                </BasicButton>
<!--                <BasicButton class="actionBtn" width="8rem" height="3rem">-->
<!--                    <img class="actionImg" src="@/assets/ui-icon/share-icon.png" alt=""><span class="actionText">转发</span>-->
<!--                </BasicButton>-->
            </span>
            </div>
        </div>
        <div style="height: 100%;width: 30%;display: flex;justify-content: center;align-items: center;flex-direction: column">
            <BasicCard class="box-shadow-focus" width="80%" height="20%" style="margin-bottom: 1rem;margin-top: 5rem;flex-direction: column">
                <div style="margin-left: 8%;margin-bottom: 1rem;width: 100%;font-size: 1.4rem;font-family: SHS-Bold,serif;margin-top: 1rem">| 简介</div>
                <div class="card-body" style="margin-bottom: 1.8rem">
                    <div style="color: #e3e3e3;font-size: 1rem;font-family: SHS-Light,serif;">{{ video.content }}</div>
                </div>
            </BasicCard>
            <BasicCard width="80%" height="55%" class="box-shadow-focus" style="display: flex;flex-direction: column;">
                <div style="margin-bottom: 1rem;width: 100%;display: flex;flex-direction: row">
                    <span style="width: 4rem;margin-left: 3%;font-size: 1.4rem;font-family: SHS-Bold,serif">| 评论</span>
                    <a-input-group compact style="width: 80%;margin-left: 3%" v-show="useSystemStore().isLogin">
                        <a-input v-model:value="userCommentText" style="width: 70%" />
                        <a-button type="primary" style="width: 25%;" @click="addComment">发布</a-button>
                    </a-input-group>
                </div>
                <div class="card-body">
                    <template v-for="comment in comments" :key="comment">
                        <CommentItem :data="comment"/>
                    </template>
                </div>
            </BasicCard>
        </div>
    </div>
</template>

<script setup lang="ts">
import { VideoPlayer } from '@videojs-player/vue'
import 'video.js/dist/video-js.css'
import BasicButton from "@/components/BasicButton.vue";
import BasicCard from "@/components/BasicCard.vue";
import CommentItem from "@/components/CommentItem.vue";
import Divider from "@/components/Divider.vue";
import {useRoute} from "vue-router";
import {Video} from "@/models/Video.js";
import {videoService} from "@/services/VideoService.js";
import {ref} from "vue";
import {dateFormat} from "../../utilities/ResourceUtility";
import {VideoComment} from "@/models/VideoComment";
import {userService} from "@/services/UserService";
import {useSystemStore} from "@/store/system";
const route = useRoute()
// TODO 捕获到视频ID
// console.log('videoId = '+route.query['videoId'])
const videoId = Number(route.query['videoId'])
const video = ref<Video>(new Video())
videoService.getVideo(videoId).then(it => {
    video.value = videoService.parseVideoVO(it.data)
    videoService.viewVideo(video.value.id)
})

const comments = ref(new Array<VideoComment>)

function updateVideoComments() {
    videoService.getVideoComments(videoId).then(pack => {
        const array: Array<any> = pack.data
        let tempComments = new Array<VideoComment>()
        for (let index in array) {
            const commentVO = array[index]
            tempComments.push(videoService.parseCommentVO(commentVO))
        }
        tempComments.reverse()
        comments.value = tempComments
    })

}
updateVideoComments()


const userCommentText = ref("")

let cooldown = false

function addComment() {
    if(!useSystemStore().isLogin) return
    const comment = new VideoComment()
    comment.content = userCommentText.value
    comment.author = userService.getLocalUser()!
    comment.videoId = videoId
    comment.replyId = -1
    videoService.addComment(comment).then(pack => {
        if(pack.code === 0) {
            userCommentText.value = ""
            updateVideoComments()
        }
    })
}

// TODO 滚轮翻页
// 每0.6秒可触发一次鼠标滚轮事件
function onWheel(event: any) {
    if(cooldown) return
    cooldown = true
    setTimeout(()=>cooldown = false,600)
    if (event.deltaY < 0) {
        console.log('up');
    } else {
        console.log('down');
    }
}
const hasFavorite = ref(false)
const hasBookmark = ref(false)
userService.hasFavorite(videoId).then(pack => hasFavorite.value = pack.data)
userService.hasBookmark(videoId).then(pack => hasBookmark.value = pack.data)
function btnFavorite() {
    let promise = null
    if(hasFavorite.value) {
        promise = userService.undoFavorite(videoId)
    } else promise = userService.favorite(videoId)
    promise.then(pack => {
        if(pack.code === 0) {
            hasFavorite.value = !hasFavorite.value
            if(hasFavorite.value) video.value.favoriteCount++
            else video.value.favoriteCount--
        }
    })
}
function btnBookmark() {
    let promise = null
    if(hasBookmark.value) {
        promise = userService.undoBookmark(videoId)
    } else promise = userService.bookmark(videoId)
    promise.then(pack => {
        if(pack.code === 0) {
            hasBookmark.value = !hasBookmark.value
            if(hasBookmark.value) video.value.bookmarkCount++
            else video.value.bookmarkCount--
        }
    })
}
</script>

<style scoped>
#title {
    font-size: 1.8rem;
    font-family: SHS-Medium,serif;
    width: 60vw;
}
#subTitle {
    display: flex;
    justify-content: end;
    width: 100%;
    font-size: 1.2rem;
    color: #454545;
}
.titleTag {
    color: white;
    border-radius: 0.5rem;
    background: #555D8B;
    padding: 0.5rem 1rem;
}
#videoActions {
    background: #555D8B;
    border-radius: 0 0 1rem 1rem;
    width: 60vw;
    height: 4rem;
    display: flex;
    align-items: center;
    flex-direction: row;
}
#videoTitle {
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 60vw;
    height: 4rem;
//background: #555D8B;
    border-radius: 1rem 1rem 0 0;
}
.actionBtn {
    margin-left: 1rem;
    margin-right: 1rem;
}
.actionImg {
    height: 2rem;
    width: 2rem;
}
.actionText {
    margin-left: 0.5rem;
    font-size: 1.3rem;
}
.card-body {
    width: 90%;
    height: 85%;
    overflow-y: scroll;
}
.card-body:hover {
    border-radius: 0.5rem;
    background: rgba(255, 255, 255, 0.03);
}
.card-body::-webkit-scrollbar {
    width: 0
}
</style>