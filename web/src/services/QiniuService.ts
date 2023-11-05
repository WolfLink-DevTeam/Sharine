import {httpClient, pack} from "@/utilities/HttpUtility";
import {userService} from "@/services/UserService";
import axios from "axios";
export class QiniuService {

    async getQiniuUploadInfo(fileName: string) {
        return pack(httpClient.post("/qiniu/generateToken",{
            userPass: {
                account: userService.getAccount(),
                password: userService.getPassword()
            },
            rawFileName: fileName
        }))
    }
    async getDownloadUrl(fileKey: string) {
        return pack(httpClient.get("/qiniu/downloadUrl/"+fileKey))
    }
    getVideoFrameUrl(videoUrl: string,frameId: number) {
        const name = videoUrl.substring(0,videoUrl.lastIndexOf('.'))
        return name+"frame"+frameId+".jpg"
    }
}
export const qiniuService = new QiniuService()