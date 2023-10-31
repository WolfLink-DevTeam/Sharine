<script setup>
import PersonVideoBar from "@/components/PersonVideoBar.vue";
import SimpleVideoCard from "@/components/SimpleVideoCard.vue";
import PersonCard from "@/components/PersonCard.vue";
import SearchBar from "@/components/SearchBar.vue";
</script>

<template>
    <div class="Person">
        <div class="search">
            <SearchBar/>
        </div>
        <PersonVideoBar class="video-bar" btn1-value="12" btn2-value="1" btn3-value="lock"/>
        <div class="video-list">
            <div class="video-row">
                <SimpleVideoCard/>
                <SimpleVideoCard/>
                <SimpleVideoCard/>
                <SimpleVideoCard/>
            </div>
            <div class="video-row">
                <SimpleVideoCard/>
                <SimpleVideoCard/>
                <SimpleVideoCard/>
                <SimpleVideoCard/>
            </div>
            <div class="video-row">
                <SimpleVideoCard/>
                <SimpleVideoCard/>
                <SimpleVideoCard/>
                <SimpleVideoCard/>
            </div>
        </div>
        <PersonCard class="person-card"/>
    </div>
</template>

<style lang="less" scoped>
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
.video-bar {
    margin-top: 8rem;
    margin-left: 4rem;
    width: 40%;
}
.video-list {
    display: flex;
    flex-direction: column;
    width: 75%;
    height: 80vh; // adjust this value as needed
    margin-top: 1rem;
    margin-left: 4rem;
    overflow-y: scroll;
}
.video-list::-webkit-scrollbar {
    width: 0;
}
.video-row {
    display: flex;
}
.person-card {
    position: absolute;
    right: 2rem;
    bottom: 12rem;
    height: 20rem;
    width: 18rem;
}
</style>
