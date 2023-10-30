<script setup>
const props = defineProps({
    direction: {
        type: String,
        required: false,
        default: "right"
    }
})
</script>

<template>
    <a-row class="card-body">
        <a-row v-if="direction === 'right'" class="card-info" style="border-radius: 1rem 0 0 1rem">
            <a-col style="width: 22%;height: 80%">
                <a-row style="align-items: center;justify-content: center"><img src="@/assets/logo.png" class="info-avatar"></a-row>
                <div style="height: 3rem"/>
                <a-row><a-col :flex="7" class="info-text-title">粉丝</a-col><a-col :flex="3"/><a-col :flex="10" class="info-text-value">9.7万</a-col><a-col :flex="3"/></a-row>
                <a-row><a-col :flex="7" class="info-text-title">获赞</a-col><a-col :flex="3"/><a-col :flex="10" class="info-text-value">21万</a-col><a-col :flex="3"/></a-row>
                <a-row><a-col :flex="7" class="info-text-title">播放</a-col><a-col :flex="3"/><a-col :flex="10" class="info-text-value">114万</a-col><a-col :flex="3"/></a-row>
            </a-col>
            <a-col style="width: 0.2rem;height: 85%;background: #D8D8D8;margin-left: 1rem;margin-right: 1rem"/>
            <a-col style="width: 27rem;height: 80%">
                <a-row style="display: flex;justify-content: start;align-items: center">
                    <a-col style="font-family: SHS-Medium,serif;font-size: 1.25rem;margin-bottom: 0.1rem">一眼丁真</a-col>
                    <a-col style="width: 0.4rem;height: 0.4rem;border-radius: 1rem;background: #4ED93B;margin: 1rem 1rem"/>
                    <a-col style="color: #737373;font-size: 0.9rem">15小时前</a-col>
                </a-row>
                <a-row style="font-size: 0.9rem;width: 100%;justify-content: start">
                    <span style="color: #797979;background: rgba(150,150,150,0.4);border-radius: 0.6rem;padding: 0.3rem 1rem;">#科幻 #视觉 #测试 #标签 #微电影</span>
                </a-row>
                <a-row style="font-size: 1.2rem;font-family: SHS-Bold,serif;margin-top: 1rem;margin-bottom: 1rem">人类在宇宙深处发现一艘巨型飞船，里面竟然隐藏着一个小世界！</a-row>
                <a-row style="color: #6B6B6B;font-size: 0.9rem">这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介。</a-row>
            </a-col>
        </a-row>
        <a-col class="cover">
            <div class="cover-span">
                <img src="../assets/ui-icon/like-icon.png" style="width: 2.2rem;height: 2.2rem;margin-left: 1rem" alt="">
                <span style="font-family: SHS-Bold,serif;color: white;font-size: 1.2rem;margin-left: 0.6rem">20.6万</span>
                <span style="position: absolute;right: 1.5rem;color: white;font-size: 1.2rem;font-family: SHS-Bold,serif">04:05</span>
            </div>
        </a-col>
        <a-row v-if="direction === 'left'" class="card-info" style="border-radius: 0 1rem 1rem 0">
            <a-col style="width: 27rem;height: 80%">
                <a-row style="display: flex;justify-content: end;align-items: center">
                    <a-col style="color: #737373;font-size: 0.9rem">15小时前</a-col>
                    <a-col style="width: 0.4rem;height: 0.4rem;border-radius: 1rem;background: #4ED93B;margin: 1rem 1rem"/>
                    <a-col style="font-family: SHS-Medium,serif;font-size: 1.25rem;margin-bottom: 0.1rem">一眼丁真</a-col>
                </a-row>
                <a-row style="font-size: 0.9rem;width: 100%;justify-content: end">
                    <span style="color: #797979;background: rgba(150,150,150,0.4);border-radius: 0.6rem;padding: 0.3rem 1rem;">#科幻 #视觉 #测试 #标签 #微电影</span>
                </a-row>
                <a-row style="font-size: 1.2rem;font-family: SHS-Bold,serif;margin-top: 1rem;margin-bottom: 1rem">人类在宇宙深处发现一艘巨型飞船，里面竟然隐藏着一个小世界！</a-row>
                <a-row style="color: #6B6B6B;font-size: 0.9rem">这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介这是视频简介。</a-row>
            </a-col>
            <a-col style="width: 0.2rem;height: 85%;background: #D8D8D8;margin-left: 1rem;margin-right: 1rem"/>
            <a-col style="width: 22%;height: 80%">
                <a-row style="align-items: center;justify-content: center"><img src="@/assets/logo.png" class="info-avatar"></a-row>
                <div style="height: 3rem"/>
                <a-row><a-col :flex="7" class="info-text-title">粉丝</a-col><a-col :flex="3"/><a-col :flex="10" class="info-text-value">9.7万</a-col><a-col :flex="3"/></a-row>
                <a-row><a-col :flex="7" class="info-text-title">获赞</a-col><a-col :flex="3"/><a-col :flex="10" class="info-text-value">21万</a-col><a-col :flex="3"/></a-row>
                <a-row><a-col :flex="7" class="info-text-title">播放</a-col><a-col :flex="3"/><a-col :flex="10" class="info-text-value">114万</a-col><a-col :flex="3"/></a-row>
            </a-col>
        </a-row>
    </a-row>

<!--    <div class="body">-->
<!--        <img class="cover" src="../assets/test-video-cover.png">-->
<!--        <div class="info">-->
<!--            <a-row style="height: 25%;">-->
<!--                <a-col :span="8" class="container" style="margin-left: 5%;border-color: #a786d7;border-bottom-style: solid;border-width: 0.15rem;margin-top: 0.1rem">-->
<!--                    <img src="../assets/ui-icon/like-icon.png" style="width: 1.2rem;height: 1.2rem">-->
<!--                    <span style="margin-left: 0.5rem;color: #fdfdff;font-size: 0.8rem;font-family: SHS-Bold">20.6万</span>-->
<!--                </a-col>-->
<!--                <a-col :span="3"/>-->
<!--                <a-col :span="6" class="container text-box" style="margin-left: 20%;padding: 2%;margin-top: 2%">-->
<!--                    12:01-->
<!--                </a-col>-->
<!--            </a-row>-->
<!--            <a-row style="width: 90%;height: 42%;margin-left: auto;margin-right: auto;margin-top: 2.5%">-->
<!--            <span class="title">-->
<!--                你好你好你好你好你好，你好你好你好你好你好你好你好你好-->
<!--            </span>-->
<!--            </a-row>-->
<!--            <a-row style="margin-top: 1.5%">-->
<!--                <a-col :span="2"/>-->
<!--                <a-col :span="12" class="small-text">@一眼丁真</a-col>-->
<!--                <a-col :span="2"/>-->
<!--                <a-col :span="6" class="small-text" style="text-align: right">1小时前</a-col>-->
<!--            </a-row>-->
<!--        </div>-->
<!--    </div>-->
</template>

<style scoped>
.card-body {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 26rem;
}
.cover {
    background-image: url("@/assets/test-video-cover.png");
    width: 15rem;
    height: 24rem;
    border-radius: 1.2rem;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
}
.cover-span {
    display: flex;
    align-items: center;
    position: absolute;
    bottom: 5%;
    height: 10%;
    width: 100%;
    background: rgba(0, 0, 0, 0.75);
}
.card-info {
    width: 40rem;
    height: 20rem;
    background: white;
    border-radius: 0 0 0.7rem 0.7rem;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
    display: flex;
    align-items: center;
    justify-content: center;
}
.info-avatar {
    border-radius: 3rem;
    width: 6rem;
    height: 6rem
}
.info-text-title {
    text-align: right;
    color: #3D3D3D;
    font-family: SHS-ExtraLight,serif;
    font-size: 1.2rem;
}
.info-text-value {
    color: #3D3D3D;
    font-family: SHS-Medium,serif;
    width: 20%;
    font-size: 1.2rem;
}
</style>