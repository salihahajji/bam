package kata.bam.web;

import kata.bam.domain.Account;
import kata.bam.domain.Money;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.usecases.WithdrawMoneyUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WithdrawMoneyController {

    private final WithdrawMoneyUseCase withdrawMoneyUseCase;

    @PostMapping(path = "/accounts/withdraw/{accountId}/{amount}")
    public void withdrawMoney(
            @PathVariable("accountId") Long accountId,
            @PathVariable("amount") Long amount) throws AccountManagementException {
        try {
            withdrawMoneyUseCase.withdraw(new Account.AccountId(accountId), Money.of(amount));
        } catch (AccountManagementException e) {
            throw new AccountManagementException(
                    "Withdraw money operation failed ");
        }
    }
}
