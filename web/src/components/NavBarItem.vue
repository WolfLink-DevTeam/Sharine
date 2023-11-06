<script setup>
import {getImageUrl} from "@/utilities/ResourceUtility.ts";
const props = defineProps({
    img: {
        type: String,
        required: true
    },
    title: {
        type: String,
        required: true
    },
    subTitle: {
        type: String,
        required: true
    },
    enabled: {
        type: Boolean,
        required: true
    }
})
defineEmits(['click'])

</script>

<template>
    <!--启用-->
    <button class="enabled" v-if="enabled">
        <img :src="img" alt="">
        <span class="title">
            {{ title }}
        </span>
        <span class="subTitle">
            {{ subTitle }}
        </span>
    </button>
    <!--禁用-->
    <button v-else class="disabled" @click="$emit('click')">
        <img :src="img" alt="">
    </button>
</template>

<style scoped>

.title {
    font-size: 1rem;
    color: var(--primary-text-color);
}
.subTitle {
    font-size: 0.8rem;
    color: #d1d1d1;
}

img {
    width: 3rem;
    height: 3rem;
}

.disabled {
    height: 6rem;
    width: 4rem;
    background: inherit;
    border-width: 0;
}

.enabled {
    height: 9rem;
    width: 4rem;
    background: var(--component-secondary-color);
    border-radius: 0.8rem;
}

</style>