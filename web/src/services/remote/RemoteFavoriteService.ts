import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteFavoriteService extends RemoteService {
    async favorite(videoId: Number) {
        return pack(this.serviceClient.get("/"+videoId))
    }
    async hasFavorite(videoId: Number) {
        return pack(this.serviceClient.get("/"+videoId))
    }
    async undoFavorite(videoId: Number) {
        return pack(this.serviceClient.get("/"+videoId))
    }
    async getUserFavoriteVideoIds() {
        return pack(this.serviceClient.get(""))
    }
}
export const remoteFavoriteService = new RemoteFavoriteService("/favorite")