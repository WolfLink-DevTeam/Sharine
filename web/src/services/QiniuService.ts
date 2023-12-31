import {httpClient, pack} from "@/utilities/HttpUtility";
export class QiniuService {

    async getQiniuUploadInfo(fileName: string) {
        return pack(httpClient.get("/qiniu/generateToken/"+fileName))
    }
    async getDownloadUrl(fileKey: string) {
        return pack(httpClient.get("/qiniu/downloadUrl/"+fileKey))
    }
    getVideoFrameUrl(videoUrl: string,frameId: number) {
        const name = videoUrl.substring(0,videoUrl.lastIndexOf('.'))
        return name+"frame00000"+frameId+".jpg"
    }
}
export const qiniuService = new QiniuService()