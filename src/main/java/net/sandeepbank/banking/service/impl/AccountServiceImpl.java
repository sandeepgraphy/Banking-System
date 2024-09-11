package net.sandeepbank.banking.service.impl;

import net.sandeepbank.banking.dto.AccountDto;
import net.sandeepbank.banking.entity.Account;
import net.sandeepbank.banking.mapper.AccountMapper;
import net.sandeepbank.banking.repository.AccountRepository;
import net.sandeepbank.banking.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public AccountDto createAccount(AccountDto accountDto) {
        Account account = AccountMapper.mapToAccount(accountDto);
        Account saveAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(saveAccount);

    }

    @Override
    public AccountDto getAccountById(Long id) {

        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not Exists"));


        return AccountMapper.mapToAccountDto(account);

    }

    @Override
    public AccountDto deposit(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not Exists"));

        double total = account.getBalance()+ amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);

        return AccountMapper.mapToAccountDto(saveAccount);
    }

    @Override
    public AccountDto withdraw(Long id, double amount) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not Exists"));
        if (account.getBalance()<amount){
            throw new RuntimeException("Insufficient Amount");
        }
        double total = account.getBalance() - amount;
        account.setBalance(total);
        Account saveAccount = accountRepository.save(account);
        return AccountMapper.mapToAccountDto (saveAccount);
    }

    @Override
    public List<AccountDto> getAllAccount() {
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(account -> AccountMapper.mapToAccountDto(account))
                .collect(Collectors.toList());

    }

    @Override
    public void deleteAccount(Long id) {
        Account account= accountRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Account does not Exists"));
        accountRepository.deleteById(id);



    }

}
