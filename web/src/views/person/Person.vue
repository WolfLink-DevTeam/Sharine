<script setup lang="ts">
import PersonVideoBar from "@/components/PersonVideoBar.vue";
import SimpleVideoCard from "@/components/SimpleVideoCard.vue";
import PersonCard from "@/components/PersonCard.vue";
import SearchBar from "@/components/SearchBar.vue";
import {ref, watch} from "vue";
import {Video} from "@/models/Video";
import {videoService} from "@/services/VideoService";
import {userService} from "@/services/UserService";
import {useSystemStore} from "@/store/system";
import {storeToRefs} from "pinia";
import {useRoute} from "vue-router";

const userId:number = Number(useRoute().query['userId']) || userService.getLocalUser()?.id || -1
const {isLogin} = storeToRefs(useSystemStore())

let favoriteVideos = new Array<Video>()
let bookmarkVideos = new Array<Video>()
let uploadVideos = new Array<Video>()
const videos = ref(new Array<Video>())

if(userId > 0) {
    userService.getUserUploadVideos(userId).then(pack => {
        const array = new Array<Video>()
        pack.data.forEach((videoVO: any) => {
            array.push(videoService.parseVideoVO(videoVO))
        })
        uploadVideos = array
        videos.value = uploadVideos
    })
    userService.getUserFavoriteVideos().then(pack => {
        const array = new Array<Video>()
        pack.data?.forEach((videoVO: any) => {
            array.push(videoService.parseVideoVO(videoVO))
        })
        favoriteVideos = array
    })
    userService.getUserBookmarkVideos().then(pack => {
        const array = new Array<Video>()
        pack.data?.forEach((videoVO: any) => {
            array.push(videoService.parseVideoVO(videoVO))
        })
        bookmarkVideos = array
    })
}

</script>

<template>
    <div class="person" v-if="isLogin">
        <div class="search">
            <SearchBar/>
        </div>
        <div style="width: 70%;">
            <PersonVideoBar class="video-bar"
                            :btn1-value="uploadVideos.length"
                            :btn2-value="(userId === userService.getLocalUser()?.id) ? favoriteVideos.length : 'lock'"
                            :btn3-value="(userId === userService.getLocalUser()?.id) ? bookmarkVideos.length : 'lock'" @btn1clicked="videos = uploadVideos" @btn2clicked="videos = favoriteVideos" @btn3clicked="videos = bookmarkVideos"/>
            <a-col class="video-list">
                <a-row :gutter="[24,12]">
                    <template v-for="video in videos" :key="video">
                        <a-col :span="6"><SimpleVideoCard :data="video"/></a-col>
                    </template>
                </a-row>
            </a-col>
        </div>
        <div style="width: 30%;justify-content: center;align-items: center;display: flex">
            <PersonCard class="person-card"/>
        </div>
    </div>
    <div v-else style="width: 100%;height: 100%;display: flex;justify-content: center;align-items: center;flex-direction: column">
        <img src="@/assets/not-login.png">
        <span style="font-size: 3rem;font-family: SHS-Bold,serif;letter-spacing: 0.4rem">请登录后查看哦~</span>
    </div>
</template>

<style lang="less" scoped>
.person {
    display: flex;
}
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
.video-bar {
    margin-top: 8rem;
    margin-left: 4rem;
    width: 40%;
}
.video-list {
    display: flex;
    flex-direction: column;
    height: 80vh; // adjust this value as needed
    margin-top: 1rem;
    margin-left: 4rem;
    overflow-y: scroll;
}
.video-list::-webkit-scrollbar {
    width: 0;
}
.person-card {
    height: 40%;
    width: 60%;
    min-height: 20rem;
    min-width: 20rem;
}
</style>
