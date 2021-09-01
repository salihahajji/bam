package kata.bam.usecases;

import kata.bam.domain.Account;
import kata.bam.domain.Operation;
import kata.bam.exceptions.AccountManagementException;

import java.time.LocalDate;
import java.util.List;

public interface PrintAccountOperationsUseCase {
    List<Operation> printAccountOperations(Account.AccountId accountId, LocalDate startDate, LocalDate endDate) throws AccountManagementException;
}
