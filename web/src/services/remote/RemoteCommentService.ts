import {RemoteService} from "@/services/remote/RemoteService";
import {VideoComment} from "@/models/VideoComment";
import {pack} from "@/utilities/HttpUtility";

class RemoteCommentService extends RemoteService {
    async comment(comment: VideoComment) {
        return pack(this.serviceClient.post("",comment))
    }
    async getVideoComments(videoId: Number) {
        return pack(this.serviceClient.get("",{
            params: {
                videoId: videoId
            }
        }))
    }
    async delVideoComments(videoId: Number) {
        return pack(this.serviceClient.delete("",{
            params: {
                videoId: videoId
            }
        }))
    }
}
export const remoteCommentService = new RemoteCommentService("/comments")