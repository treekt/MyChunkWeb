package pl.treekt.mychunk.API.Payments;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.treekt.mychunk.API.Payments.Entity.SmsResponse;
import pl.treekt.mychunk.API.Payments.Entity.SmsRequest;

import java.io.IOException;

@Component
public class SMSPaymentManager {

    private RestTemplate restTemplate = new RestTemplate();

    private final String url = "https://homepay.pl/api";


    public SmsResponse checkSMS(String code) {
        SmsRequest smsRequest = new SmsRequest();
        smsRequest.setAccount(231);
        smsRequest.setCode(code);


        HttpEntity<SmsRequest> entity = new HttpEntity<SmsRequest>(smsRequest);
        String resultJson = restTemplate.postForObject(url, entity, String.class);

        SmsResponse smsResponse = convertJsonToCheckResult(resultJson);

        return smsResponse;
    }


    //Private methods

    private SmsResponse convertJsonToCheckResult(String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(json, SmsResponse.class);

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }



}
