package pl.treekt.mychunk.Payments.Model;

import org.springframework.util.DigestUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.ArrayList;

public class Transfer {

    private int uid;
    private String publicKey;
    private int amount;
    private int mode;
    private String label;
    private String control;
    private String successUrl;
    private String failureUrl;
    private String notifyUrl;
    private String privateKey;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getFailureUrl() {
        return failureUrl;
    }

    public void setFailureUrl(String failureUrl) {
        this.failureUrl = failureUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }



    //customs

    public LinkedMultiValueMap<String, String> getParameters(){
        LinkedMultiValueMap<String, String> parameters = new LinkedMultiValueMap<String, String>();
        parameters.add("uid", String.valueOf(uid));
        parameters.add("public_key", publicKey);
        parameters.add("amount", String.valueOf(amount));
        parameters.add("mode", String.valueOf(mode));
        parameters.add("label", label);
        parameters.add("control", control);
        parameters.add("success_url", successUrl);
        parameters.add("failure_url", failureUrl);
        parameters.add("notify_url", notifyUrl);
        parameters.add("crc", getCrc());
        return parameters;
    }

    private ArrayList<Object> getFields(){
        ArrayList<Object> fields = new ArrayList<Object>();
        fields.add(uid);
        fields.add(publicKey);
        fields.add(amount);
        fields.add(mode);
        fields.add(label);
        fields.add(control);
        fields.add(successUrl);
        fields.add(failureUrl);
        fields.add(notifyUrl);
        fields.add(privateKey);
        return fields;
    }

    private String getCrc(){
        ArrayList<Object> fields = getFields();
        StringBuilder sb = new StringBuilder(12);
        for(Object field : fields){
            if(field != null){
                sb.append(field);
            }
        }
        String crc = DigestUtils.md5DigestAsHex(sb.toString().getBytes());

        return crc;
    }
}
