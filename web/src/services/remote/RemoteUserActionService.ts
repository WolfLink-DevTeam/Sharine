import {RemoteService} from "@/services/remote/RemoteService";
import {pack} from "@/utilities/HttpUtility";

class RemoteUserActionService extends RemoteService {
    async login(email: String,encryptPassword: String) {
        return pack(this.serviceClient.post("/login", {}, {
            params: {
                email: email,
                encryptPassword: encryptPassword
            }
        }))
    }
    async register(email: String,encryptPassword: String,emailCode: String) {
        return pack(this.serviceClient.post("/register", {}, {
            params: {
                email: email,
                encryptPassword: encryptPassword,
                emailCode: emailCode
            }
        }))
    }
    async changePassword(encryptPassword: String,verificationCode: String) {
        return pack(this.serviceClient.post("/changePassword",{},{
            params: {
                encryptPassword: encryptPassword,
                verificationCode: verificationCode
            }
        }))
    }
    async requestEmailCode(email: String) {
        return pack(this.serviceClient.post("/requestEmailCode",{},{
            params: {
                email: email
            }
        }))
    }
}
export const remoteUserActionService = new RemoteUserActionService("/user-actions")