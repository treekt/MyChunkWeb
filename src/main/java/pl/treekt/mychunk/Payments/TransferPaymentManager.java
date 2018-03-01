package pl.treekt.mychunk.Payments;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import pl.treekt.mychunk.Payments.Model.Transfer;

@Component
public class TransferPaymentManager {

    private RestTemplate restTemplate = new RestTemplate();

    private final String url = "http://homepay.pl/przelew/";

    public void sendTransfer() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        Transfer transfer = new Transfer();
        transfer.setUid(231);
        transfer.setAmount(100);
        transfer.setPublicKey("Dasddasdadasd");
        MultiValueMap<String, String> parameters = transfer.getParameters();

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<MultiValueMap<String, String>>(parameters, headers);

        restTemplate.postForLocation(url, entity, String.class);
    }
}
