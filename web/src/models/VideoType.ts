export enum VideoType {
    ORIGINAL = 1,
    REPRINT = 2,
    UNKNOWN = 3
}
const videoTypeNames = {
    [VideoType.ORIGINAL]: "原创",
    [VideoType.REPRINT]: "转载",
    [VideoType.UNKNOWN]: "未知类型"
};

export function VideoTypeChineseName(videoType: VideoType) {
    return videoTypeNames[videoType] || "未知类型";
}