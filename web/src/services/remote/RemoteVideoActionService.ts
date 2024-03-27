import {pack} from "@/utilities/HttpUtility";
import {RemoteService} from "@/services/remote/RemoteService";
import {VideoDTO} from "@/models/VideoDTO";

class RemoteVideoActionService extends RemoteService {
    async signature(fileKey: String,hash: String,video: VideoDTO) {
        return pack(this.serviceClient.post("/signature",video,{
            params: {
                fileKey: fileKey,
                hash: hash
            }
        }))
    }
}
export const remoteVideoActionService = new RemoteVideoActionService("/video-actions")