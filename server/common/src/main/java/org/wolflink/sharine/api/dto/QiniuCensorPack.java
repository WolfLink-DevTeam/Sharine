package org.wolflink.sharine.api.dto;

import com.google.gson.JsonArray;

public class QiniuCensorPack extends QiniuBasePack {

    public QiniuCensorPack() {
        getParams().add("scenes", new JsonArray());
    }

    public JsonArray getScenes() {
        return getParams().getAsJsonArray("scenes");
    }
}
