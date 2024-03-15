import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteUserRelationService extends RemoteService {
    async follow(anotherUserId: Number) {
        return pack(this.serviceClient.post("/follow/"+anotherUserId))
    }
    async hasFollow(anotherUserId: Number) {
        return pack(this.serviceClient.get("/follow/"+anotherUserId))
    }
    async undoFollow(anotherUserId: Number) {
        return pack(this.serviceClient.delete("/follow/"+anotherUserId))
    }
}
export const remoteUserRelationService = new RemoteUserRelationService("/user-relation")