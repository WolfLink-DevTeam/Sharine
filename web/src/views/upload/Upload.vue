<script setup>
import ProgressBar from "@/components/ProgressBar.vue";
import ToggleButton from "@/components/ToggleButton.vue";
import CategoryDropdownButton from "@/components/CategoryDropdownButton.vue";
import BasicButton from "@/components/BasicButton.vue";
import "@/common/css/global.css";
</script>

<template>
    <div class="upload">
        <div class="upload-page">
            <ProgressBar class="progress-bar" progress="32" speed="1.2MB/s" time-left="预计剩余15秒"/>
            <a-row class="row-1">
                <a-col :span="7" class="cover"><img style="border-radius: 1rem;height: 100%;width: 100%" src="@/assets/test-video-cover.png" alt="" class="box-shadow-blur"></a-col>
                <a-col :span="17" style="justify-content: center;align-items: center;display: flex;flex-direction: column;padding: 1rem">
                    <a-row style="width: 100%;height: 100%;align-items: center">
                        <span style="font-size: 1.4rem;font-family: SHS-Bold,serif;" class="text-shadow-focus">视频抽帧</span>
                        <span style=";font-size: 0.9rem;font-family: SHS-Light,serif;color: #7C7C7C;margin-left: 0.7rem" class="text-shadow-focus">下列封面来自视频内容抽帧，可选择其作为视频封面。</span>
                    </a-row>
                    <a-row justify="space-between" style="width: 100%;height: 100%">
                        <a-col :span="7" style="justify-content: center;align-items: end;display: flex"><img class="small-cover box-shadow-blur" src="@/assets/test-video-cover.png" alt=""></a-col>
                        <a-col :span="7" style="justify-content: center;align-items: end;display: flex"><img class="small-cover box-shadow-blur" src="@/assets/test-video-cover.png" alt=""></a-col>
                        <a-col :span="7" style="justify-content: center;align-items: end;display: flex"><img class="small-cover box-shadow-blur" src="@/assets/test-video-cover.png" alt=""></a-col>
                    </a-row>
                </a-col>
            </a-row>
            <a-row class="row-2"><a-input class="box-shadow-blur" show-count:maxlength="28"
                                          placeholder="请输入视频标题"
            /></a-row>
            <a-row class="row-3"><a-textarea class="box-shadow-blur" show-count:maxlength="28" :rows="6"
                                             placeholder="请输入视频简介"
                                             :auto-size="{ minRows: 6, maxRows: 6 }"
            /></a-row>
            <a-row class="row-4"><ToggleButton class="box-shadow-blur" style="height: 3.2rem;width: 12rem"/></a-row>
            <a-row class="row-5"><CategoryDropdownButton class="box-shadow-blur" style="height: 3.2rem;width: 12rem"/></a-row>
            <a-row class="row-6">
                <BasicButton class="box-shadow-blur" width="10rem" height="3.2rem" style="font-family: SHS-Normal,serif">
                    保存草稿
                </BasicButton>
                <div style="width: 4rem"/>
                <BasicButton class="box-shadow-blur" width="10rem" height="3.2rem" style="font-family: SHS-Bold,serif">
                    立即投稿
                </BasicButton>
            </a-row>
        </div>
    </div>
</template>

<style lang="less" scoped>
.upload{
    overflow-y: scroll;
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .upload-page{
        width: 100%;
        height: 90%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        //overflow-y: scroll;
        .progress-bar {
            width: 50%;
            height: 15%;
        }
    }
}
.upload::-webkit-scrollbar {
    width: 0;
}
.cover {
    padding: 1rem;
    height: 100%;
    display: flex;
    justify-content: center;
    align-items: center;
    object-fit: cover;
}
.small-cover {
    height: 7rem;
    width: 11rem;
    border-radius: 0.7rem;
}
.row-1 {
    height: 12rem;
    width: 50%;
}
.row-2 {
    margin-top: 1rem;
    width: 50%;
    height: 3rem;
}
.row-3 {
    margin-top: 1rem;
    width: 50%;
    height: 9rem;
}
.row-4 {
    align-items: center;
    margin-top: 1rem;
    width: 50%;
    height: 4rem;
}
.row-5 {
    align-items: center;
    margin-top: 1rem;
    width: 50%;
    height: 4rem;
}
.row-6 {
    align-items: center;
    margin-top: 1rem;
    width: 50%;
    height: 4rem;
    justify-content: center;
}
</style>