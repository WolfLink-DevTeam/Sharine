<script setup>

</script>

<template>
    <div class="container">
        <span style="font-size: 1.5rem;color: var(--primary-text-color);font-weight: lighter">当前</span>
        <span style="font-size: 1.5rem;color: var(--primary-text-color);font-weight: lighter">分区</span>
        <span style="width: 3.5rem;height: 0.15rem;background: #9589BF;margin-bottom: 1rem;margin-top: 1rem"/>
        <img src="@/assets/alltype_icon.png" style="width: 4rem;height: 4rem;margin-bottom: 1rem" alt="">
        <span style=" writing-mode: vertical-rl;text-orientation: upright;font-size: 2.4rem;font-weight: bold;letter-spacing: 0.8rem;color: var(--primary-text-color)">综合</span>
        <div style="margin-top: 1.5rem;background: white;border-radius: 2rem;width: 3rem;height: 3rem;display: flex;justify-content: center;align-items: center">
            <img src="@/assets/left_icon.png" style="width: 2rem;height: 2rem;margin-right: 0.3rem" alt="">
        </div>

    </div>
</template>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    background: var(--component-background-color);
    border-radius: 1.2rem 0 0 1.2rem;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
}
</style>