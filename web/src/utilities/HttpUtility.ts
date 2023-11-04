import axios, {AxiosPromise} from "axios";
import {ResponsePack} from "@/models/ResponsePack";
import {userService} from "@/services/UserService";
export const httpClient = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    timeout: 1000
});
httpClient.interceptors.request.use(
    (config) => {
        // 在发送请求之前做些什么
        config.headers['satoken'] = userService.getToken();
        return config;
    }
);
httpClient.interceptors.response.use(
    (response) => {
        if(response?.data?.msg?.startsWith("程序内部错误: token 无效：")) {
            alert("Token 已过期，请重新登录！")
        }
        return response;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export function pack(axiosPromise: AxiosPromise<any>): Promise<ResponsePack> {
    return axiosPromise.then(function (response) {
        return ResponsePack.of(response)
    }).catch(function (error) {
        console.log(error);
        return ResponsePack.ofEmpty()
    });
}