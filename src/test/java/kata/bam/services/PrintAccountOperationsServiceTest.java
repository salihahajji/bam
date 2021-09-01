package kata.bam.services;

import kata.bam.domain.Account;
import kata.bam.domain.Money;
import kata.bam.domain.Operation;
import kata.bam.enums.OperationType;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.storage.AccountStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PrintAccountOperationsServiceTest {

    private PrintAccountOperationsService printAccountOperationsService;
    @Mock
    private AccountStorage accountStorage;

    @Before
    public void init() {
        List<Operation> operations = new ArrayList<>();
        Operation operation = new Operation(OperationType.DEPOSIT, Money.of(100L), LocalDate.of(2022, 03, 01));
        operations.add(operation);
        Account account = new Account(Money.of(1000L), new Account.AccountId(1L), operations);
        MockitoAnnotations.openMocks(this);
        Mockito.when(accountStorage.getAccountByAccountId(Mockito.any(Account.AccountId.class))).thenReturn(account);
        printAccountOperationsService = new PrintAccountOperationsService(accountStorage);
    }

    @Test(expected = AccountManagementException.class)
    public void testGetAccountStatementReturnAccountManagementExceptionWhenStartDateIsBeforeEndDate()
            throws AccountManagementException {
        printAccountOperationsService.printAccountOperations(new Account.AccountId(1L), LocalDate.ofEpochDay(222), LocalDate.ofEpochDay(111));
    }

    @Test
    public void testPrintAccountOperationsShouldReturnExpectedResult() throws AccountManagementException {
        List<Operation> result = printAccountOperationsService.printAccountOperations(new Account.AccountId(1L), LocalDate.of(2022, 02, 01), LocalDate.of(2023, 03, 01));
        assertThat(result.get(0).getOperationType()).isEqualTo(OperationType.DEPOSIT);
    }
}
