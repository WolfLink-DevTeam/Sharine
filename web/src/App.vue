<script setup>

import SearchBar from "@/components/SearchBar.vue";
import LeftBar from "@/components/NavBar.vue";
import RightBar from "@/components/CategoryBar.vue";
import Logo from "@/components/Logo.vue";
import SimpleVideoCard from "@/components/SimpleVideoCard.vue";
import { useRouter } from 'vue-router'

const router = useRouter()
</script>

<template>
    <!-- <div class="body">
        <SearchBar style="height: 3.5rem;width: 55rem;margin-top: 2rem;position: fixed;top: 0;z-index: 5"/>
        <a-space style="margin-top: 7rem">
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
        </a-space>
        <a-space style="margin-top: 0.5rem">
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
        </a-space>
        <a-space style="margin-top: 0.5rem">
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
        </a-space>
        <a-space style="margin-top: 0.5rem">
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
            <SimpleVideoCard style="height: 14rem;width: 10rem"/>
        </a-space>
    </div> -->
    <router-view></router-view>
</template>

<style lang="less" scoped>
.body {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    margin-left: auto;
    margin-right: auto;
    height: 100%;
    width: 100%;
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
