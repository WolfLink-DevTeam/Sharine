import { createRouter, createWebHashHistory } from 'vue-router'

export const router = createRouter({
    history: createWebHashHistory(),
    routes: [
        {
            path: '/',
            redirect: '/app/home'
        },
        {
            path: '/app',
            redirect: '/app/home'
        },
        {
            name: 'layout',
            path: '/app',
            component: () => import('@/layout/LayoutOverview.vue'),
            children: [
                // 设置页面跳转
                {
                    name: 'home',
                    path: '/app/home',
                    component: () => import('@/views/home/Home.vue')
                },
                {
                    name: 'favorite',
                    path: '/app/favorite',
                    component: () => import('@/views/favorite/Favorite.vue')
                },
                {
                    name: 'upload',
                    path: '/app/upload',
                    component: () => import('@/views/upload/Upload.vue')
                },
                {
                    name: 'person',
                    path: '/app/person',
                    component: () => import('@/views/person/Person.vue')
                },
            ]
        },
    ]
})