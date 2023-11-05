import {userService} from "@/services/UserService";
import {useSystemStore} from "@/store/system";
import {User} from "@/models/User";

export class AccountService {

    logout() {
        userService.saveLocalUser(null)
        userService.savePassword("")
        useSystemStore().logout()
    }
    saveInfo(account: string,password: string) {
        userService.saveAccount(account)
        userService.savePassword(password)
    }
    login(): Promise<boolean> {
        return userService.login().then(pack => {
            if(pack.code === 0) {
                const userSimpleVO = pack.data.userSimpleVO
                const user = new User()
                user.id = userSimpleVO.id
                user.nickname = userSimpleVO.nickname
                user.avatar = userSimpleVO.avatar
                user.content = userSimpleVO.content
                user.followedCount = userSimpleVO.followedCount
                user.followingCount = userSimpleVO.followingCount
                user.favoriteCount = userSimpleVO.favoriteCount
                user.bookmarkCount = userSimpleVO.bookmarkCount
                user.createTime = userSimpleVO.createTime
                user.updateTime = userSimpleVO.updateTime
                userService.saveLocalUser(user)
                userService.saveToken(pack.data.token)
                useSystemStore().login()
                return true;
            }
            else {
                alert(pack.msg)
                return false;
            }
        }).catch(error => {
            return false;
        });
    }
}
export const accountService = new AccountService()