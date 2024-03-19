import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteContentService extends RemoteService {
    async getUploadInfo(fileName: String) {
        return pack(this.serviceClient.get("/uploadInfo",{
            params: {
                fileName: fileName
            }
        }))
    }
    async getDownloadInfo(fileKey: String) {
        return pack(this.serviceClient.get("/downloadInfo",{
            params: {
                fileKey: fileKey
            }
        }))
    }
}
export const remoteContentService = new RemoteContentService("/oss")