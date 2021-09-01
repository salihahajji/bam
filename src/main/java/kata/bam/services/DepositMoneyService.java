package kata.bam.services;

import kata.bam.domain.Account;
import kata.bam.domain.Account.AccountId;
import kata.bam.domain.Money;
import kata.bam.domain.Operation;
import kata.bam.enums.OperationType;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.storage.AccountStorage;
import kata.bam.storage.OperationStorage;
import kata.bam.usecases.DepositMoneyUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepositMoneyService implements DepositMoneyUseCase {

    private final AccountStorage accountStorage;
    private final OperationStorage operationStorage;

    public Money deposit(AccountId accountId, Money amount) throws AccountManagementException {
        if (amount.isPositive()) {
            Account account = Optional.of(accountStorage.getAccountByAccountId(accountId))
                    .orElseThrow(() -> new AccountManagementException("account with accountId = " + accountId + " does not exist"));

            account.deposit(amount);
            Operation operation = new Operation(OperationType.DEPOSIT, amount);
            operationStorage.saveOperation(operation, accountId);
            return account.getAmount();
        } else {
            log.warn("can't deposit negative amount={}", amount);
            throw new AccountManagementException("can't deposit negative amount=" + amount);
        }
    }
}