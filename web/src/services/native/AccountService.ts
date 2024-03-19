import {useSystemStore} from "@/store/system";
import {User} from "@/models/User";
import {ref, Ref} from "vue";
import {cookieService} from "@/services/native/CookieService";
import {remoteUserActionService} from "@/services/remote/RemoteUserActionService";
import {passwordEncryption} from "@/utilities/ResourceUtility";

export class AccountService {

    user: Ref<User> = ref(new User())

    logout() {
        cookieService.saveLocalUser(null)
        cookieService.saveEncrypPassword("")
        useSystemStore().logout()
    }
    saveInfo(account: string,password: string) {
        cookieService.saveAccount(account)
        cookieService.saveEncrypPassword(passwordEncryption(password))
    }
    login(): Promise<boolean> {
        return remoteUserActionService.login(cookieService.getAccount(),cookieService.getEncrypPassword()).then(pack => {
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