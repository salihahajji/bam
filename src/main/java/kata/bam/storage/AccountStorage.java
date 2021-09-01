package kata.bam.storage;

import kata.bam.domain.Account;
import kata.bam.domain.Account.AccountId;

public interface AccountStorage {
    Account getAccountByAccountId(AccountId accountId);
}
