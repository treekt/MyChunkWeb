package pl.treekt.mychunk.API.Bungee;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;


@Component
public class BungeeManager {

    @Value("${bungee.url}")
    private String bungeeUrl;

    public JsonNode call(String endpoint, HashMap<String, String> params){
        RestTemplate restTemplate = new RestTemplate();
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(bungeeUrl + endpoint);
        params.forEach((key, value) -> builder.queryParam(key, value));

        JsonNode map;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity =
                new HttpEntity<>(headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(
                builder.build().encode().toUri(),
                HttpMethod.POST,
                requestEntity,
                JsonNode.class);


        map = responseEntity.getBody();
        return map;
    }
}
