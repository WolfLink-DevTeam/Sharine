<script setup lang="ts">

import {useRouter} from "vue-router";
import {Video} from "@/models/Video";
import {dateFormat} from "@/utilities/ResourceUtility";

const router = useRouter()


const props = defineProps({
    data: {
        type: Video,
        required: true
    }
})
function gotoVideoPage() {
    router.push('video?videoId='+props.data.id)
}


</script>

<template>
<div class="body" @click="gotoVideoPage">
    <img class="cover" src="@/assets/test-video-cover.png">
    <div class="info">
        <a-row style="height: 27%;border-color: #d1d1d1;border-bottom-style: solid;border-width: 0.1rem;">
            <a-col :span="8" class="container" style="margin-left: 5%;margin-top: 0.1rem">
                <img src="@/assets/ui-icon/like-icon.png" style="width: 1.2rem;height: 1.2rem" alt="">
                <span style="margin-left: 0.5rem;color: #fdfdff;font-size: 0.9rem;font-family: SHS-Bold,serif">{{data.favoriteCount}}</span>
            </a-col>
            <a-col :span="4"/>
            <a-col :span="8" class="container" style="margin-left: 5%;margin-top: 0.1rem">
                <img src="@/assets/ui-icon/bookmark-icon.png" style="width: 1.2rem;height: 1.2rem" alt="">
                <span style="margin-left: 0.5rem;color: #fdfdff;font-size: 0.9rem;font-family: SHS-Bold,serif">{{data.bookmarkCount}}</span>
            </a-col>
        </a-row>
        <a-row style="width: 90%;height: 45%;margin-left: auto;margin-right: auto">
            <span class="title">
                {{data.title}}
            </span>
        </a-row>
        <a-row style="margin-top: 1.5%">
            <a-col :span="2"/>
            <a-col :span="9" class="small-text">@{{data.author.nickname}}</a-col>
            <a-col :span="11" class="small-text" style="text-align: right">{{dateFormat(data.createTime)}}</a-col>
            <a-col :span="2"/>
        </a-row>
    </div>
</div>
</template>

<style scoped>
.body {
    height: 100%;
    width: 100%;
    border-radius: 0.7rem;
    box-shadow: 0 0 0.4rem rgba(0, 0, 0, 1);
}
.body:hover {
    opacity: 0.9;
    cursor: pointer;
}
.body:active {
    opacity: 1;
}
.cover {
    width: 100%;
    height: 65%;
    border-radius: 0.7rem 0.7rem 0 0;
}
.info {
    width: 100%;
    height: 35%;
    background: var(--component-background-color);
    border-radius: 0 0 0.7rem 0.7rem;
}
.container {
    display: flex;
    justify-content: center;
    align-items: center;
}
.text-box {
    display: flex;
    justify-content: center;
    align-items: center;
    border-radius: 0.2rem;
    background: rgba(0, 0, 0, 0.4);
    margin-left: auto;
    margin-right: auto;
    width: 70%;
    height: 80%;
    color: white;
    font-size: 0.7rem;
    font-weight: bold;
    letter-spacing: 0.03rem;
}
.title {
    //font-family: "SHS-Normal";
    display: flex;
    align-items: center;
    color: white;
    font-size: 0.85rem;
}
.small-text {
    color: #d3d3d3;
    font-size: 0.75rem;
}
</style>