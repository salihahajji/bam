package kata.bam.storage;

import kata.bam.domain.Account;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AccountStorageImpl implements AccountStorage {

    Map<Long, Account> accounts = new HashMap<>();

    @Override
    public Account getAccountByAccountId(Account.AccountId accountId) {
        return accounts.get(accountId.getValue());
    }
}
