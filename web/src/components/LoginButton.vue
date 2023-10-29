<script setup>

</script>

<template>
    <div class="login-button">
       <span class="login-text">未登录</span>
    </div>
</template>

<style lang="less" scoped>
.login-button{
    text-align: center;
    position: absolute;
    left: 30px;
    top: 21px;
    width: 70px;
    height: 70px;
    opacity: 1;
    background: #FF5B5B;
    border-radius: 50%;
    box-shadow: -4px 4px 10px 0px rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
    .login-text{
        line-height: 10px;
    }
}
</style>
