package pl.treekt.mychunk.Payments.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckSms {

    //Homepay user ID
    @JsonProperty("id")
    private int id;
    //API command
    @JsonProperty("command")
    private String command;
    //Homepay user API key
    @JsonProperty("key")
    private String key;
    //Homepay user password
    @JsonProperty("password")
    private String password;
    //Queried account ID
    @JsonProperty("account")
    private int account;
    //Queried code
    @JsonProperty("code")
    private String code;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
