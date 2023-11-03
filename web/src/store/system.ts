import { defineStore } from "pinia"

export interface State {
    homeContentToCategory: boolean
    isLogin: boolean
}
export const useSystemStore = defineStore("system", {
    state: (): State => ({
        // home界面，分类和内容切换管理
        homeContentToCategory: true,
        isLogin: false
    }),
    actions: {
        // 搜索改变
        homeContentToCategoryChange() {
            this.homeContentToCategory = !this.homeContentToCategory
        },
        login() {
            this.isLogin = true
        },
        logout() {
            this.isLogin = false
        }
    },
})