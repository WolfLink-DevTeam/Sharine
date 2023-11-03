import { createApp } from 'vue'
import '@/commons/global.css'
import "@/commons/global-var.less"
import App from './App.vue'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/reset.css'
// router的导入
import { router } from './router/index.js'
import VueVideoPlayer from '@videojs-player/vue';
import 'video.js/dist/video-js.css'
import {createPinia} from "pinia";
import axios from "axios";
createApp(App).use(Antd).use(router).use(createPinia()).use(VueVideoPlayer).mount('#app')