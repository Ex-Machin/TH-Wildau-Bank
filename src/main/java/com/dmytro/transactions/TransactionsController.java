package com.dmytro.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;
import com.dmytro.customer.CustomerService;

@Controller
public class TransactionsController {

    @Autowired
    private CustomerService customerService; // Service to handle customer data

    @PostMapping("/transfer")
    @Transactional // Ensure the entire method is treated as a single transaction
    public ResponseEntity<Object> transferMoney(@RequestBody TransferRequest transferRequest) {

        // Validate the transaction details: check account existence, sufficient funds, etc.
        // This can be a part of customerService or a separate validation service.

        try {
            // Deduct amount from sender's account
            customerService.deductAmount(transferRequest.getFromAccountId(), transferRequest.getAmount());

            // Add amount to recipient's account
            customerService.addAmount(transferRequest.getToAccountId(), transferRequest.getAmount());

            // If both operations are successful, commit the transaction
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            // If any operation fails, the transaction will be automatically rolled back
            // Handle the exception and return a meaningful response
            return ResponseEntity.badRequest().body("ERROR");
        }
    }
}
