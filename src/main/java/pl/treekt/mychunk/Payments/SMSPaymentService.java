package pl.treekt.mychunk.Payments;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.annotations.Check;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import pl.treekt.mychunk.Payments.Model.CheckResult;
import pl.treekt.mychunk.Payments.Model.CheckSms;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class SMSPaymentService {

    private RestTemplate restTemplate = new RestTemplate();

    public CheckResult checkSMS(int account, String code){
        CheckSms checkSms = new CheckSms();
//        checkSms.setId(13822);
//        checkSms.setCommand("CheckSMS");
//        checkSms.setKey("key");
//        checkSms.setPassword("password");
//        checkSms.setAccount(account);
//        checkSms.setCode(code);

        String url = "https://homepay.pl/api";

        HttpEntity<CheckSms> entity = new HttpEntity<CheckSms>(checkSms);
        String resultJson = restTemplate.postForObject(url, entity, String.class);

        return convertJsonToCheckResult(resultJson);
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
