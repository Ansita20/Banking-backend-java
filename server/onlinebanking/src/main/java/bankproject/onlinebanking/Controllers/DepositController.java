package bankproject.onlinebanking.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import bankproject.onlinebanking.Model.BankAccount;
import bankproject.onlinebanking.Model.Transactions;
import bankproject.onlinebanking.Service.AccountService;
import bankproject.onlinebanking.Service.TransactionService;
import bankproject.onlinebanking.helper.Helper;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class DepositController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody Map<String, Object> request) {
        try {
            long accountno = Long.parseLong(request.get("accountno").toString());
            double amount = Double.parseDouble(request.get("amount").toString());

            BankAccount account = accountService.findByAccountNo(accountno);
            if (account == null) {
                return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
            }

            double newBalance = account.getBalance() + amount;
            account.setBalance(newBalance);
            accountService.updateAccount(account);

            Transactions transaction = new Transactions();
            transaction.setFromAccount(99999999);
            transaction.setToAccount(accountno);
            transaction.setAmount(amount);
            transaction.setSenderBal(0.0);
            transaction.setReceiverBal(newBalance);
            transaction.setDescription("Cash Deposit / Credit");
            transaction.setTransactionStatus("Completed");
            transaction.setTransactionDate(Helper.dateStamp());
            transaction.setTransactionTime(Helper.timeStamp());
            transactionService.save(transaction);

            return new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing deposit: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
