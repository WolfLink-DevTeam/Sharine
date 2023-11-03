export class User {
    id: number = -1
    nickname: string = ""
    avatar: string = ""
    content: string = ""
    followingCount: number = 0
    followedCount: number = 0
    favoriteCount: number = 0
    bookmarkCount: number = 0
    createTime: Date = new Date()
    updateTime: Date = new Date()
}