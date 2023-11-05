<script setup lang="ts">
import SearchBar from "@/components/SearchBar.vue";
import DetailVideoCard from "@/components/DetailVideoCard.vue";
import {Video} from "@/models/Video";
import {videoService} from "@/services/VideoService";
import {userService} from "@/services/UserService";
import {useSystemStore} from "@/store/system";
import {ref} from "vue";
const videos = ref(new Array<Video>())

if(useSystemStore().isLogin) {
    userService.getSubscribeVideos().then(pack => {
        pack.data?.forEach((it:any)=>{
            videos.value.push(videoService.parseVideoVO(it))
        })
    })
}
</script>

<template>
    <div class="favorite" v-if="useSystemStore().isLogin">
        <div class="search">
            <SearchBar></SearchBar>
        </div>
        <div class="favorite-page">
            <div class="favorite-list">
                <template v-for="(video,index) in videos" :key="video">
                    <DetailVideoCard :video="video" :direction="index%2===0 ? 'left' : 'right'"/>
                </template>
            </div>
        </div>
    </div>
    <div v-else style="width: 100%;height: 100%;display: flex;justify-content: center;align-items: center;flex-direction: column">
        <img src="@/assets/not-login.png">
        <span style="font-size: 3rem;font-family: SHS-Bold,serif;letter-spacing: 0.4rem">请登录后查看哦~</span>
    </div>
</template>

<style lang="less" scoped>
.favorite{
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
    .favorite-page{
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        overflow-y: scroll;
        .favorite-list{
            width: 70%;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 7rem;
        }
    }
    .favorite-page::-webkit-scrollbar {
        width: 0;
    }
}
</style>
