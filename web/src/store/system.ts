import { defineStore } from "pinia"

export const useSystemStore = defineStore("system", {
    state: () => ({
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
    }
})
export class Persist {
    getNavIndex() {
        return Number(localStorage.getItem("sharine-nav-index") || "0")
    }
    setNavIndex(value: number) {
        localStorage.setItem("sharine-nav-index",String(value))
    }
}
export const persist = new Persist()
