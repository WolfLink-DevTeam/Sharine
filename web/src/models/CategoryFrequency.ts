import {Category} from "@/models/Category";
import {nativeCategoryService} from "@/services/native/NativeCategoryService";

export class CategoryFrequency {
    category: Category|null
    frequency: number
    constructor(categoryId: number,frequency: number) {
        this.category = nativeCategoryService.findCategoryById(categoryId)
        this.frequency = frequency
    }
}