<script setup>
import SearchBar from "@/components/SearchBar.vue";
</script>

<template>
    <div class="favorite">
        <div class="search">
            <SearchBar></SearchBar>
        </div>
        <div class="favorite-page">
            <div class="favorite-list">
                <div class="favorite-item">
                    <div class="cover">
                        <img class="img-cover" src="@/assets/test_video_cover.png">
                    </div>
                    <div class="introduce"></div>
                </div>
                <div class="favorite-item">
                    <div class="cover"></div>
                    <div class="introduce"></div>
                </div>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
.favorite{
    width: 100%;
    height: 100vh;
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: flex-start;
    .search{
        height: 10%;
        min-height: 100px;
        width: 100%;
        display: flex;
        align-items: center;
        justify-content: center;
    }
    .favorite-page{
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: flex-start;
        align-items: center;
        .favorite-list{
            width: 70%;
            height: 100%;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
            align-items: center;
            margin: 20px 100px 0 100px;
            .favorite-item{
                width: 100%;
                height: 100%;
                display: flex;
                flex-direction: row;
                margin-top: 20px;
                justify-content: flex-start;
                align-items: center;
                overflow-y: scroll;
                .cover{
                    width: 300px;
                    height: 400px;
                    .img-cover{
                        width: 100%;
                        height: 65%;
                        border-radius: 0.7rem 0.7rem 0 0;
                    }
                }
                .introduce{
                    height: 380px;
                    width: 550px;
                }
            }
            .favorite-item::-webkit-scrollbar {
                width: 0;
            }
        }
        
    }
}
</style>
