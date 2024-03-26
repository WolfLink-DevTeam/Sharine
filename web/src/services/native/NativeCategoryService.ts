import {Category} from "@/models/Category";
import {remoteCategoryService} from "@/services/remote/RemoteCategoryService";
import {ref, Ref} from "vue";
import {CategoryFrequency} from "@/models/CategoryFrequency";
import {VideoVO} from "@/models/VideoVO";

class NativeCategoryService {
    list: Ref<Array<Category>> = ref(new Array<Category>())
    constructor() {
        this.updateCategories().then(_ => setTimeout(this.updateCategories,3000))
    }
    private async updateCategories() {
        this.list.value = await remoteCategoryService.findAll()
    }
    findCategoryByIndex(index: number): Category {
        return this.list.value[index]
    }
    findCategoryById(id: number): Category|null {
        let category: Category|null = null
        this.list.value.forEach(tempC => {
            if(tempC.id === id) {
                category = tempC
            }
            if(category !== null) return
        })
        return category;
    }
    viewCountMap: Ref<Map<number,number>> = this.loadViewCountMap()

    saveViewCountMap() {
        setTimeout(()=>this.saveViewCountMap(),1000 * 60)
        const obj = Object.fromEntries(this.viewCountMap.value.entries());
        localStorage.setItem('viewCountMap', JSON.stringify(obj));
    }
    loadViewCountMap() {
        setTimeout(()=>this.saveViewCountMap(),1000 * 60)
        const objStr = localStorage.getItem('viewCountMap');
        if (objStr) {
            const obj = JSON.parse(objStr);
            const map = new Map<number, number>(Object.entries(obj).map((k, v) => [Number(k), v]));
            return ref(map);
        } else {
            return ref(new Map());
        }
    }

    hasViewed(video: VideoVO) {
        const v = this.viewCountMap.value.get(video.category.id) || 0;
        this.viewCountMap.value.set(video.category.id,v+1)
    }

    topFiveHotCategories(videos: Array<VideoVO>): Array<CategoryFrequency> {
        const categoryCount: Map<number, number> = new Map();
        videos.forEach(video => {
            if(video.category.id === 1) return
            const count = categoryCount.get(video.category.id) || 0;
            categoryCount.set(video.category.id, count + 1);
        });
        const sortedCategories: { categoryId: number; count: number }[]
            = Array.from(categoryCount, ([categoryId, count]) => ({ categoryId, count }))
            .sort((a, b) => b.count - a.count);
        const total = videos.length;
        const topFive: Array<CategoryFrequency> = sortedCategories.slice(0, 5)
            .map(({ categoryId, count }) => {
                return new CategoryFrequency(categoryId,count/total)
            });
        const sumOfTopFive = topFive.reduce((sum, { frequency }) => sum + frequency, 0);
        topFive.forEach(cf => cf.frequency /= sumOfTopFive);
        return topFive;
    }
    topFiveDailyCategories(): Array<CategoryFrequency> {
        const entries = Array.from(this.viewCountMap.value.entries()).filter(([k, v]) => !isNaN(k) && !isNaN(v));
        // 对数组进行排序，根据值（数组的第二个元素）进行降序排序
        const sortedEntries: [number, number][] = entries.sort((a, b) => b[1] - a[1]);
        // 获取前五个元素并转换为 CategoryFrequency 对象
        const temp = sortedEntries.slice(0, 5).map(([k, v]) => new CategoryFrequency(k, v));
        const total = temp.reduce((sum,{frequency}) => sum + frequency,0)
        temp.forEach(cf => cf.frequency /= total)
        return temp;
    }
}
export const nativeCategoryService = new NativeCategoryService()