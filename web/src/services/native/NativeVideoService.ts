import {ref} from "vue";
import {VideoVO} from "@/models/VideoVO";

export class NativeVideoService {
    loadedVideos = ref(new Array<VideoVO>())
    findById(id: Number): VideoVO | undefined {
        return this.loadedVideos.value.find(it => it.id === id)
    }
}
export const nativeVideoService = new NativeVideoService()