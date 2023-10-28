<script setup>
import { ref, watch } from "vue";
import LeftBarItem from "@/components/NavBarItem.vue";

const selectPage = ref(1)

const radioClasses = ref([true, false, false, false])

watch(selectPage, (newValue, oldValue) => {
    radioClasses.value[oldValue - 1] = false;
    radioClasses.value[newValue - 1] = true;
    console.log(oldValue + " -> " + newValue)
})

</script>

<template>
    <div class="container">
        <LeftBarItem img="@/assets/home_icon.png" title="首页" sub-title="Home" :enabled="radioClasses[0]" @click="selectPage = 1"/>
        <LeftBarItem img="@/assets/home_icon.png" title="关注" sub-title="Favorite" :enabled="radioClasses[1]" @click="selectPage = 2"/>
        <LeftBarItem img="@/assets/home_icon.png" title="投稿" sub-title="Upload" :enabled="radioClasses[2]" @click="selectPage = 3"/>
        <LeftBarItem img="@/assets/home_icon.png" title="个人" sub-title="Person" :enabled="radioClasses[3]" @click="selectPage = 4"/>
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
}
</style>