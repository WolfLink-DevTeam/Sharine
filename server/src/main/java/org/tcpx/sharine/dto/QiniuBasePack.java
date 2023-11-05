package org.tcpx.sharine.dto;

import com.google.gson.JsonObject;
import lombok.Data;

@Data
public class QiniuBasePack {
    private final JsonObject data = new JsonObject();
    private final JsonObject params = new JsonObject();

    public JsonObject toJsonObject() {
        JsonObject result = new JsonObject();
        result.add("data", data);
        result.add("params", params);
        return result;
    }
}
