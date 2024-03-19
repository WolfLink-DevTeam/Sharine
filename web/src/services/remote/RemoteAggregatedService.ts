import {RemoteService} from "@/services/remote/RemoteService";
import {VideoVO} from "@/models/VideoVO";
import {pack} from "@/utilities/HttpUtility";

class RemoteAggregatedService extends RemoteService {
    async getSubscribeVideos(userId: Number): Promise<VideoVO[]> {
        return pack(this.serviceClient.get("/subscribe-videos/"+userId)).then(pack => {
            return pack.data.map((it: any) => it as VideoVO)
        })
    }
    async getFavoriteVideos(userId: Number): Promise<VideoVO[]> {
        return pack(this.serviceClient.get("/favorite-videos/"+userId)).then(pack => {
            return pack.data.map((it: any) => it as VideoVO)
        })
    }
    async getBookmarkVideos(userId: Number): Promise<VideoVO[]> {
        return pack(this.serviceClient.get("/bookmark-videos/"+userId)).then(pack => {
            return pack.data.map((it: any) => it as VideoVO)
        })
    }
}
export const remoteAggregatedService = new RemoteAggregatedService("/aggregated")