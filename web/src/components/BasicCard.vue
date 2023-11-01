<script setup>
const props = defineProps({
    height: {
        type: String,
        required: true
    },
    width: {
        type: String,
        required: true
    }
})
</script>

<template>
    <div :style="{height: props.height,width: props.width}" class="basic-card-body">
        <slot/>
    </div>
</template>

<style scoped lang="less">
.basic-card-body {
    border-radius: 0.8rem;
    color: white;
    font-size: 1.2rem;
    background: #555D8B;
    justify-content: center;
    align-items: center;
    display: flex;
}
</style>