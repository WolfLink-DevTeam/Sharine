<script setup lang="ts">
import { ref, watch } from "vue";
import LeftBarItem from "@/components/NavBarItem.vue";
import { useRouter } from "vue-router";
import {persist} from "@/store/system.js";

const router = useRouter()

const navIndex = ref(persist.getNavIndex())
function navChange(index: number) {
    if(persist.getNavIndex() === index) return
    if(index === 0){
        router.push("home")
    }
    if(index === 1){
        router.push("favorite")
    }
    if(index === 2){
        router.push("upload")
    }
    if(index === 3){
        router.push("person")
    }
    persist.setNavIndex(index)
    navIndex.value = index
}
</script>

<template>
    <div class="container">
        <LeftBarItem img="@/assets/ui-icon/home-navbar-icon.png" title="首页" sub-title="Home" :enabled="navIndex === 0" @click="navChange(0)"/>
        <LeftBarItem img="@/assets/ui-icon/favorite-navbar-icon.png" title="关注" sub-title="Favorite" :enabled="navIndex === 1" @click="navChange(1)"/>
        <LeftBarItem img="@/assets/ui-icon/upload-navbar-icon.png" title="投稿" sub-title="Upload" :enabled="navIndex === 2" @click="navChange(2)"/>
        <LeftBarItem img="@/assets/ui-icon/person-navbar-icon.png" title="个人" sub-title="Person" :enabled="navIndex === 3" @click="navChange(3)"/>
    </div>
</template>

<style scoped>
button {
    height: 8rem;
    width: 4rem;
    background: inherit;
    border-width: 0;
    outline: inherit;
}
.enable {
    border-radius: 1rem;
    background: var(--component-secondary-color);
    color: white;
}
.container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: var(--component-background-color);
    border-radius: 0.8rem;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
    height: 35rem;
    width: 5rem;
    margin-bottom: 2rem;
    margin-left: 2rem;
}
</style>