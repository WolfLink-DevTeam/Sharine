import {pack} from "@/utilities/HttpUtility";
import {Category} from "@/models/Category";
import {RemoteService} from "@/services/remote/RemoteService";

class RemoteCategoryService extends RemoteService {
    /**
     * 获取全部分类数据
     */
    async findAll(): Promise<Category[]> {
        return pack(this.serviceClient.get("/all"))
            .then(resultPack => resultPack.data.map((it: any) => it as Category))
    }
}
export const remoteCategoryService = new RemoteCategoryService("/categories")