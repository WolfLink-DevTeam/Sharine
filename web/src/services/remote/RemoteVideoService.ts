import {VideoVO} from "@/models/VideoVO";
import {pack} from "@/utilities/HttpUtility";
import {Category} from "@/models/Category";
import {RemoteService} from "@/services/remote/RemoteService";

class RemoteVideoService extends RemoteService {

    async getVideo(videoId?: Number,userId?: Number,page?: Number,size?: Number): Promise<VideoVO[]> {
        return pack(this.serviceClient.get("",{
            params: {
                videoId: videoId,
                userId: userId,
                page: page,
                size: size
            }
        })).then(resultPack => resultPack.data.map((it: any) => it as VideoVO))
    }
    async delVideo(videoId: Number) {
        return pack(this.serviceClient.delete("",{
            params: {
                videoId: videoId
            }
        }))
    }
    async putVideo(video: VideoVO) {
        return pack(this.serviceClient.put("",{
            params: {
                video: video
            }
        }))
    }
}
export const remoteVideoService = new RemoteVideoService("/videos")