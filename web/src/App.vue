<script setup>

import SearchBar from "@/components/SearchBar.vue";
import LeftBar from "@/components/NavBar.vue";
import RightBar from "@/components/CategoryBar.vue";
import Logo from "@/components/Logo.vue";
</script>

<template>
    <div class="left-bar">
        <LeftBar style="height: 35rem;width: 5rem;margin-bottom: 2rem;margin-left: 2rem;"/>
    </div>
    <Logo style="height: 7rem;width: 16rem;right: 0;top: 0;position: fixed;z-index: 5"/>
    <div class="right-bar">
        <RightBar style="height: 27rem;width: 5rem;right: 0;"/>
    </div>
    <div class="body">
        <SearchBar style="height: 3.5rem;width: 55rem;margin-top: 2rem"/>
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
}
.left-bar {
    display: flex;
    align-items: end;
    float: left;
    position: fixed;
    height: 100%;
    width: 8%;
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
}
</style>
