<script setup>
</script>

<template>
<div class="body">
    <img class="cover" src="@/assets/test_video_cover.png">
    <div class="info">
        <a-row style="height: 30%;margin-top: 1%;">
            <a-col :span="10" class="container" style="margin-left: 5%;border-color: #9589BF;border-bottom-style: solid;border-width: 0.1rem">
                <img src="@/assets/like_icon.png" style="width: 1rem;height: 1rem">
                <span style="margin-left: 0.2rem;color: white;font-weight: bold;font-size: 0.7rem;">20.61万</span>
            </a-col>
            <a-col :span="6" class="container text-box" style="margin-left: 20%;padding: 2%;margin-top: 2%">
                12:01
            </a-col>
        </a-row>
        <a-row style="width: 85%;height: 35%;margin-left: auto;margin-right: auto;margin-top: 3%">
            <span class="title">
                你好你好你好你好你好你好你好你好你好你好你好你好
            </span>
        </a-row>
        <a-row style="margin-top: 1.5%">
            <a-col :span="2"/>
            <a-col :span="12" class="small-text">@一眼丁真</a-col>
            <a-col :span="2"/>
            <a-col :span="6" class="small-text" style="text-align: right">1小时前</a-col>
        </a-row>
    </div>
</div>
</template>

<style scoped>
.body {
    border-radius: 0.7rem;
    box-shadow: 0 0 0.4rem rgba(0, 0, 0, 1);
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
    color: white;
    font-weight: lighter;
    font-size: 0.7rem;
}
.small-text {
    color: #c3c3c3;
    font-weight: lighter;
    font-size: 0.6rem;
}
</style>