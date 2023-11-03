import axios, {AxiosPromise} from "axios";
import {ResponsePack} from "@/models/ResponsePack";
import {userService} from "@/services/UserService";

export const httpClient = axios.create({
    baseURL: 'http://127.0.0.1:8080',
    timeout: 1000,
    headers: {
        satoken: localStorage.getItem("sharine-token") || ""
    }
});
export function pack(axiosPromise: AxiosPromise<any>): Promise<ResponsePack> {
    return axiosPromise.then(function (response) {
        return ResponsePack.of(response)
    }).catch(function (error) {
        console.log(error);
        return ResponsePack.ofEmpty()
    });
}