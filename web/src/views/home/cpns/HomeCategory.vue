<script setup>

import CategorysContainer from "@/components/CategorysContainer.vue";
</script>

<template>
    <div class="body">
        <a-row class="chart-row" type="flex">
            <a-col :flex="10" class="chart"/>
            <a-col :flex="1"/>
            <a-col :flex="10" class="chart"/>
        </a-row>
        <CategorysContainer class="all-category"/>
    </div>
</template>

<style lang="less" scoped>
.body {
    height: 100%;
    width: 93%;
    display: flex;
    flex-direction: column;
    align-items: center;
    .chart-row {
        width: 70%;
        height: 30%;
        .chart{
            border-radius: 16px;
            background: #555D8B;
            box-shadow: -4px 4px 12px 0px rgba(0, 0, 0, 0.4);
        }
    }
    .all-category{
        width: 70%;
        height: 45%;
        margin: 3rem 0;
    }
}
</style>
