package pl.treekt.mychunk.API.Minecraft.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class MinecraftResponse {

    private String result;
    @JsonProperty("is_success")
    private boolean success;
    private String source;
    private String tag;
    @JsonProperty("success")
    private Object successData;


    public MinecraftResponse(String result, boolean success, String source, String tag, Object successData){
        this.result = result;
        this.success = success;
        this.source = source;
        this.tag = tag;
        this.successData = successData;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Object getSuccessData() {
        return successData;
    }

    public void setSuccessData(Object successData) {
        this.successData = successData;
    }

}
