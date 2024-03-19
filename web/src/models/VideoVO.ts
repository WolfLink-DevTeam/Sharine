import {VideoType} from "./VideoType";
import {VideoComment} from "./VideoComment";
import {User} from "@/models/User";
import {Category} from "@/models/Category";

export class VideoVO {
    id: number = 0
    author: User = new User()
    category: Category = new Category()
    title: string = ""
    content: string = ""
    url: string = ""
    coverUrl: string = ""
    type: VideoType = VideoType.UNKNOWN
    comments: Array<VideoComment> = new Array<VideoComment>()
    bookmarkCount: number = 0
    favoriteCount: number = 0
    viewCount: number = 0
    createTime: Date = new Date()
    updateTime: Date = new Date()
}