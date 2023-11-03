<script setup>
import "@/commons/global.css";
const props = defineProps({
    progress: {
        type: Number,
        required: true
    },
    speed: {
        type: String,
        required: true
    },
    timeLeft: {
        type: String,
        required: true
    }
})
</script>

<template>
    <div>
        <a-row style="align-items: center">
            <a-col class="text-shadow-focus" style="font-family: SHS-Bold,serif;font-size: 3rem">{{progress}}%</a-col>
            <a-col class="special-dot"/>
            <a-col class="text-shadow-focus" style="color: #474747;font-size: 1.2rem">{{speed}}</a-col>
            <a-col class="special-dot"/>
            <a-col class="text-shadow-focus" style="color: #474747;font-size: 1.2rem">{{timeLeft}}</a-col>
        </a-row>
        <a-space style="width: 100%" direction="vertical">
            <a-progress :size="12" style="margin-top: 0.5rem" :percent="progress" :show-info="false" :stroke-color="{
        '0%': 'rgba(85,93,139,0.5)',
        '100%': '#FCDCDD',
      }"/>
        </a-space>
    </div>
</template>

<style scoped lang="less">
.special-dot {
    margin-left: 1rem;
    margin-right: 1rem;
    width: 0.6rem;
    height: 0.6rem;
    border-radius: 0.4rem;
    background: #9F9F9F;
}
</style>