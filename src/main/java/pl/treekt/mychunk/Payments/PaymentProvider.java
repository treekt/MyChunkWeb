package pl.treekt.mychunk.Payments;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import pl.treekt.mychunk.Payments.Model.CheckResult;
import pl.treekt.mychunk.Payments.Model.CheckSms;

public class PaymentProvider {

    public CheckResult checkSms(){
//        CheckSms checkSms = new CheckSms();
//        checkSms.setId(13822);
//        checkSms.setCommand("CheckSMS");
//        checkSms.setKey("key");
//        checkSms.setPassword("password");
//        checkSms.setAccount(0);
//        checkSms.setCode("code");

        ClientHttpRequestFactory requestFactory = getClientHttpRequestFactory();

        RestTemplate restTemplate = new RestTemplate(requestFactory);
        HttpEntity<CheckSms> request = new HttpEntity<CheckSms>(checkSms);
        ResponseEntity<CheckResult> response = restTemplate.exchange("https://homepay.pl/api", HttpMethod.POST, request, CheckResult.class);

        assertThat(response.getStatusCode(), is(HttpStatus.OK));

        CheckResult

        return null;
    }

    private ClientHttpRequestFactory getClientHttpRequestFactory() {
        int timeout = 5000;
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                = new HttpComponentsClientHttpRequestFactory();
        clientHttpRequestFactory.setConnectTimeout(timeout);
        return clientHttpRequestFactory;
    }
}
