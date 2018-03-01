package pl.treekt.mychunk.API.Minecraft;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import pl.treekt.mychunk.Entity.API.CheckResult;
import pl.treekt.mychunk.Entity.API.CheckSms;
import pl.treekt.mychunk.Entity.API.MinecraftRequest;

public class JSONAPI {

    private RestTemplate restTemplate = new RestTemplate();

    private String host;
    private int port;
    private String username;
    private String password;
    private String salt;

    private String url;

    public JSONAPI(String host, int port, String username, String password, String salt){
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.salt = salt;

        this.url = "http://" + host + ":" + port + "/api/call/";
    }

    public String makeKey(String method, String username, String password, String salt){
        String key = Hashing.sha256()
                .hashString(username + method + password + salt, Charsets.UTF_8)
                .toString();
        return key;
    }

    public void call(String method, String[] args){

        MinecraftRequest minecraftRequest = new MinecraftRequest(method, makeKey(method, username, password, salt), args, method);


        HttpEntity<MinecraftRequest> entity = new HttpEntity<CheckSms>(minecraftRequest);
        String resultJson = restTemplate.postForObject(url, entity, String.class);

        CheckResult checkResult = convertJsonToCheckResult(resultJson);

    }
}
