<script setup>

import SearchBar from "@/components/SearchBar.vue";
import LeftBar from "@/components/LeftBar.vue";
import RightBar from "@/components/RightBar.vue";
</script>

<template>
    <div class="left-bar">
        <LeftBar style="height: 35rem;width: 5rem;margin-bottom: 2rem;margin-left: 2rem;"/>
    </div>
    <div class="right-bar">
        <RightBar style="height: 27rem;width: 7rem;right: 0;"/>
    </div>
    <div class="body">
        <SearchBar style="height: 4rem;width: 60rem;"/>
    </div>
</template>

<style scoped>
.body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-left: auto;
    margin-right: auto;
    height: 100%;
    width: 70%;
    background: yellow;
}
.left-bar {
    display: flex;
    align-items: end;
    float: left;
    position: fixed;
    height: 100%;
    width: 8%;
    background: #3f4c9b;
}
.right-bar {
    display: flex;
    justify-content: right;
    align-items: center;
    float: right;
    position: fixed;
    right: 0;
    height: 100%;
    width: 8%;
    background: #766C9B;
}
</style>
