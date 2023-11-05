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
                const user = userService.parseUserVO(userSimpleVO)
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