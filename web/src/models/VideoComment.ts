import {User} from "@/models/User";

export class VideoComment {
    id: number = 0
    author: User = new User()
    replyId: number = 0
    videoId: number = 0
    content: string = ""
    createTime: Date = new Date()
    updateTime: Date = new Date()
}