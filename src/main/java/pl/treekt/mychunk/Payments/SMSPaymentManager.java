package pl.treekt.mychunk.Payments;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.treekt.mychunk.Payments.Model.CheckResult;
import pl.treekt.mychunk.Payments.Model.CheckSms;

import java.io.IOException;

@Component
public class SMSPaymentManager {

    private RestTemplate restTemplate = new RestTemplate();

    private final String url = "https://homepay.pl/api";

    public boolean checkSMS(String code){
        CheckSms checkSms = new CheckSms();
        checkSms.setAccount(231);
        checkSms.setCode(code);


        HttpEntity<CheckSms> entity = new HttpEntity<CheckSms>(checkSms);
        String resultJson = restTemplate.postForObject(url, entity, String.class);

        CheckResult checkResult = convertJsonToCheckResult(resultJson);

        if(checkResult.getCode() == 1){
            return true;
        }

        return false;
    }



    private CheckResult convertJsonToCheckResult(String json){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(json, CheckResult.class);

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
