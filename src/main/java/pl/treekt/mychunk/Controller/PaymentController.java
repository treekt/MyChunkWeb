package pl.treekt.mychunk.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.treekt.mychunk.API.Payments.Entity.Transaction;
import pl.treekt.mychunk.API.Payments.Entity.TransactionResult;

@RestController
@RequestMapping(value = "/payments/api")
public class PaymentController {


    @PostMapping("/")
    public ResponseEntity<TransactionResult> transaction(@RequestBody Transaction transaction){
        TransactionResult transactionResult = new TransactionResult();
        if(transaction != null){
            transactionResult.setId(transaction.getId());
            transactionResult.setReturn(1);
        }

        return new ResponseEntity<TransactionResult>(transactionResult,HttpStatus.OK);
    }


}
