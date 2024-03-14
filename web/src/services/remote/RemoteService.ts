import {AxiosInstance} from "axios";
import axios from "axios/index";

export class RemoteService {
    serviceClient: AxiosInstance
    constructor(prefix: String) {
        this.serviceClient = axios.create({
            baseURL: import.meta.env.VITE_SHARINE_SERVER_URL+prefix,
            timeout: 10000
        });
    }
}