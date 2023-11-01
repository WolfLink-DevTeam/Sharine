<template>
    <div style="height: 100%;width: 100%;display: flex">
        <div style="margin-left: 4rem;height: 100%;width: 70%;justify-content: center;align-items: center;display: flex;flex-direction: column">
            <span id="videoTitle">
                <span id="title" class="text-shadow-focus">这里是视频标题这里是视频标题</span>
                <span id="subTitle">
                    <span class="titleTag box-shadow-focus" style="margin-right: 1rem;">播放 105.1万</span>
                    <span class="titleTag box-shadow-focus">2023-10-31 17:35:00</span>
                </span>
            </span>
            <div class="box-shadow-focus" style="width: 80rem;height: 0.15rem;background: #555D8B;margin-bottom: 1rem;border-radius: 1rem"/>
            <div class="box-shadow-focus" style="border-radius: 1rem" @wheel="onWheel">
                <video-player
                    :height="720"
                    :width="1280"
                    src="https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4"
                    poster=""
                    autoplay="true"
                    controls
                    :loop="true"
                    :volume="0.6"/>
                <span id="videoActions">
                <BasicButton class="actionBtn" width="8rem" height="3rem">
                    <img class="actionImg" src="@/assets/ui-icon/like-icon.png"><span class="actionText">14万</span>
                </BasicButton>
                <BasicButton class="actionBtn" width="8rem" height="3rem">
                    <img class="actionImg" src="@/assets/ui-icon/bookmark-icon.png"><span class="actionText">2万</span>
                </BasicButton>
                <BasicButton class="actionBtn" width="8rem" height="3rem">
                    <img class="actionImg" src="@/assets/ui-icon/share-icon.png"><span class="actionText">324</span>
                </BasicButton>
            </span>
            </div>
        </div>
        <div style="height: 100%;width: 30%;display: flex;justify-content: center;align-items: center;flex-direction: column">
            <BasicCard class="box-shadow-focus" width="80%" height="20%" style="margin-bottom: 1rem;margin-top: 5rem">
                <div class="card-body">
                    <div style="font-size: 1.4rem;font-family: SHS-Bold,serif">| 简介</div>
                    <div style="color: #e3e3e3;font-size: 1rem;font-family: SHS-Light,serif;margin-top: 0.7rem">这则视频讲述的是一个........abababababab的故bababababab的故事这则视频讲述的是一个........abababababab的故事这则视频讲述的是一个........abababababab的故事这则视频讲述的是一个........abababababab的故事这则视频讲述的是一个........abababababab的故事这则视频讲述的是一个........abababababab的故事</div>
                </div>
            </BasicCard>
            <BasicCard width="80%" height="55%" class="box-shadow-focus" style="display: flex;flex-direction: column">
                <div style="margin-left: 4rem;margin-bottom: 1rem;width: 100%;font-size: 1.4rem;font-family: SHS-Bold,serif">| 评论</div>
                <div class="card-body">
                    <CommentItem/>
                    <CommentItem/>
                    <CommentItem/>
                    <CommentItem/>
                </div>
            </BasicCard>
        </div>
    </div>
</template>

<script setup>
import { VideoPlayer } from '@videojs-player/vue'
import 'video.js/dist/video-js.css'
import BasicButton from "@/components/BasicButton.vue";
import BasicCard from "@/components/BasicCard.vue";
import CommentItem from "@/components/CommentItem.vue";
import Divider from "@/components/Divider.vue";
import {useRoute} from "vue-router";
const route = useRoute()
// TODO 捕获到视频ID
// console.log('videoId = '+route.query['videoId'])

let cooldown = false

// TODO 滚轮翻页
// 每0.6秒可触发一次鼠标滚轮事件
function onWheel(event) {
    if(cooldown) return
    cooldown = true
    setTimeout(()=>cooldown = false,600)
    if (event.deltaY < 0) {
        console.log('up');
    } else {
        console.log('down');
    }
}
</script>

<style scoped>
#title {
    font-size: 1.8rem;
    font-family: SHS-Medium,serif;
    width: 60rem;
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
    width: 80rem;
    height: 4rem;
    display: flex;
    align-items: center;
    flex-direction: row;
}
#videoTitle {
    display: flex;
    flex-direction: row;
    align-items: center;
    width: 80rem;
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
}
</style>