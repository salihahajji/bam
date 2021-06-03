package kata.bam.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.sql.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import kata.bam.dao.AccountDao;
import kata.bam.dao.OperationDao;
import kata.bam.entities.Account;
import kata.bam.entities.Operation;
import kata.bam.exceptions.AccountManagementException;

public class AccountManagementServiceTest {

	private AccountDao accountDao;
	private OperationDao operationDao;

	private AccountManegementService accountManegementService;

	@Before
	public void init() {
		accountDao = Mockito.mock(AccountDao.class);
		operationDao = Mockito.mock(OperationDao.class);
		accountManegementService = new AccountManagementServiceImpl(accountDao, operationDao);
	}

	@Test(expected = AccountManagementException.class)
	public void testDepositReturnAccountManagementExceptionWhenAmountIsNegative() throws AccountManagementException {
		Account account = new Account();
		account.setId(1);
		account.setAmount(100);
		accountManegementService.deposit(account, -20);
	}

	@Test
	public void testDepositShouldAddAmount() throws AccountManagementException {
		Account account = new Account();
		account.setId(1);
		account.setAmount(100);
		doNothing().when(accountDao).setAmount(120, 1);
		when(operationDao.save(any(Operation.class))).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) {
				return invocation.getArguments()[0];
			}
		});
		double result = accountManegementService.deposit(account, 20);
		assertThat(result).isEqualTo(120);
		verify(operationDao).save(Mockito.any(Operation.class));
		verify(accountDao).setAmount(120, 1);
	}

	@Test(expected = AccountManagementException.class)
	public void testWithdrawalReturnAccountManagementExceptionWhenAmountIsNegative() throws AccountManagementException {
		Account account = new Account();
		account.setId(1);
		account.setAmount(100);
		accountManegementService.withdraw(account, -20);
	}

	@Test(expected = AccountManagementException.class)
	public void testWithdrawalReturnAccountManagementExceptionWhenAmountIsGreaterThanAccountAmount()
			throws AccountManagementException {
		Account account = new Account();
		account.setId(1);
		account.setAmount(100);
		accountManegementService.withdraw(account, 150);
	}

	@Test
	public void testWithdrawalShouldWithdrawAmount() throws AccountManagementException {
		Account account = new Account();
		account.setId(1);
		account.setAmount(100);
		doNothing().when(accountDao).setAmount(120, 1);
		when(operationDao.save(any(Operation.class))).thenAnswer(new Answer() {
			public Object answer(InvocationOnMock invocation) {
				return invocation.getArguments()[0];
			}
		});
		double result = accountManegementService.withdraw(account, 20);
		assertThat(result).isEqualTo(80);
		verify(operationDao).save(Mockito.any(Operation.class));
		verify(accountDao).setAmount(80, 1);
	}

	@Test(expected = AccountManagementException.class)
	public void testGetAccountStatementReturnAccountManagementExceptionWhenStartDateIsBeforeEndDate()
			throws AccountManagementException {
		Account account = new Account();
		account.setId(1);
		account.setAmount(100);
		accountManegementService.getAccountStatement(account, new Date(222), new Date(111));
	}
}
