import {userService} from "@/services/UserService";
import {useSystemStore} from "@/store/system";
import {User} from "@/models/User";
import {ref, Ref} from "vue";
import {cookieService} from "@/services/native/CookieService";

export class AccountService {

    user: Ref<User> = ref(new User())

    logout() {
        cookieService.saveLocalUser(null)
        cookieService.savePassword("")
        useSystemStore().logout()
    }
    saveInfo(account: string,password: string) {
        cookieService.saveAccount(account)
        cookieService.savePassword(password)
    }
    login(): Promise<boolean> {
        return userService.login().then(pack => {
            if(pack.code === 0) {
                const userSimpleVO = pack.data.userSimpleVO
                const user = userSimpleVO as User
                cookieService.saveLocalUser(user)
                cookieService.saveToken(pack.data.token)
                useSystemStore().login()
                return true;
            }
            else {
                if(pack.msg.length !== 0) alert(pack.msg)
                return false;
            }
        }).catch(() => {
            return false;
        });
    }
}
export const accountService = new AccountService();