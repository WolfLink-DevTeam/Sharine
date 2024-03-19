<script setup lang="ts">

import CategorysContainer from "@/components/CategorysContainer.vue";
import TopChart from "@/components/TopChart.vue";
import {Category} from "@/models/Category";
import {simpleDateFormat} from "@/utilities/ResourceUtility";
import {nativeCategoryService} from "@/services/native/NativeCategoryService";
import {nativeVideoService} from "@/services/native/NativeVideoService";

defineEmits(['selectCategory'])

const categoryFrequencies1 =
nativeCategoryService.topFiveHotCategories(nativeVideoService.loadedVideos.value)

const categoryFrequencies2 = nativeCategoryService.topFiveDailyCategories()
</script>

<template>
    <div class="category-body">
        <a-row class="chart-row" type="flex">
            <a-col :flex="10">
                <TopChart :date="simpleDateFormat(new Date())" :five-pairs="categoryFrequencies1" title-img="" title="热门分区"/>
            </a-col>
            <a-col :flex="2"/>
            <a-col :flex="10">
                <TopChart :date="simpleDateFormat(new Date())" :five-pairs="categoryFrequencies2" title-img="" title="最常浏览"/>
            </a-col>
        </a-row>
        <CategorysContainer class="all-category" @selectCategory="(category:Category) => {$emit('selectCategory',category)}"/>
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
        height: 35%;
    }

    .all-category {
        width: 90%;
        height: 45%;
        margin: 2rem 0;
    }
}
</style>
