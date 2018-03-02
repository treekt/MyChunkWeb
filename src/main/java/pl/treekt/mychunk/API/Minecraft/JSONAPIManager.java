package pl.treekt.mychunk.API.Minecraft;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.treekt.mychunk.API.Minecraft.Entity.MinecraftRequest;
import pl.treekt.mychunk.API.Minecraft.Entity.MinecraftResponse;

import java.io.IOException;

@Component
public class JSONAPIManager {

    private RestTemplate restTemplate = new RestTemplate();

    private String host = "localhost";
    private int port = 20059;
    private String username = "admin";
    private String password = "changeme";
    private String salt = "saltgoeshere";

    private String url = "http://" + host + ":" + port + "/api/2/call";



    public MinecraftResponse call(String method, String[] args) {


        MinecraftRequest minecraftRequest = new MinecraftRequest(method,
                makeKey(method, username, password, salt),
                username,
                args,
                method);

        HttpEntity<MinecraftRequest> entity = new HttpEntity<MinecraftRequest>(minecraftRequest);
        String resultJson = restTemplate.postForObject(url, entity, String.class);

        MinecraftResponse minecraftResponse = convertJsonToMinecraftResponse(cutTheBrackets(resultJson));

        return minecraftResponse;
    }



    private String makeKey(String method, String username, String password, String salt) {
        String key = Hashing.sha256()
                .hashString(username + method + password + salt, Charsets.UTF_8)
                .toString();
        return key;
    }

    private MinecraftResponse convertJsonToMinecraftResponse(String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(json, MinecraftResponse.class);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String cutTheBrackets(String json){
        return json.substring(1, json.length()-1);
    }

}
