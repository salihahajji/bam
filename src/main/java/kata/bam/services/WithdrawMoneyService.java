package kata.bam.services;

import kata.bam.domain.Account;
import kata.bam.domain.Money;
import kata.bam.domain.Operation;
import kata.bam.enums.OperationType;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.storage.AccountStorage;
import kata.bam.storage.OperationStorage;
import kata.bam.usecases.WithdrawMoneyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class WithdrawMoneyService implements WithdrawMoneyUseCase {

    private final AccountStorage accountStorage;
    private final OperationStorage operationStorage;

    public Money withdraw(Account.AccountId accountId, Money amount) throws AccountManagementException {

        Account account = Optional.of(accountStorage.getAccountByAccountId(accountId))
                .orElseThrow(() -> new AccountManagementException("account with accountId = " + accountId + " does not exist"));
        if (account.canWithdraw(amount)) {
            account.withdraw(amount);
            Operation operation = new Operation(OperationType.WITHDRAWAL, amount);
            operationStorage.saveOperation(operation, accountId);
            return account.getAmount();
        } else {
            log.warn("can't withdraw amount={} , current amount={}", amount, account.getAmount());
            throw new AccountManagementException(
                    "can't withdraw amount=" + amount + " , current amount=" + account.getAmount());
        }
    }
}