<script setup lang="ts">
import {Ref, ref} from "vue";
import {User} from "@/models/User";
import {remoteUserService} from "@/services/remote/RemoteUserService";

const props = defineProps({
    userId: {
        type:Number,
        required: true
    }
})
const user: Ref<User|null> = ref(null)
remoteUserService.get(props.userId).then(pack => {
    // TODO 视图对象转换
    user.value = pack.data
})
</script>

<template>
    <div class="card-body" v-if="user !== null">
        <div style="display: flex;flex-direction: row;">
            <img class="person-avatar" :src="user.avatar" alt="">
            <div style="display: flex;flex-direction: column;margin-top: 1.3rem;margin-left: 1.5rem;font-size: 1.2rem">
                <span style="color: white;margin-top: 1rem"><span style="font-family: SHS-ExtraLight,serif">粉丝</span><span style="font-family: SHS-Bold,serif;margin-left: 1rem">{{user.followedCount}}</span></span>
                <span style="color: white;margin-top: 1rem"><span style="font-family: SHS-ExtraLight,serif">获赞</span><span style="font-family: SHS-Bold,serif;margin-left: 1rem">{{user.beenFavoriteCount}}</span></span>
                <span style="color: white;margin-top: 1rem"><span style="font-family: SHS-ExtraLight,serif">播放</span><span style="font-family: SHS-Bold,serif;margin-left: 1rem">{{user.beenViewCount}}</span></span>
            </div>
        </div>
        <div style="margin-top: 0.7rem;width: 100%;text-align: center;background: rgba(255,255,255,0.15);padding-top: 0.2rem;padding-bottom: 0.2rem">
            <span class="person-name">{{user.nickname}}</span>
        </div>
<!--        <div class="tag-line">-->
<!--            <span><img style="background: rgba(61,61,61,0.5);border-radius: 1rem;margin-left: 1rem;width: 1.6rem;height: 1.6rem;padding: 0.08rem" src="@/assets/ui-icon/boy-icon.png" alt=""></span>-->
<!--            <span style="margin-left: 0.3rem;color: #e6e6e6;background: rgba(61,61,61,0.5);border-radius: 1rem;padding: 0.15rem 0.5rem">IP属地：未知</span>-->
<!--        </div>-->
        <div style="width: 100%;padding: 1rem;">
            <span style="color: white;font-size: 1rem;font-family: SHS-ExtraLight,serif">{{user.content}}</span>
        </div>
    </div>
</template>

<style scoped lang="less">
.card-body {
    background: linear-gradient(180deg, #606DE7 0%, #555D8B 4%, #555D8B 86%, #6655B3 95%, #606DE7 100%);
    border-radius: 1rem;
}
.person-avatar {
    height: 7rem;
    width: 7rem;
    border-radius: 4rem;
    margin-left: 2rem;
    margin-top: 2rem;
    border: 0.1rem solid rgba(166, 166, 246, 0.5);
}
.person-name {
    font-size: 1.4rem;
    color: #ffffff;
}
.tag-line {
    margin-top: 1.2rem;
    font-size: 1.2rem;
    justify-content: start;
    align-items: center;
    display: flex;
}
</style>