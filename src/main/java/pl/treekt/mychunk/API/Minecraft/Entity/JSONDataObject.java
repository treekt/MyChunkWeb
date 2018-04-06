package pl.treekt.mychunk.API.Minecraft.Entity;

public class JSONDataObject {
     private String url;
     private String username;
     private String password;
     private String salt;

     public JSONDataObject(String url, String username, String password, String salt){
         this.url = url;
         this.username = username;
         this.password = password;
         this.salt = salt;
     }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
