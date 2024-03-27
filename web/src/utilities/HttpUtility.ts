import axios, {AxiosPromise} from "axios";
import {ResponsePack} from "@/models/ResponsePack";

export function pack(axiosPromise: AxiosPromise<any>): Promise<ResponsePack> {
    return axiosPromise.then(function (response : any) {
        return ResponsePack.of(response)
    }).catch(function (error: any) {
        console.log(error);
        return ResponsePack.ofEmpty()
    });
}