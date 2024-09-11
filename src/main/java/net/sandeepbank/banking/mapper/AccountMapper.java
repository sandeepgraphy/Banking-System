package net.sandeepbank.banking.mapper;

import net.sandeepbank.banking.dto.AccountDto;
import net.sandeepbank.banking.entity.Account;

public class AccountMapper {

    // Method to map AccountDto to Account
    public static Account mapToAccount(AccountDto accountDto) {
        if (accountDto == null) {
            return null; // Handle null case if necessary
        }
        return new Account(
                accountDto.getId(),
                accountDto.getAccountHolderName(),
                accountDto.getBalance()
        );
    }

    // Method to map Account to AccountDto
    public static AccountDto mapToAccountDto(Account account) {
        if (account == null) {
            return null; // Handle null case if necessary
        }
        return new AccountDto(
                account.getId(),
                account.getAccountHolderName(),
                account.getBalance()
        );
    }
}
