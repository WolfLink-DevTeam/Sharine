import {Ref, ref} from "vue";
import {ResponsePack} from "@/models/ResponsePack";
import {httpClient, pack} from "@/utilities/HttpUtility";
import {User} from "@/models/User";


export class UserService {

    saveLocalUser(user: User | null) {
        if(user !== null) {
            localStorage.setItem("sharine-local-user",JSON.stringify(user))
        } else localStorage.removeItem("sharine-local-user")
    }
    getLocalUser(): User | null {
        const str = localStorage.getItem("sharine-local-user")
        if(str === null) return null
        else return JSON.parse(str)
    }

    user: Ref<User> = ref(new User())

    getToken() {
        return localStorage.getItem("sharine-token") || ""
    }
    saveToken(token: string) {
        localStorage.setItem("sharine-token",token)
    }
    getAccount() {
        return localStorage.getItem("sharine-account") || ""
    }
    saveAccount(account: string) {
        localStorage.setItem("sharine-account",account)
    }
    savePassword(password: string) {
        localStorage.setItem("sharine-password",password)
    }
    getPassword() {
        return localStorage.getItem("sharine-password") || ""
    }

    async askForEmailVerification(): Promise<ResponsePack> {
        return pack(httpClient.post("/users/sendCode", {
            account: this.getAccount()
        }))
    }
    async login(): Promise<ResponsePack> {
        return pack(httpClient.post("/users/login", {
            account: this.getAccount(),
            password: this.getPassword()
        }))
    }
    async register(verificationCode: number): Promise<ResponsePack> {
        return pack(httpClient.post("/users/register", {
            account: this.getAccount(),
            password: this.getPassword(),
            verificationCode: verificationCode
        }))
    }
    async changePassword(newPassword: string,verificationCode: number): Promise<ResponsePack> {
        return pack(httpClient.post("/users/changePassword", {
            account: this.getAccount(),
            password: newPassword,
            verificationCode: verificationCode
        }))
    }
    async findUserDetailInfo(userId: number): Promise<ResponsePack> {
        return pack(httpClient.get("/users/"+userId))
    }
    async favorite(videoId: number) {
        return pack(httpClient.post("/users/favorite/"+videoId,{
            account: this.getAccount(),
            password: this.getPassword()
        }))
    }
    async undoFavorite(videoId: number) {
        return pack(httpClient.delete("/users/favorite/"+videoId,{
            data: {
                account: this.getAccount(),
                password: this.getPassword()
            }
        }))
    }
    async bookmark(videoId: number) {
        return pack(httpClient.post("/users/bookmark/"+videoId,{
            account: this.getAccount(),
            password: this.getPassword()
        }))
    }
    async undoBookmark(videoId: number) {
        return pack(httpClient.delete("/users/bookmark/"+videoId,{
            data: {
                account: this.getAccount(),
                password: this.getPassword()
            }
        }))
    }
    async getUserFavoriteVideos() {
        return pack(httpClient.get("/users/favorite/videos",{
            params: {
                account: this.getAccount(),
                password: this.getPassword()
            }
        }))
    }
    async getUserBookmarkVideos() {
        return pack(httpClient.get("/users/bookmark/videos",{
            params: {
                account: this.getAccount(),
                password: this.getPassword()
            }
        }))
    }
    async getUserSubscribeVideos() {
        return pack(httpClient.get("/users/subscribe/videos",{
            params: {
                account: this.getAccount(),
                password: this.getPassword()
            }
        }))
    }
    async getUserUploadVideos(userId: number) {
        return pack(httpClient.get("/users/upload/videos/"+userId))
    }
    async getSubscribeVideos() {
        return pack(httpClient.get("/users/subscribe/videos",{
            params: {
                account: this.getAccount(),
                password: this.getPassword()
            }
        }))
    }
}
export const userService = new UserService()