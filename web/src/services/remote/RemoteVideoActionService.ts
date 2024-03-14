import {Video} from "@/models/Video";
import {httpClient, pack} from "@/utilities/HttpUtility";
import {RemoteService} from "@/services/remote/RemoteService";

class RemoteVideoActionService extends RemoteService {
    async signature(fileKey: String,hash: String,categoryId: Number,video: Video) {
        return pack(httpClient.post("/signature",video,{
            params: {
                fileKey: fileKey,
                hash: hash,
                categoryId: categoryId
            }
        }))
    }
}
export const remoteVideoActionService = new RemoteVideoActionService("/video-actions")