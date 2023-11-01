<script setup>
import SimpleVideoCard from "@/components/SimpleVideoCard.vue";
import {ref} from "vue";

const num = ref([
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
    {count: 1},
])
</script>

<template>
    <div class="home-content">
        <div class="content-body">
            <div style="height: 8rem;width: 100%"/>
            <a-row :gutter="[24,12]">
                <a-col v-for="item in num" :key="item" :span="4">
                    <SimpleVideoCard/>
                </a-col>
            </a-row>

        </div>
    </div>
</template>

<style lang="less" scoped>
.home-content {
    height: 100%;
    width: 93%;

    .content-body {
        width: 100%;
        height: 100%;
        overflow-y: scroll;
        padding: 30px 20px;
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
