<script setup lang="ts">
import SimpleVideoCard from "@/components/SimpleVideoCard.vue";
import {Video} from "@/models/Video.js";
import {ref} from "vue";

const props = defineProps({
    videos: {
        type: Array<Video>,
        required: true
    }
})
const emit = defineEmits(['refresh'])
const cooldown = ref(false)
function refresh() {
    if(cooldown.value)return
    cooldown.value = true
    setTimeout(()=>cooldown.value = false,1000)
    emit('refresh')
}
function scroll(e:any){
    const {scrollTop, clientHeight, scrollHeight} = e.target
    if ((scrollTop + clientHeight) / scrollHeight > 0.98 ){
        refresh()
    }
}
</script>

<template>
    <div class="home-content">
        <div class="content-body" @scroll="scroll">
            <div style="height: 8rem;width: 100%"/>
            <a-row :gutter="[24,18]" style="width: 100%">
                <template v-for="item in videos" :key="item">
                    <a-col :span="4">
                        <SimpleVideoCard :data="item"/>
                    </a-col>
                </template>
            </a-row>

        </div>
    </div>
</template>

<style lang="less" scoped>
.home-content {
    height: 100%;
    width: 90%;
    margin-left: 3%;
    .content-body {
        width: 100%;
        height: 100%;
        overflow-y: scroll;
        display: flex;
        flex-direction: row;
        flex-wrap: wrap;
        justify-content: center;
        align-items: flex-start;
    }

    .content-body::-webkit-scrollbar {
        width: 0;
    }
}
</style>
