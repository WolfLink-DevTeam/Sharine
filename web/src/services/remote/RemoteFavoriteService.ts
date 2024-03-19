import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteFavoriteService extends RemoteService {
    async post(videoId: Number) {
        return pack(this.serviceClient.post("/"+videoId))
    }
    async get(videoId: Number) {
        return pack(this.serviceClient.get("/"+videoId))
    }
    async del(videoId: Number) {
        return pack(this.serviceClient.delete("/"+videoId))
    }
}
export const remoteFavoriteService = new RemoteFavoriteService("/favorite")