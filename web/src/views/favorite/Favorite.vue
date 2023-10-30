<script setup>
import SearchBar from "@/components/SearchBar.vue";
import DetailVideoCard from "@/components/DetailVideoCard.vue";
</script>

<template>
    <div class="favorite">
        <div class="search">
            <SearchBar></SearchBar>
        </div>
        <div class="favorite-page">
            <div class="favorite-list">
                <DetailVideoCard direction="left"/>
                <DetailVideoCard direction="right"/>
                <DetailVideoCard direction="left"/>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
.favorite{
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    .search{
        z-index: 5;
        height: 10%;
        min-height: 100px;
        width: 100%;
        position: fixed;
        top: 0;
        left: 0;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .favorite-page{
        width: 100%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        overflow-y: scroll;
        .favorite-list{
            width: 70%;
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 7rem;
        }
    }
    .favorite-page::-webkit-scrollbar {
        width: 0;
    }
}
</style>
