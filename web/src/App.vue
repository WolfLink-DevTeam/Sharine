<script setup lang="ts">
import { useRouter } from 'vue-router'
import {h, ref} from "vue";
import { LoadingOutlined } from '@ant-design/icons-vue';

const router = useRouter()
let lastLoading = 0
const loading = ref(false);
const indicator = h(LoadingOutlined, {
    style: {
        fontSize: '24px',
    },
    spin: true,
});
router.beforeEach((to, from, next) => {
    loading.value = true; // Show loading animation
    lastLoading = Date.now()
    next();
});

router.afterEach((to, from) => {
    let now = Date.now()
    if(now - lastLoading < 400) {
        setTimeout(()=>{
            lastLoading = now
            loading.value = false;
        },200)
    } else {
        lastLoading = now
        loading.value = false;
    }
    // TODO 调试接口



});

</script>

<template>
    <a-spin :spinning="loading" :indicator="indicator">
        <router-view/>
    </a-spin>
</template>

<style lang="less" scoped>

</style>
