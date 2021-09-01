package kata.bam.usecases;

import kata.bam.domain.Account;
import kata.bam.domain.Money;
import kata.bam.exceptions.AccountManagementException;

public interface WithdrawMoneyUseCase {
    Money withdraw(Account.AccountId accountId, Money amount) throws AccountManagementException;
}
