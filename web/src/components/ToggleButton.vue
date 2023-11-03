<script setup>
function toggleVideoType(value) {
    videoType.value = value
}

import "@/store/system.ts"
import {ref, watch} from "vue";
// 视频类型，0为原创，1为转载
const videoType = ref(-1)

const classes = ref(["text-unselect","text-unselect"])

watch(videoType,(newValue,oldValue)=>{
    if(newValue === 0) {
        classes.value[0] = "text-selected"
        classes.value[1] = "text-unselect"
    }
    else {
        classes.value[0] = "text-unselect"
        classes.value[1] = "text-selected"
    }
})
videoType.value = 0


</script>

<template>
<div style="height: 100%;width: 100%;border-radius: 0.5rem;display: flex;align-items: center;justify-content: center;background: #555D8B;padding: 1rem 1rem">
    <div :class="classes[0]" @click="toggleVideoType(0)">原创</div>
    <div style="height: 1.75rem;width: 0.25rem;border-radius: 0.2rem;background: #9589BF;margin: 1rem 1rem"/>
    <div :class="classes[1]" @click="toggleVideoType(1)">转载</div>
</div>
</template>

<style scoped lang="less">
@import "@/commons/global-var.less";
.text-selected {
    width: 45%;
    text-align: center;
    font-size: 1.2rem;
    color: @component-foreground-color;
    font-family: SHS-Bold,serif;
}
.text-unselect {
    text-align: center;
    width: 45%;
    font-size: 1.2rem;
    color: #a9a9a9;
    font-family: SHS-Medium,serif;
    cursor: pointer;
}
.text-unselect:hover {
    color: #ffffff;
}
</style>