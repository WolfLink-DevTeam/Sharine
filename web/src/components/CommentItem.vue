<script setup lang="ts">
import {VideoComment} from "@/models/VideoComment";

const props = defineProps({
    data: {
        type: VideoComment,
        require: true
    }
})
</script>

<template>
<div>
    <div id="first-line">
        <img :src="data?.author.avatar" id="avatar">
        <div id="name">{{data.author.nickname}}</div>
    </div>
    <div id="content">
        {{data.content}}
    </div>
</div>
</template>

<style scoped lang="less">
#first-line {
    margin-top: 0.7rem;
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-bottom: 0.5rem;
}
#avatar {
    padding: 0.25rem;
    width: 3rem;
    height: 3rem;
    border-radius: 2rem;
    background: white;
}
#name {
    width: 100%;
    margin-left: 1rem;
    font-size: 1rem;
    font-family: SHS-Bold,serif;
}
#content {
    margin-left: 3rem;
    padding: 0.8rem;
    border-radius: 0 1rem 1rem 1rem;
    background: #766C9B;
    font-size: 0.8rem;
    font-family: SHS-Light,serif;
}
</style>