import { createApp } from 'vue'
import '@/common/css/global.css'
import "@/common/css/global-var.less"
import App from './App.vue'
import Antd from 'ant-design-vue'
import { pinia } from './store/index.js'
import 'ant-design-vue/dist/reset.css'
// router的导入
import { router } from './router/index.js'

createApp(App).use(Antd).use(router).use(pinia).mount('#app')