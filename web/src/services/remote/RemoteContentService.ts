import {RemoteService} from "@/services/remote/RemoteService";

class RemoteContentService extends RemoteService {
    async getUploadInfo(fileName: String) {
        return this.serviceClient.get("/uploadInfo",{
            params: {
                fileName: fileName
            }
        })
    }
    async getDownloadInfo(fileKey: String) {
        return this.serviceClient.get("/downloadInfo",{
            params: {
                fileKey: fileKey
            }
        })
    }
}
export const remoteContentService = new RemoteContentService("/oss")