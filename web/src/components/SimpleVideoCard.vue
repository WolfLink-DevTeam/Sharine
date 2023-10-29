<script setup>
</script>

<template>
<div class="body">
    <img class="cover" src="../assets/test-video-cover.png">
    <div class="info">
        <a-row style="height: 25%;margin-top: 1%;">
            <a-col :span="10" class="container" style="margin-left: 5%;border-color: #9589BF;border-bottom-style: solid;border-width: 0.15rem;margin-top: 0.1rem">
                <img src="../assets/ui-icon/like_icon.png" style="width: 1.2rem;height: 1.2rem">
                <span style="margin-left: 0.5rem;color: #e7d1ff;font-size: 0.8rem;font-family: SHS-Bold">20.61万</span>
            </a-col>
            <a-col :span="6" class="container text-box" style="margin-left: 20%;padding: 2%;margin-top: 2%">
                12:01
            </a-col>
        </a-row>
        <a-row style="width: 90%;height: 42%;margin-left: auto;margin-right: auto;margin-top: 2.5%">
            <span class="title">
                你好你好你好你好你好，你好你好你好你好你好你好你好你好
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
    height: 22rem;
    width: 14rem;
    border-radius: 0.7rem;
    box-shadow: 0 0 0.4rem rgba(0, 0, 0, 1);
    margin: 10px 15px;
}
.cover {
    width: 100%;
    height: 70%;
    border-radius: 0.7rem 0.7rem 0 0;
}
.info {
    width: 100%;
    height: 30%;
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