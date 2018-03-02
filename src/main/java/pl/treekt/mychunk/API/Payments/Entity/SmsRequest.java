package pl.treekt.mychunk.API.Payments.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Value;

public class SmsRequest {

    @JsonProperty("id")
    @Value("${homepay.api.id}")
    private int id;

    @JsonProperty("command")
    private String command = "CheckSMS";

    @JsonProperty("key")
    @Value("${homepay.api.key}")
    private String key;

    @JsonProperty("password")
    @Value("${homepay.api.password}")
    private String password;

    @JsonProperty("account")
    private int account;

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
