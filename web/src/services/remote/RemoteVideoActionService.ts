import {Video} from "@/models/Video";
import {httpClient, pack} from "@/utilities/HttpUtility";
import {RemoteService} from "@/services/remote/RemoteService";

class RemoteVideoActionService extends RemoteService {
    async signature(fileKey: String,hash: String,video: Video) {
        return pack(httpClient.post("/signature",video,{
            params: {
                fileKey: fileKey,
                hash: hash
            }
        }))
    }
}
export const remoteVideoActionService = new RemoteVideoActionService("/video-actions")