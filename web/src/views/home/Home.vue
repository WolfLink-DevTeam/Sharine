<script setup lang="ts">
import SearchBar from "@/components/SearchBar.vue";
import "@/components/CategoryBar.vue";
import HomeContent from '@/views/home/cpns/HomeContent.vue'
import HomeCategory from '@/views/home/cpns/HomeCategory.vue'
import {Ref, ref} from "vue";
import { useSystemStore } from "@/store/system"
import CategoryBar from "@/components/CategoryBar.vue";
import {Video} from "@/models/Video";
import {videoService} from "@/services/VideoService";
import {categoryService} from "@/services/CategoryService";
import {Category} from "@/models/Category";

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
const videos = videoService.videos
// 分区视频列表
const categoryVideos = ref(new Array<Video>())
// 实际展示的视频
const displayVideos = ref(new Array<Video>())

const page = ref(0)
function getVideosByPage() {
    videoService.getVideos(page.value++,30).then(pack => {
        pack.data.forEach((it: any) => {
            const video = videoService.parseVideoVO(it)
            videos.value.push(video)
        })

    })
}
getVideosByPage()
// 初始化分区视频列表
categoryVideos.value = videos.value
// 初始化实际展示视频列表
displayVideos.value = videos.value


const selectCategory: Ref<Category> = ref(new Category())
setTimeout(()=>{
    selectCategory.value = categoryService.getCategory(0)
},100)
setTimeout(()=>{
    selectCategory.value = categoryService.getCategory(0)
},1000)

function changeCategory(category: Category) {
    selectCategory.value = category
    // 综合分区
    if(category.id === 1) categoryVideos.value = videos.value
    else categoryVideos.value = videos.value.filter(video => video.category.id === category.id)
    displayVideos.value = categoryVideos.value
    systemStore.homeContentToCategoryChange()
}
</script>

<template>
    <div class="home">
        <div class="search">
            <SearchBar @onSearch="onSearch"/>
        </div>
        <div class="home-body">
            <HomeContent :videos="displayVideos" @refresh="getVideosByPage"/>
<!--            <HomeCategory v-show="!systemStore.homeContentToCategory"></HomeCategory>-->
            <div class="category-bar" style="z-index: 10">
                <CategoryBar style="height: 27rem;width: 5rem;right: 0;" :category="selectCategory"/>
            </div>
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
