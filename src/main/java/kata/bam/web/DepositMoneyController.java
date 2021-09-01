package kata.bam.web;

import kata.bam.domain.Account.AccountId;
import kata.bam.domain.Money;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.usecases.DepositMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DepositMoneyController {

    private final DepositMoneyUseCase depositMoneyUseCase ;

    @PostMapping(path = "/accounts/deposit/{accountId}/{amount}")
    public void depositMoney(
            @PathVariable("accountId") Long accountId,
            @PathVariable("amount") Long amount) throws AccountManagementException {
        try {
            depositMoneyUseCase.deposit(new AccountId(accountId), Money.of(amount));
        } catch (AccountManagementException e) {
            throw new AccountManagementException(
                    "Deposit money operation failed ");
        }
    }
}
