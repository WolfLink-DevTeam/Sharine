<script setup>

</script>

<template>
    <div class="home-category">
        <div class="item-category">
            <div class="category">

            </div>
            <div class="category">
            </div>
        </div>
        <div class="all-category">
        </div>
    </div>
</template>

<style lang="less" scoped>
.home-category{
    height: 100%;
    width: 93%;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    .item-category{
        width: 80%;
        height: 45%;
        display: flex;
        flex-direction: row;
        align-items: center;
        justify-content: center;
        .category{
            height: 80%;
            width: 40%;
            margin: 0 40px;
            border-radius: 16px;
            background: #555D8B;
            box-shadow: -4px 4px 12px 0px rgba(0, 0, 0, 0.4);
        }
    }
    .all-category{
        width: 70%;
        height: 55%;
        border-radius: 16px;
        margin: 40px 0;
        background: #555D8B;
        box-shadow: -4px 4px 12px 0px rgba(0, 0, 0, 0.4);
    }
}
</style>
