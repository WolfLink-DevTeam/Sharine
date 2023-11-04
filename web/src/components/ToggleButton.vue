<script setup lang="ts">

const props = defineProps({
    videoType: {
        type: Number,
        required: true
    }
})
defineEmits(['type-toggle'])

import "@/store/system.ts"
// 视频类型，1为原创，2为转载

</script>

<template>
<div style="height: 100%;width: 100%;border-radius: 0.5rem;display: flex;align-items: center;justify-content: center;background: #555D8B;padding: 1rem 1rem">
    <template v-if="videoType === 1">
        <div class="text-selected">原创</div>
        <div style="height: 1.75rem;width: 0.25rem;border-radius: 0.2rem;background: #9589BF;margin: 1rem 1rem"/>
        <div class="text-unselect" @click="$emit('type-toggle')">转载</div>
    </template>
    <template v-else>
        <div class="text-unselect" @click="$emit('type-toggle')">原创</div>
        <div style="height: 1.75rem;width: 0.25rem;border-radius: 0.2rem;background: #9589BF;margin: 1rem 1rem"/>
        <div class="text-selected">转载</div>
    </template>

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