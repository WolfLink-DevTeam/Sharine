import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteBookmarkService extends RemoteService {
    async post(videoId: Number) {
        return pack(this.serviceClient.post("",{},{
            params: {
                videoId: videoId
            }
        }))
    }
    async get(videoId: Number) {
        return pack(this.serviceClient.get("",{
            params: {
                videoId: videoId
            }
        }))
    }
    async del(videoId: Number) {
        return pack(this.serviceClient.delete("",{
            params: {
                videoId: videoId
            }
        }))
    }
}
export const remoteBookmarkService = new RemoteBookmarkService("/bookmarks")