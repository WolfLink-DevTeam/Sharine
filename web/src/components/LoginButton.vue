<script setup lang="ts">

import {ref} from "vue";
import {userService} from "@/services/UserService.js";
import {useSystemStore} from "@/store/system";
import {accountService} from "@/services/native/AccountService";

const modalVisible = ref(false)
const isRegister = ref(false)
const tempAccount = ref(userService.getAccount())
const tempPassword = ref(userService.getPassword())
const verificationCode = ref(0)

const store = useSystemStore()
function autoLogin() {
    if(!store.isLogin && userService.getPassword().length != 0) {
        btnLogin()
    }
}
autoLogin()

function setModalVisible(value: boolean) {
    modalVisible.value = value
}

function setRegister(value: boolean) {
    accountService.saveInfo(tempAccount.value,tempPassword.value)
    if(!isRegister.value && value) {
        userService.askForEmailVerification().then(pack => {
            if(pack.code === 0) alert("验证码已发放至邮箱！")
            else if(pack.code === -1){
                alert("网络异常，请稍后再尝试")
            } else {
                alert(pack.msg)
                return
            }
        })
    }
    if(isRegister.value && value) {
        if(verificationCode.value.toString().length < 6) {
            alert("请正确填写验证码")
            return
        }
        userService.register(verificationCode.value).then(pack => {
            if(pack.code === 0) {
                alert("注册成功！现在可以登录了。")
                setModalVisible(false)
            } else {
                alert("注册失败，原因："+pack.msg)
            }
        })
    }
    isRegister.value = value
}
function btnLogin() {
    accountService.saveInfo(tempAccount.value,tempPassword.value)
    accountService.login().then(result => {
        if(result) {
            setModalVisible(false)
        }
    })
}

</script>

<template>
    <a-button class="login-button" @click="setModalVisible(true)" v-if="!store.isLogin">
       <span class="login-text">未登录</span>
    </a-button>
    <a-button v-else class="user-button" @click="accountService.logout()">
        <img class="user-avatar" :src="userService.getLocalUser()?.avatar" alt="">
        <span class="user-info">{{ userService.getLocalUser().nickname }}</span>
    </a-button>
    <div style="z-index: 100;width: 200%;height: 100%;opacity: 0.4;background: black;position: fixed;" v-show="modalVisible" @click="setModalVisible(false)"/>
    <div id="modal" v-show="modalVisible" style="z-index: 101">
        <div id="modal-body">
            <div id="login-body">
                <div style="height: 10%;width: 100%">账号密码登录</div>
                <div style="margin-top: 2rem;height: 10rem;display: flex;flex-direction: column;justify-content: center">
                    <a-row style="justify-content: center;align-items: center;height: 2.75rem">
                        <div style="width: 20%;font-size: 1.4rem;margin-right: 0.75rem">账户</div><a-input  style="width: 65%;height: 90%;border-radius: 0.75rem" v-model:value="tempAccount"/>
                    </a-row>
                    <a-row style="justify-content: center;align-items: center;margin-top: 1rem;height: 2.75rem">
                        <div style="width: 20%;font-size: 1.4rem;margin-right: 0.75rem">密码</div><a-input-password style="width: 65%;height: 90%;border-radius: 0.75rem" v-model:value="tempPassword"/>
                    </a-row>
                    <a-row v-show="isRegister" style="justify-content: center;align-items: center;margin-top: 1rem;height: 2.75rem">
                        <div style="width: 40%;font-size: 1.4rem;margin-right: 0.75rem">邮箱验证码</div><a-input-number :controls="false" style="width: 45%;height: 90%;border-radius: 0.75rem" v-model:value="verificationCode"/>
                    </a-row>
                </div>
                <div style="margin-top: 2rem">
                    <a-button style="background: #b4b9ff;width: 7rem;height: 2.4rem;border-radius: 1rem;color: white;font-family: SHS-Bold,serif;font-size: 1.2rem;margin-right: 1rem" @click="setRegister(true)">注册</a-button>
                    <a-button @click="btnLogin" v-if="!isRegister" style="background: #ECA4B8;width: 7rem;height: 2.4rem;border-radius: 1rem;color: white;font-family: SHS-Bold,serif;font-size: 1.2rem">登录</a-button>
                </div>
                <span style="font-size: 1rem">忘记密码？</span>
            </div>
            <div id="divider"/>
            <div id="qrcode-body">
                <div style="height: 10%">扫码登录</div>
                <div style="margin-top: 10%" id="qrcode"/>
            </div>
        </div>
    </div>
</template>

<style lang="less" scoped>
.login-button{
    z-index: 99;
    text-align: center;
    position: fixed;
    left: 2rem;
    top: 1rem;
    width: 4rem;
    height: 4rem;
    //opacity: 1;
    background: #FF5B5B;
    border-radius: 50%;
    box-shadow: -4px 4px 10px 0px rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: center;
    align-items: center;
    .login-text{
        color: white;
        font-size: 1rem;
    }
}
.user-button {
    z-index: 99;
    position: fixed;
    left: 2rem;
    top: 1rem;
    min-width: 10rem;
    max-width: 16rem;
    height: 4rem;
    background: #555D8B;
    border-radius: 1.5rem;
    box-shadow: -4px 4px 10px 0px rgba(0, 0, 0, 0.3);
    display: flex;
    justify-content: start;
    align-items: center;
    .user-info {
        color: white;
        font-family: SHS-Bold,serif;
        width: 100%;
        font-size: 1rem;
        text-align: center;
    }
    .user-avatar {
        width: 3rem;
        height: 3rem;
    }
}

@keyframes fade-in{
0%{opacity: 0;}
100%{opacity: 1;}
}
#modal {
    animation:fade-in 0.8s ease;
    left: 0;
    right: 0;
    margin: auto;
    top: 0;
    bottom: 0;
    position: fixed;
    z-index: 98;
    width: 35%;
    height: 38%;
    border-radius: 3rem;
    background: linear-gradient(90deg, #5C68AD 0%, #555D8B 43%, #4C3E59 100%);
    padding: 2rem;
    min-height: 22rem;
    min-width: 40rem;
    #modal-body {
        height: 100%;
        width: 100%;
        display: flex;
        #login-body {
            width: 50%;
            margin-right: 3%;
            height: 100%;
            font-size: 1.6rem;
            text-align: center;
            color: white;
            font-family: SHS-Bold,serif;
        }
        #divider {
            width: 1%;
            height: 100%;
            background: #9589BF;
            border-radius: 1rem;
        }
        #qrcode-body {
            margin-left: 3%;
            width: 43%;
            height: 100%;
            font-size: 1.6rem;
            text-align: center;
            color: white;
            font-family: SHS-Bold,serif;
            display: flex;
            flex-direction: column;
            justify-content: start;
            align-items: center;
            #qrcode {
                width: 14rem;
                height: 14rem;
                background: white;
            }
        }
    }
}
</style>
