<script setup>

import CategorysContainer from "@/components/CategorysContainer.vue";
import TopChart from "@/components/TopChart.vue";

const fivePairs1 = [
    {
        key: "生活",
        value: 0.2
    },
    {
        key: "游戏",
        value: 0.6
    },
    {
        key: "娱乐",
        value: 0.15
    },
    {
        key: "科技",
        value: 0.3
    },
    {
        key: "音乐",
        value: 0.9
    },
]
const fivePairs2 = [
    {
        key: "生活",
        value: 0.4
    },
    {
        key: "游戏",
        value: 0.35
    },
    {
        key: "娱乐",
        value: 0.2
    },
    {
        key: "科技",
        value: 0.7
    },
    {
        key: "音乐",
        value: 0.5
    },
]
</script>

<template>
    <div class="category-body">
        <span style="width: 100%;height: 10%"/>
        <a-row class="chart-row" type="flex">
            <a-col :flex="10">
                <TopChart date="2023.10" :five-pairs="fivePairs1" title-img="" title="热门分区"/>
            </a-col>
            <a-col :flex="1"/>
            <a-col :flex="10">
                <TopChart date="2023.10" :five-pairs="fivePairs2" title-img="" title="最常浏览"/>
            </a-col>
        </a-row>
        <CategorysContainer class="all-category"/>
    </div>
</template>

<style lang="less" scoped>
.category-body {
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;

    .chart-row {
        width: 90%;
        height: 30%;
    }

    .all-category {
        width: 90%;
        height: 45%;
        margin: 1rem 0;
    }

    TopChart {
        width: 40%;
        height: 30%;
    }
}
</style>
