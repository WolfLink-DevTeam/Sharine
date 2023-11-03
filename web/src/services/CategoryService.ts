import {Category} from "@/models/Category";
import {httpClient, pack} from "@/utilities/HttpUtility";
import {ResponsePack} from "@/models/ResponsePack";

export class CategoryService {

    list: Array<Category> = new Array<Category>()

    constructor() {
        this.getAllCategories().then(result => {
                this.list = result.data.map((it: any) => {
                    const category = new Category()
                    category.id = it.id
                    category.title = it.title
                    category.url = it.url
                    return category
                })
            })
    }

    /**
     * 获取全部分类数据
     */
    async getAllCategories(): Promise<ResponsePack> {
        return pack(httpClient.get("/categories/all"))
    }
}
export const categoryService = new CategoryService()