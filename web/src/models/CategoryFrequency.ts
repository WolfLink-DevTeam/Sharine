import {Category} from "@/models/Category";
import {categoryService} from "@/services/CategoryService";

export class CategoryFrequency {
    category: Category|null
    frequency: number
    constructor(categoryId: number,frequency: number) {
        this.category = categoryService.getCategoryById(categoryId)
        this.frequency = frequency
    }
}