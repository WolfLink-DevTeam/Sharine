<script setup>
import { ref, watch } from "vue";
import LeftBarItem from "@/components/NavBarItem.vue";
import { useRouter } from "vue-router";

const selectPage = ref(1)
const router = useRouter()

const radioClasses = ref([true, false, false, false])

watch(selectPage, (newValue, oldValue) => {
    radioClasses.value[oldValue - 1] = false;
    radioClasses.value[newValue - 1] = true;
})

const changeButton = (count) => {
    selectPage.value = count
    if(count === 1){
        router.push({name: "home"})
    }
    if(count === 2){
        router.push({name: "favorite"})
    }
    if(count === 3){
        router.push({name: "upload"})
    }
    if(count === 4){
        router.push({name: "person"})
    }

}
</script>

<template>
    <div class="container">
        <LeftBarItem img="@/assets/ui-icon/home-navbar-icon.png" title="首页" sub-title="Home" :enabled="radioClasses[0]" @click="changeButton(1)"/>
        <LeftBarItem img="@/assets/ui-icon/favorite-navbar-icon.png" title="关注" sub-title="Favorite" :enabled="radioClasses[1]" @click="changeButton(2)"/>
        <LeftBarItem img="@/assets/ui-icon/upload-navbar-icon.png" title="投稿" sub-title="Upload" :enabled="radioClasses[2]" @click="changeButton(3)"/>
        <LeftBarItem img="@/assets/ui-icon/person-navbar-icon.png" title="个人" sub-title="Person" :enabled="radioClasses[3]" @click="changeButton(4)"/>
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