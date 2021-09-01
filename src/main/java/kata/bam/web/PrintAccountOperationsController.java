package kata.bam.web;

import kata.bam.domain.Account;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.usecases.PrintAccountOperationsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class PrintAccountOperationsController {

    private final PrintAccountOperationsUseCase printAccountOperationsUseCase;

    @GetMapping(path = "/accounts/print/{accountId}/operations/{startDate}/{endDate}")
    public void printAccountOperations(
            @PathVariable("accountId") Long accountId,
            @PathVariable("startDate") LocalDate startDate,
            @PathVariable("endDate") LocalDate endDate) throws AccountManagementException {
        printAccountOperationsUseCase.printAccountOperations(new Account.AccountId(accountId), startDate, endDate);
    }
}
