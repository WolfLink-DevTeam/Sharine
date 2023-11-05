import {AxiosResponse} from "axios";

export class ResponsePack {
    code: number = -1
    msg: string = ""
    data: any

    static of(response: AxiosResponse<any, any>): ResponsePack {
        const pack = new ResponsePack()
        pack.code = response.data.code
        pack.msg = response.data.msg
        pack.data = response.data.data
        return pack;
    }
    static ofEmpty(): ResponsePack {
        return new ResponsePack();
    }
}