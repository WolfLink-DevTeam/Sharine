<script setup>

</script>

<template>
<div class="container">
    <img src="@/assets/logo.png" alt="" style="width: 5rem;height: 5rem">
    <div style="display: flex;flex-direction: column;width: 8rem;height: 5rem;justify-content: center;align-items: center">
        <span class="title">闪灵</span>
        <span class="subTitle">Sharine</span>
    </div>
</div>
</template>

<style scoped>
.container {
    background: #281F48;
    border-radius: 0 0 0 3rem;
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.7);
}
.title {
    font-family: HGHB_CNKI;
    font-size: 2.8rem;
    font-weight: normal;
    line-height: normal;
    position: relative;
    bottom: -0.3rem;
    right: -1rem;
    background: linear-gradient(180deg, #F7B9C2 0%, #8214FF 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    text-fill-color: transparent;
    text-shadow: 0 0 0.3rem rgba(255, 255, 255, 0.55);
}
.subTitle {
    font-family: Sylfaen;
    font-size: 1.8rem;
    font-weight: normal;
    line-height: normal;
    text-align: center;
    letter-spacing: 0em;
    position: relative;
    top: -0.2rem;
    right: -1rem;
    background: linear-gradient(180deg, #A849EC 0%, #ECA4B8 100%);
    -webkit-background-clip: text;
    -webkit-text-fill-color: transparent;
    background-clip: text;
    text-shadow: 0 0 0.4rem rgba(255, 255, 255, 0.55);
}
</style>