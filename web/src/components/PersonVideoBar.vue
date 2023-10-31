<script setup>
import {ref, watchEffect} from "vue";

const emits = defineEmits(['btn1clicked','btn2clicked','btn3clicked'])
let activeButton = ref(1)

const buttons = ref([
    { name: '作品', btnValue: 'btn1Value', locked: false },
    { name: '喜欢', btnValue: 'btn2Value', locked: false },
    { name: '收藏', btnValue: 'btn3Value', locked: false }
])

const setActive = (btn) => {
    if(buttons.value[btn-1].locked) return
    activeButton.value = btn;
    emits('btn'+btn+'clicked');
}

const props = defineProps({
    btn1Value: { required: true },
    btn2Value: { required: true },
    btn3Value: { required: true }
})

watchEffect(() => {
    buttons.value.forEach((btn, index) => {
        if (props[btn.btnValue] === 'lock') btn.locked = true
    })
})
</script>

<template>
    <div style="justify-content: center;align-items: center;display: flex">
        <button
            style="margin-left: 2rem;margin-right: 2rem"
            v-for="(btn, index) in buttons"
            :key="index"
            :class="{ 'active': activeButton === index + 1 }"
            @click="setActive(index + 1)"
        >
      <span>
        <span>{{ btn.name }}</span>
        <span v-if="!btn.locked" style="margin-left: 1rem">{{ props[btn.btnValue] }}</span>
        <span v-else style="margin-left: 1rem">
          <img style="height: 1.5rem;width: 1.5rem" src="@/assets/ui-icon/lock-icon.png" alt="">
        </span>
      </span>
        </button>
    </div>
</template>

<style scoped>
button {
    font-size: 1.4rem;
    height: 3rem;
    width: 7rem;
    border: none;
    cursor: pointer;
    color: #8A8A8A;
}

button.active {
    border-bottom: 0.1rem solid black;
    color: #3D3D3D;
}
</style>
