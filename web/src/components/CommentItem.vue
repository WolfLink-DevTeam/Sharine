<script setup>

</script>

<template>
<div>
    <div id="first-line">
        <img src="@/assets/logo.png" id="avatar">
        <div id="name">这里是非常非常长的用户名</div>
    </div>
    <div id="content">
        这里是评论内容。
        这里是评论内容。
        这里是评论内容。
        这里是评论内容。
    </div>
</div>
</template>

<style scoped lang="less">
#first-line {
    margin-top: 0.7rem;
    width: 100%;
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-bottom: 0.5rem;
}
#avatar {
    width: 2.5rem;
    height: 2.5rem;
    border-radius: 2rem;
}
#name {
    width: 100%;
    margin-left: 1rem;
    font-size: 1rem;
    font-family: SHS-Bold,serif;
}
#content {
    margin-left: 3rem;
    padding: 0.8rem;
    border-radius: 0 1rem 1rem 1rem;
    background: #766C9B;
    font-size: 0.8rem;
    font-family: SHS-Light,serif;
}
</style>