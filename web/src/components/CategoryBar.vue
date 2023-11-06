<script setup lang="ts">
import {useSystemStore} from "@/store/system"
import {Category} from "@/models/Category";
import {getImageUrl} from "@/utilities/ResourceUtility";

// home页面内容和分类得切换
const systemStore = useSystemStore()
const changeSearchClick = () => {
    systemStore.homeContentToCategoryChange()
}

const props = defineProps({
    category: {
        type: Category,
        required: true
    }
})
</script>

<template>
    <div class="container">
        <span style="font-size: 1.5rem;color: var(--primary-text-color);font-weight: lighter">当前</span>
        <span style="font-size: 1.5rem;color: var(--primary-text-color);font-weight: lighter">分区</span>
        <span style="width: 3.5rem;height: 0.15rem;background: #9589BF;margin-bottom: 1rem;margin-top: 1rem"/>
        <img alt="" :src="getImageUrl(category.url)"
             style="width: 4rem;height: 4rem;margin-bottom: 1rem">
        <span
            style=" writing-mode: vertical-rl;text-orientation: upright;font-size: 2.4rem;font-weight: bold;letter-spacing: 0.8rem;color: var(--primary-text-color)">{{category.title}}</span>
        <div class="toggle-btn" @click="changeSearchClick()">
            <img v-if="systemStore.homeContentToCategory" alt="" class="img"
                 src="../../public/ui-icon/left-icon.png" style="margin-right: 0.3rem;">
            <img v-if="!systemStore.homeContentToCategory" alt="" class="img"
                 src="../../public/ui-icon/right-icon.png" style="margin-left: 0.3rem;">
        </div>

    </div>
</template>

<style lang="less" scoped>
@import "@/commons/global-var.less";

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