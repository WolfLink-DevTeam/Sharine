import {ResponsePack} from "@/models/ResponsePack";
import {httpClient, pack} from "@/utilities/HttpUtility";
import {Video} from "@/models/Video";
import {timeStrParse} from "@/utilities/ResourceUtility";
import {User} from "@/models/User";
import {Category} from "@/models/Category";
import {VideoComment} from "@/models/VideoComment";
import {userService} from "@/services/UserService";
import {VideoType, VideoTypeChineseName} from "@/models/VideoType";

export class VideoService {
    async uploadVideo(fileKey: string,hash: string,categoryId: number,title: string,content: string,url: string,coverUrl: string,videoType: VideoType) {
        return pack(httpClient.post("/videos/verify",{
            userPass: {
                account: userService.getAccount(),
                password: userService.getPassword()
            },
            fileKey: fileKey,
            hash: hash,
            categoryId: categoryId,
            title: title,
            content: content,
            url: url,
            coverUrl: coverUrl,
            videoType: videoType
        }))
    }
    async getVideos(current: number,size: number): Promise<ResponsePack> {
        return pack(httpClient.get("/videos", {
            params: {
                current: current,
                size: size
            }
        }))
    }
    async addComment(comment: VideoComment) {
        return pack(httpClient.put("/videos/"+comment.videoId+"/comments",{
            userPass: {
                account: userService.getAccount(),
                password: userService.getPassword()
            },
            replyId: comment.replyId,
            videoId: comment.videoId,
            content: comment.content
        }))
    }
    async getVideo(videoId: number): Promise<ResponsePack> {
        return pack(httpClient.get("/videos/"+videoId))
    }
    async getVideoComments(videoId: number): Promise<ResponsePack> {
        return pack(httpClient.get("videos/"+videoId+"/comments"))
    }
    parseCommentVO(commentVO: any):VideoComment {
        console.log(commentVO)

        const comment = new VideoComment()

        comment.createTime = timeStrParse(commentVO.createTime)
        comment.updateTime = timeStrParse(commentVO.updateTime)
        comment.id = commentVO.id
        comment.replyId = commentVO.replyId
        comment.content = commentVO.content
        comment.videoId = commentVO.videoId
        const author = new User()
        author.id = commentVO.author.id
        author.nickname = commentVO.author.nickname
        author.avatar = commentVO.author.avatar
        author.content = commentVO.author.content
        comment.author = author
        return comment
    }
    parseVideoVO(videoVO: any):Video {

        const video = new Video()

        video.createTime = timeStrParse(videoVO.createTime)
        video.updateTime = timeStrParse(videoVO.updateTime)
        video.url = videoVO.url
        video.title = videoVO.title
        video.content = videoVO.content
        video.coverUrl = videoVO.coverUrl
        video.id = videoVO.id
        video.favoriteCount = videoVO.favoriteCount
        video.bookmarkCount = videoVO.bookmarkCount

        const author = new User()
        author.nickname = videoVO.author.nickname
        author.bookmarkCount = videoVO.author.bookmarkCount
        author.avatar = videoVO.author.avatar
        author.favoriteCount = videoVO.author.favoriteCount
        author.followingCount = videoVO.author.followingCount
        author.followedCount = videoVO.author.followedCount
        author.content = videoVO.author.content
        author.id = videoVO.author.id
        video.author = author

        const category = new Category()
        category.url = videoVO.category.url
        category.id = videoVO.category.id
        category.title = videoVO.category.title

        video.category = category
        return video
    }
}
export const videoService = new VideoService()