<script setup lang="ts">
import SearchBar from "@/components/SearchBar.vue";
import "@/components/CategoryBar.vue";
import HomeContent from '@/views/home/cpns/HomeContent.vue'
import HomeCategory from '@/views/home/cpns/HomeCategory.vue'
import {Ref, ref} from "vue";
import { useSystemStore } from "@/store/system"
import CategoryBar from "@/components/CategoryBar.vue";
import {VideoVO} from "@/models/VideoVO";
import {Category} from "@/models/Category";
import {remoteVideoService} from "@/services/remote/RemoteVideoService";
import {nativeVideoService} from "@/services/native/NativeVideoService";
import {nativeCategoryService} from "@/services/native/NativeCategoryService";

// home页面内容和分类得切换
const systemStore = useSystemStore()
const changeSearchClick = () => {
    systemStore.homeContentToCategoryChange()
}
function onSearch(text: string) {
    if(text.length === 0) {
        displayVideos.value = categoryVideos.value
    } else {
        displayVideos.value = categoryVideos.value.filter(video => video.title.includes(text) || video.content.includes(text))
    }
}
// 从服务器获取的全部视频
const videos = nativeVideoService.loadedVideos
// 分区视频列表
const categoryVideos = ref(new Array<VideoVO>())
// 实际展示的视频
const displayVideos = ref(new Array<VideoVO>())

const page = ref(0)

const isRetrying = ref(false)

function getVideosByPage() {
    remoteVideoService.getVideo(undefined,undefined,page.value++,30).then(tempVideos => {
        try {
            tempVideos.forEach((video: VideoVO) => videos.value.push(video))
            isRetrying.value = false
        } catch (_) {
            isRetrying.value = true
            setTimeout(()=>getVideosByPage(),300)
        }

    })
}
getVideosByPage()
// 初始化分区视频列表
categoryVideos.value = videos.value
// 初始化实际展示视频列表
displayVideos.value = videos.value


const selectCategory: Ref<Category> = ref(new Category())
setTimeout(()=>{
    selectCategory.value = nativeCategoryService.findCategoryById(0) ?? new Category()
},100)
setTimeout(()=>{
    selectCategory.value = nativeCategoryService.findCategoryById(0) ?? new Category()
},1000)

function changeCategory(category: Category) {
    selectCategory.value = category
    // 综合分区
    if(category.id === 1) categoryVideos.value = videos.value
    else categoryVideos.value = videos.value.filter((video: VideoVO) => video.category.id === category.id)
    displayVideos.value = categoryVideos.value
    systemStore.homeContentToCategoryChange()
}
</script>

<template>
    <div class="home">
        <div class="search">
            <SearchBar @onSearch="onSearch"/>
        </div>
        <div class="home-body" v-if="!isRetrying">
            <HomeContent :videos="displayVideos" @refresh="getVideosByPage"/>
<!--            <HomeCategory v-show="!systemStore.homeContentToCategory"></HomeCategory>-->
            <div class="category-bar" style="z-index: 10">
                <CategoryBar style="height: 27rem;width: 5rem;right: 0;" :category="selectCategory"/>
            </div>
        </div>
        <div v-else style="width: 100%;height: 100%;display: flex;flex-direction: column;justify-content: center;align-items: center">
            <img src="@/assets/wait-img.png">
            <span style="font-size: 2.5rem;font-family: SHS-Bold,serif">正在拉取数据 请耐心等待哦~</span>
        </div>
    </div>
    <a-drawer :open="!systemStore.homeContentToCategory" :closable="false" width="65rem" @close="systemStore.homeContentToCategoryChange()">
        <HomeCategory @selectCategory="changeCategory"/>
    </a-drawer>
</template>

<style lang="less" scoped>
.home{
    min-width: 80rem;
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    .search{
        z-index: 5;
        height: 10%;
        min-height: 100px;
        width: 100%;
        position: fixed;
        top: 0;
        left: 0;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .home-body{
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: row;
        justify-content: center;
        .category-bar{
            flex: 1;
            height: 100%;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: flex-end;
        }
    }
}
</style>
