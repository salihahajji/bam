package kata.bam.services;

import kata.bam.domain.Account;
import kata.bam.domain.Money;
import kata.bam.domain.Operation;
import kata.bam.enums.OperationType;
import kata.bam.exceptions.AccountManagementException;
import kata.bam.storage.AccountStorage;
import kata.bam.storage.OperationStorage;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DepositMoneyServiceTest {

    private DepositMoneyService depositMoneyService;
    @Mock
    private AccountStorage accountStorage;
    @Mock
    private OperationStorage operationStorage;

    @Before
    public void init() {
        List<Operation> operations = new ArrayList<>();
        Operation operation = new Operation(OperationType.DEPOSIT, Money.of(100L), LocalDate.of(2021, 9, 1));
        operations.add(operation);
        Account account = new Account(Money.of(1000L), new Account.AccountId(1L), operations);
        MockitoAnnotations.openMocks(this);
        Mockito.when(accountStorage.getAccountByAccountId(Mockito.any(Account.AccountId.class))).thenReturn(account);
        depositMoneyService = new DepositMoneyService(accountStorage, operationStorage);
    }

    @Test(expected = AccountManagementException.class)
    public void testDepositReturnAccountManagementExceptionWhenAmountIsNegative() throws AccountManagementException {
        depositMoneyService.deposit(new Account.AccountId(1L), Money.of(-20));
    }

    @Test
    public void testDepositShouldAddAmount() throws AccountManagementException {
        Money result = depositMoneyService.deposit(new Account.AccountId(1L), Money.of(20));
        assertThat(result.getAmount()).isEqualTo(1020);
    }
}
