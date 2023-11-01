import { defineStore } from "pinia"

export const videoStore = defineStore("system", {
    state: () => ({
        // home界面，分类和内容切换管理
        homeContentToCategory: true
    }),
    actions: {
        // 搜索改变
        homeContentToCategoryChange() {
            this.homeContentToCategory = !this.homeContentToCategory
        },
    }
})