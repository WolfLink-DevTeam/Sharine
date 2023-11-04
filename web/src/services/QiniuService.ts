import {httpClient, pack} from "@/utilities/HttpUtility";
import {userService} from "@/services/UserService";
export class QiniuService {

    async getQiniuUploadInfo(fileName: string) {
        return pack(httpClient.post("/qiniu/generateToken",{
            userPass: {
                account: userService.getAccount(),
                password: userService.getPassword()
            },
            rawFileName: fileName
        }))
    }

}
export const qiniuService = new QiniuService()