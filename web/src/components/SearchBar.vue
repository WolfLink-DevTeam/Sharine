<script setup>
import {ref} from "vue";

const searchText = ref("")
</script>

<template>
    <div class="container">
        <input placeholder="搜你想搜..." v-model="searchText">
        <button class="circle">
            <img src="@/assets/search_icon.png" style="width: 2.5rem;height: 2.5rem">
        </button>
    </div>
</template>

<style scoped>
.circle {
    width: 3rem;
    height: 3rem;
    border-radius: 1.5rem;
    display: flex;
    justify-content: center;
    align-items: center;
}
button {
    background: none;
    color: inherit;
    border: none;
    padding: 0;
    font: inherit;
    cursor: pointer;
    outline: inherit;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.4);
}
button:hover {
    background: white;
}
button:active {
    background: #3f4c9b;
}
.container {
    width: 50%;
    display: flex;
    justify-content: center;
    align-items: center;
    background: var(--component-background-color);
    border-radius: 0.8rem;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
}
.container:hover {
    background: #60679d;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.5);
}
input::placeholder {
    color: #AFAFAF;
}
input {
    color: white;
    font-size: 1.8rem;
    width: 90%;
    height: 80%;
    border-radius: 0.8rem;
    background:none;
    outline:none;
    border:none;
}
</style>