<script setup>
import {getImageUrl} from "@/common/js/ResourceUtils.js";
const props = defineProps({
    img: {
        type: String,
        required: true
    },
    text: {
        type: String,
        required: true
    }
})

</script>

<template>
<a-row class="card-body" type="flex">
    <a-col :span="7"><img style="width: 2rem;height: 2rem" :src="getImageUrl(img)" alt=""></a-col>
    <a-col :span="2"/>
    <a-col :span="7" class="text">{{text}}</a-col>
</a-row>
</template>

<style scoped lang="less">
.card-body {
    width: 100%;
    height: 100%;
    transform: skewX(-18deg);
    background: #FFFFFF;
    display: flex;
    align-items: center;
    justify-content: center;
    div {
        transform: skewX(18deg);
    }
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.4);
}
.card-body:hover {
    cursor: pointer;
    background: #F1D3DA;
}
.card-body:active {
    background: #f6a8bb;
}
a-row {
    display: flex;
    justify-content: center;
    align-items: center;
}
.text {
    font-size: 1.2rem;
}
</style>