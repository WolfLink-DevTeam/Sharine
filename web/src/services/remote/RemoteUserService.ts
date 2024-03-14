import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteUserService extends RemoteService {
    async get(id?: Number,emailStr?: String) {
        return pack(this.serviceClient.get("",{
            params: {
                id: id,
                emailStr: emailStr
            }
        }))
    }
    async put(id: Number,nickname?: String,avatar?: String,content?: String) {
        return pack(this.serviceClient.put("",{},{
            params:{
                id: id,
                nickname: nickname,
                avatar: avatar,
                content: content
            }
        }))
    }
}
export const remoteUserService = new RemoteUserService("/users")