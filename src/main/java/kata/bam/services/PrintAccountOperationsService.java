package kata.bam.services;

import kata.bam.domain.Account;
import kata.bam.domain.Operation;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.storage.AccountStorage;
import kata.bam.usecases.PrintAccountOperationsUseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PrintAccountOperationsService implements PrintAccountOperationsUseCase {

    private final AccountStorage accountStorage;

    public List<Operation> printAccountOperations(Account.AccountId accountId, LocalDate startDate, LocalDate endDate)
            throws AccountManagementException {
        if (endDate.isBefore(startDate)) {
            throw new AccountManagementException(
                    "start date = " + startDate + " should be before end date = " + endDate);
        } else {
            Account account = Optional.of(accountStorage.getAccountByAccountId(accountId))
                    .orElseThrow(() -> new AccountManagementException("account with accountId = " + accountId + " does not exist"));
            return account.getOperations().stream()
                    .filter(operation -> operation.getOperationDate().isAfter(startDate)
                            && operation.getOperationDate().isBefore(endDate))
                    .collect(Collectors.toList());
        }
    }
}