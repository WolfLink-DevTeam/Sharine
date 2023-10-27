import { createApp } from 'vue'
import '@/common/css/global.css'
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
createApp(App).use(Antd).mount('#app')