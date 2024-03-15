import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteViewVideoService extends RemoteService {

    async viewVideo(videoId: Number) {
        return pack(this.serviceClient.post("",{},{
            params:{
                videoId: videoId
            }
        }))
    }
}
export const remoteViewVideoService = new RemoteViewVideoService("/view-video")