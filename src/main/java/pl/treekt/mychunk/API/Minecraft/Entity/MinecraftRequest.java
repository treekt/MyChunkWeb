package pl.treekt.mychunk.API.Minecraft.Entity;

public class MinecraftRequest {

    private String name;
    private String key;
    private String username;
    private String[] arguments;
    private String tag;


    public MinecraftRequest(String name, String key, String username, String[] arguments, String tag){
        this.name = name;
        this.key = key;
        this.username = username;
        this.arguments = arguments;
        this.tag = tag;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String[] getArguments() {
        return arguments;
    }

    public void setArguments(String[] arguments) {
        this.arguments = arguments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
