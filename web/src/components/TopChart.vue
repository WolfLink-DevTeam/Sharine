<script setup>

import {ref} from "vue";

const props = defineProps({
    title:{
        type:String,
        required:true
    },
    titleImg:{
        type:String,
        required:true
    },
    date:{
        type:String,
        required:true
    },
    fivePairs:{
        type:Array,
        required:true
    }
})
props.fivePairs.sort((a,b)=>a['value'] - b['value'])
const maxHeight = 10
let totalValue = 0
let maxValue = -1
let subTitle = ref("")
props.fivePairs.forEach((pair,i,a)=>{
    const v = Number(pair['value'])
    totalValue += v;
    const k = pair['key']
    if(v > maxValue) {
        maxValue = v;
        subTitle.value = k;
    }
})
</script>

<template>
<div class="card-body">
    <a-row class="row">
        <a-col class="title-part-1">{{title}}</a-col>
        <a-col style="width: 0.2rem;height: 1.8rem;background: #9589BF;border-radius: 0.5rem;margin: 1rem 1rem;"/>
        <a-col><img src="@/assets/category-icon/live-category.png" alt="" style="width: 2rem;height: 2rem;margin-right: 0.6rem"></a-col>
        <a-col class="title-part-2">
            {{subTitle}}
        </a-col>
        <a-col class="date">{{date}}</a-col>
    </a-row>
    <a-row class="row" style="height: 75%">
        <a-col :span="2" class="top5-text">T
            O
            P
            5</a-col>
        <a-col :span="20" style="width: 100%;height: 100%">
            <a-row class="pillar-body">
                <template v-for="item in fivePairs" :key="item['value']">
                    <a-col :flex="1" class="pillar-col">
                        <div :style="{height: item['value'] * maxHeight+'rem'}" class="pillar"/>
                    </a-col>
                </template>
            </a-row>
            <a-row class="divider-line"/>
            <a-row class="text-body" justify="space-between">
                <template v-for="item in fivePairs" :key="item['value']">
                    <a-col :flex="1">{{item['key']}}</a-col>
                </template>
            </a-row>
        </a-col>
        <a-col :span="2" class="top5-text">T
            O
            P
            5</a-col>
    </a-row>
    <a-row/>
</div>
</template>

<style scoped lang="less">
@import "@/commons/global-var.less";
.row {
    display: flex;
    align-items: center;
}
.card-body {
    background: @component-background-color;
    border-radius: 1rem;
    box-shadow: 0 0 1rem rgba(0, 0, 0, 0.4);
    width: 100%;
    height: 100%;
}
.title-part-1 {
    margin-left: 2rem;
    font-family: SHS-ExtraLight,serif;
    font-size: 1.5rem;
    color: @primary-text-color;
}
.title-part-2 {
    font-family: SHS-Bold,serif;
    font-size: 1.5rem;
    color: @primary-text-color;
}
.date {
    position: absolute;
    right: 1rem;
    padding: 0.4rem;
    width: 6rem;
    text-align: center;
    border-radius: 1.2rem;
    background: rgba(0, 0, 0, 0.3);
    color: @primary-text-color;
    font-size: 1.1rem;
}
.top5-text {
    padding-left: 0.5rem;
    padding-right: 0.5rem;
    text-align: center;
    font-size: 1.6rem;
    color: #CACACA;
}
.pillar-body {
    height: 80%;
}
.pillar-col {
    display: flex;
    align-items: end;
    justify-content: center;
}
.pillar {
    width: 50%;
    border-radius: 0.5rem 0.5rem 0 0;
    background: linear-gradient(180deg, #FFDEDE 0%, #9589BF 100%);
}
.divider-line {
    height: 2%;
    background: #9589BF;
    border-radius: 1rem;
}
.text-body {
    color: @primary-text-color;
    font-family: SHS-Medium, serif;
    height: 18%;
    text-align: center;
    display: flex;
    align-items: center;
    justify-content: center;

}
</style>