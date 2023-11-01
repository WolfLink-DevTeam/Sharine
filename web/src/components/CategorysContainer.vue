<script setup>

import CategoryCard from "@/components/CategoryItem.vue";
</script>

<template>
    <div class="category-container">
        <a-row class="titleRow" type="flex">
            <a-col class="title" :flex="1">全部分区</a-col>
            <a-col class="subTitle" :flex="1">Categories</a-col>
            <a-col :flex="20"/>
        </a-row>
<!--        临时样式，改为循环组件放置-->
        <a-row class="categoryRow" type="flex" justify="space-between">
            <a-col :span="5"><CategoryCard img="@/assets/category-icon/alltype-category.png" text="综合"/></a-col>
            <a-col :span="5"><CategoryCard img="@/assets/category-icon/alltype-category.png" text="综合"/></a-col>
            <a-col :span="5"><CategoryCard img="@/assets/category-icon/alltype-category.png" text="综合"/></a-col>
            <a-col :span="5"><CategoryCard img="@/assets/category-icon/alltype-category.png" text="综合"/></a-col>
        </a-row>
    </div>
</template>

<style scoped lang="less">
@import "@/common/css/global-var.less";
.category-container {
    width: 100%;
    height: 100%;
    border-radius: 1rem;
    background: #555D8B;
    box-shadow: -4px 4px 12px 0 rgba(0, 0, 0, 0.4);
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: start;
}
.titleRow {
    width: 90%;
    height: 15%;
    margin-top: 2rem;
    color: @primary-text-color;
    font-size: 1.6rem;
}
.title {
    font-size: 1.6rem;
    font-family: SHS-Medium,serif;
}
.subTitle {
    font-size: 1.6rem;
    font-family: SHS-Normal, serif;
}
.categoryRow {
    width: 80%;
    height: 10%;
}
</style>