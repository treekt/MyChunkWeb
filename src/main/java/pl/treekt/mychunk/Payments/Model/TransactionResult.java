package pl.treekt.mychunk.Payments.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TransactionResult {

    @JsonProperty("tr_id")
    private int id;
    @JsonProperty("tr_return")
    private int trReturn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReturn() {
        return trReturn;
    }

    public void setReturn(int trReturn) {
        this.trReturn = trReturn;
    }
}
