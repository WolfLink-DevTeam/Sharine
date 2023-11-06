export function getImageUrl(imgName: string|null) {
    return imgName?.replace("@", "src") || ""
}
export function timeStrParse(time: number): Date {
    const timeStr = String(time)
    const year = parseInt(timeStr.slice(0, 4), 10)
    const month = parseInt(timeStr.slice(4, 6), 10) - 1
    const day = parseInt(timeStr.slice(6, 8), 10)
    const hour = parseInt(timeStr.slice(8, 10), 10)
    const minute = parseInt(timeStr.slice(10, 12), 10)
    const second = parseInt(timeStr.slice(12, 14), 10)
    return new Date(year,month,day,hour,minute,second)
}
export function dateFormat(date: Date): string {
    const year = date.getFullYear()
    const month = (date.getMonth() + 1).toString().padStart(2, '0')
    const day = date.getDate().toString().padStart(2, '0')
    const hour = date.getHours().toString().padStart(2, '0')
    const minute = date.getMinutes().toString().padStart(2, '0')
    const second = date.getSeconds().toString().padStart(2, '0')
    return `${year}-${month}-${day} ${hour}:${minute}:${second}`
}
export function simpleDateFormat(date: Date): string {
    const year = date.getFullYear()
    const month = (date.getMonth() + 1).toString().padStart(2, '0')
    const day = date.getDate().toString().padStart(2, '0')
    return `${year}-${month}-${day}`
}