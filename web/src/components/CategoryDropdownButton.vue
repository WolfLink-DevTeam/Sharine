<script setup lang="ts">

import {categoryService} from "@/services/CategoryService";
import {getImageUrl} from "@/utilities/ResourceUtility";

const props = defineProps({
    selectCategoryIndex: {
        type: Number,
        required: true
    }
})
defineEmits(['category-select'])
</script>

<template>
    <div class="panel">
        <a-dropdown>
            <a class="ant-dropdown-link" @click.prevent>
                <img src="@/assets/ui-icon/dropdown-icon.png" style="width: 1.5rem;height: 1.5rem">
            </a>
            <template #overlay>
                <a-menu>
                    <a-menu-item v-for="(category,index) in categoryService.list.value" @click="$emit('category-select',index)">
                        <div>
                            <img :src="getImageUrl(category.url)" style="height: 1.5rem;width: 1.5rem;margin-right: 0.7rem" alt="">
                            <span style="font-size: 1.2rem;color: black;font-family: SHS-Medium,serif">{{category.title}}</span>
                        </div>
                    </a-menu-item>
                </a-menu>
            </template>
        </a-dropdown>
        <img :src="getImageUrl(categoryService.getCategory(selectCategoryIndex).url)" style="height: 2rem;width: 2rem;margin-left: 0.7rem;margin-right: 0.7rem" alt="">
        <span style="font-size: 1.5rem;color: white;font-family: SHS-Medium,serif">{{categoryService.getCategory(selectCategoryIndex).title}}</span>
    </div>
</template>

<style scoped lang="less">
@import "@/commons/global-var.less";
.panel {
    height: 100%;
    width: 100%;
    border-radius: 0.5rem;
    display: flex;
    align-items: center;
    justify-content: center;
    background: #555D8B;
    padding: 1rem 1rem;
}
</style>