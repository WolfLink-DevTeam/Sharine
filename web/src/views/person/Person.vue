<script setup>
import PersonVideoBar from "@/components/PersonVideoBar.vue";
import SimpleVideoCard from "@/components/SimpleVideoCard.vue";
import PersonCard from "@/components/PersonCard.vue";
import SearchBar from "@/components/SearchBar.vue";
</script>

<template>
    <div class="person">
        <div class="search">
            <SearchBar/>
        </div>
        <div style="width: 70%;">
            <PersonVideoBar class="video-bar" btn1-value="12" btn2-value="1" btn3-value="lock"/>
            <a-col class="video-list">
                <a-row :gutter="[24,12]">
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                    <a-col :span="6"><SimpleVideoCard/></a-col>
                </a-row>
            </a-col>
        </div>
        <div style="width: 30%;justify-content: center;align-items: center;display: flex">
            <PersonCard class="person-card"/>
        </div>
    </div>
</template>

<style lang="less" scoped>
.person {
    display: flex;
}
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
    height: 80vh; // adjust this value as needed
    margin-top: 1rem;
    margin-left: 4rem;
    overflow-y: scroll;
}
.video-list::-webkit-scrollbar {
    width: 0;
}
.person-card {
    height: 40%;
    width: 60%;
    min-height: 20rem;
    min-width: 20rem;
}
</style>
