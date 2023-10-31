<script setup>
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
    console.log("before:"+lastLoading)
    next();
});

router.afterEach((to, from) => {
    let now = Date.now()
    console.log("now:"+now)
    if(now - lastLoading < 400) {
        setTimeout(()=>{
            lastLoading = now
            loading.value = false;
        },400)
    } else {
        lastLoading = now
        loading.value = false;
    }
});

</script>

<template>
    <a-spin :spinning="loading" :indicator="indicator">
        <router-view/>
    </a-spin>
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
