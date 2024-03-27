import axios, {AxiosInstance, AxiosRequestConfig, AxiosResponse} from "axios";
import {cookieService} from "@/services/native/CookieService";

export class RemoteService {
    serviceClient: AxiosInstance
    constructor(prefix: String) {
        this.serviceClient = axios.create({
            baseURL: import.meta.env.VITE_SHARINE_SERVER_URL+prefix,
            timeout: 10000
        });
        this.serviceClient.interceptors.request.use(
            (config: any) => {
                // 在发送请求之前添加 Token
                config.headers['satoken'] = cookieService.getToken();
                // 重写请求的URL
                urlMapper(config,"/aggregated","http://localhost:23700")
                urlMapper(config,"/categories","http://localhost:23600")
                urlMapper(config,"/oss","http://localhost:23600")
                urlMapper(config,"/bookmarks","http://localhost:23400")
                urlMapper(config,"/comments","http://localhost:23400")
                urlMapper(config,"/favorites","http://localhost:23400")
                urlMapper(config,"/user-relations","http://localhost:23400")
                urlMapper(config,"/interact-actions","http://localhost:23400")
                urlMapper(config,"/user-actions","http://localhost:23300")
                urlMapper(config,"/users","http://localhost:23300")
                urlMapper(config,"/video-actions","http://localhost:23500")
                urlMapper(config,"/videos","http://localhost:23500")
                return config;
            }
        );
        this.serviceClient.interceptors.response.use(
            (response: any) => {
                if(response?.data?.msg?.startsWith("程序内部错误: token 无效：")) {
                    alert("Token 已过期，请重新登录！")
                }
                return response;
            },
            (error: any) => {
                return Promise.reject(error);
            }
        );
    }
}
function urlMapper(config: AxiosRequestConfig, prefix: string,target: string) {
    if(config.baseURL && config.baseURL.endsWith(prefix)) {
        config.baseURL = target+prefix;
    }
}