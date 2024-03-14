import {Video} from "@/models/Video";
import {httpClient, pack} from "@/utilities/HttpUtility";
import {Category} from "@/models/Category";
import {RemoteService} from "@/services/remote/RemoteService";

class RemoteVideoService extends RemoteService {

    async getVideo(videoId?: Number,userId?: Number,page?: Number,size?: Number): Promise<Video[]> {
        return pack(httpClient.get("",{
            params: {
                videoId: videoId,
                userId: userId,
                page: page,
                size: size
            }
        })).then(resultPack => resultPack.data.map((it: any) => it as Video))
    }
    async delVideo(videoId: Number) {
        return pack(httpClient.delete("",{
            params: {
                videoId: videoId
            }
        }))
    }
    async putVideo(videoId: Number,title?: String,content?: String,coverUrl?: String) {
        return pack(httpClient.put("",{
            params: {
                videoId: videoId,
                title: title,
                content: content,
                coverUrl: coverUrl
            }
        }))
    }
}
export const remoteVideoService = new RemoteVideoService("/videos")