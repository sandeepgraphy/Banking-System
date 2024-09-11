package net.sandeepbank.banking.controller;
import net.sandeepbank.banking.dto.AccountDto;
import net.sandeepbank.banking.service.AccountService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
//    Add Account Rest Api
    @PostMapping
    public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto)
    {
        return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
    }
//    Get Account rest Api
    @GetMapping("/{id}")
    public ResponseEntity<AccountDto> getAccountById(@PathVariable  Long id)
    {
        AccountDto accountDto= accountService.getAccountById(id);
        return ResponseEntity.ok(accountDto);

    }
//    Deposit account rest Api
    @PutMapping("/{id}/deposit")


    public ResponseEntity<AccountDto>deposit (@PathVariable long id,
                                              @RequestBody Map< String, Double> request){
        Double amount= request.get("amount");
        AccountDto accountDto= accountService.deposit(id, amount);
        return ResponseEntity.ok(accountDto);


    }
//    Withdraw account rest Api
    @PutMapping("/{id}/withdraw")



public ResponseEntity<AccountDto>withdraw (@PathVariable long id,
                                          @RequestBody Map< String, Double> request) {
        Double amount = request.get("amount");
        AccountDto accountDto = accountService.withdraw(id, amount);
        return ResponseEntity.ok(accountDto);
    }
//    Get all account Rest api

    @GetMapping
    public ResponseEntity<List<AccountDto>>getAllAccount(){
        List<AccountDto>account = accountService.getAllAccount();
        return ResponseEntity.ok(account);
    }
//    delete account rest api

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteAccount(@PathVariable Long id){
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Madarchod ka Account delete Successfully ");
    }


}
