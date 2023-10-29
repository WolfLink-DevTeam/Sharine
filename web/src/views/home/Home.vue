<script setup>
import SearchBar from "@/components/SearchBar.vue";
import RightBar from "@/components/CategoryBar.vue";
import HomeContent from '@/views/home/cpns/HomeContent.vue'
import HomeCategory from '@/views/home/cpns/HomeCategory.vue'
import { ref } from "vue";
import { useSystemStore } from "@/store/modules/system.js"

// home页面内容和分类得切换
const systemStore = useSystemStore()
const changeSearchClick = () => {
    systemStore.homeContentToCategoryChange()
}
const num = ref([
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
    {count : 1},
])
</script>

<template>
    <div class="home">
        <div class="search">
            <SearchBar></SearchBar>
        </div>
        <div class="home-body">
            <HomeContent v-show="systemStore.homeContentToCategory"></HomeContent>
            <HomeCategory v-show="!systemStore.homeContentToCategory"></HomeCategory>
            <div class="category-bar">
                <RightBar style="height: 27rem;width: 5rem;right: 0;"/>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
.home{
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    .search{
        height: 10%;
        min-height: 100px;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .home-body{
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: row;
        justify-content: center;
        .category-bar{
            flex: 1;
            height: 100%;
            display: flex;
            justify-content: center;
            flex-direction: column;
            align-items: flex-end;
        }
    }
}
</style>
