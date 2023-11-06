import axios, {AxiosPromise} from "axios";
import {ResponsePack} from "@/models/ResponsePack";
import {userService} from "@/services/UserService";

export const httpClient = axios.create({
    baseURL: import.meta.env.VITE_SHARINE_API_BASE_URL,
    timeout: Number(import.meta.env.VITE_SHARINE_API_TIMEOUT)
});

httpClient.interceptors.request.use(
    (config: any) => {
        // 在发送请求之前做些什么
        config.headers['satoken'] = userService.getToken();
        return config;
    }
);
httpClient.interceptors.response.use(
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

export function pack(axiosPromise: AxiosPromise<any>): Promise<ResponsePack> {
    return axiosPromise.then(function (response : any) {
        return ResponsePack.of(response)
    }).catch(function (error: any) {
        console.log(error);
        return ResponsePack.ofEmpty()
    });
}