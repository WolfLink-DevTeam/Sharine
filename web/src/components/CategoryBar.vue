<script setup>
import { useSystemStore } from "@/store/modules/system.js"

// home页面内容和分类得切换
const systemStore = useSystemStore()
const changeSearchClick = () => {
    systemStore.homeContentToCategoryChange()
}
</script>

<template>
    <div class="container">
        <span style="font-size: 1.5rem;color: var(--primary-text-color);font-weight: lighter">当前</span>
        <span style="font-size: 1.5rem;color: var(--primary-text-color);font-weight: lighter">分区</span>
        <span style="width: 3.5rem;height: 0.15rem;background: #9589BF;margin-bottom: 1rem;margin-top: 1rem"/>
        <img src="../assets/category-icon/alltype-category.png" style="width: 4rem;height: 4rem;margin-bottom: 1rem" alt="">
        <span style=" writing-mode: vertical-rl;text-orientation: upright;font-size: 2.4rem;font-weight: bold;letter-spacing: 0.8rem;color: var(--primary-text-color)">综合</span>
        <div class="toggle-btn" @click="changeSearchClick()">
            <img class="img" style="margin-right: 0.3rem;" v-if="systemStore.homeContentToCategory" src="../assets/ui-icon/left-icon.png" alt="">
            <img class="img" style="margin-left: 0.3rem;" v-if="!systemStore.homeContentToCategory" src="../assets/ui-icon/right-icon.png" alt="">
        </div>
    </div>
</template>

<style scoped lang="less">
@import "@/common/css/global-var.less";
.toggle-btn {
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
    margin-top: 1.5rem;
    background: @component-background-color;
    border-radius: 2rem;
    width: 3rem;
    height: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
    .img {
        width: 2rem;
        height: 2rem;
    }
}
.toggle-btn:hover {
    background: white;
}
.toggle-btn:active {
    background: #3f4c9b;
}
.container {
    height: 27rem;
    width: 5rem;
    right: 0;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: var(--component-background-color);
    border-radius: 1.2rem 0 0 1.2rem;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
}
</style>