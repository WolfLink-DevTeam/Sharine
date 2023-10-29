<script setup>
import LeftBar from "@/components/NavBar.vue"
import Logo from "@/components/Logo.vue"
import LoginButtion from '@/components/LoginButton.vue'
</script>

<template>
    <div class="layout-overview">
        <div class="left-bar">
            <div class="blank"></div>
            <LeftBar></LeftBar>
            <LoginButtion></LoginButtion>
        </div>
        <div class="right-bar">
            <router-view></router-view>
        </div>
    </div>
    <Logo style="height: 7rem;width: 16rem;right: 0;top: 0;position: fixed;z-index: 5"/>
</template>

<style lang="less" scoped>
.layout-overview{
    height: 100vh;
    width: 100vw;
    display: flex;
    overflow-y: hidden;
    position: relative;
    .left-bar {
        display: flex;
        align-items: center;
        flex-direction: column;
        justify-content: center;
        float: left;
        height: 100%;
        width: 100px;
        .blank{
            width: 100%;
            height: 10%;
            min-height: 150px;
        }
    }
    .right-bar{
        flex: auto;
        height: 100%;
    }
}
</style>
