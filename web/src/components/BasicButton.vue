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
    <div :style="{height: props.height,width: props.width}" class="btn-body">
        <slot/>
    </div>
</template>

<style lang="less" scoped>
.btn-body {
    border-radius: 0.8rem;
    color: white;
    font-size: 1.2rem;
    background: #555D8B;
    justify-content: center;
    align-items: center;
    display: flex;
}

.btn-body:hover {
    background: #60679d;
    cursor: pointer;
}

.btn-body:active {
    background: #3f4c9b;
}
</style>