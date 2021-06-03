package kata.bam.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kata.bam.dao.AccountDao;
import kata.bam.dao.OperationDao;
import kata.bam.entities.Account;
import kata.bam.entities.Operation;
import kata.bam.enums.OperationType;
import kata.bam.exceptions.AccountManagementException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountManagementServiceImpl implements AccountManegementService {

	private AccountDao accountDao;

	private OperationDao operationDao;

	@Autowired
	public AccountManagementServiceImpl(AccountDao accountDao, OperationDao operationDao) {
		this.accountDao = accountDao;
		this.operationDao = operationDao;
	}

	public double deposit(Account account, double amount) throws AccountManagementException {
		if (amount > 0) {
			account.deposit(amount);
			operationDao.save(new Operation(OperationType.DEPOSIT, account, amount));
			accountDao.setAmount(account.getAmount(), account.getId());
			return account.getAmount();
		}

		else {
			log.warn("can't deposit negative amount={}", amount);
			throw new AccountManagementException("can't deposit negative amount=" + amount);
		}
	}

	public double withdraw(Account account, double amount) throws AccountManagementException {
		if (account.canWidhraw(amount)) {
			account.widhraw(amount);
			operationDao.save(new Operation(OperationType.WITHDRAWAL, account, amount));
			accountDao.setAmount(account.getAmount(), account.getId());
			return account.getAmount();
		} else {
			log.warn("can't widhraw amount={} , current amount={}", amount, account.getAmount());
			throw new AccountManagementException(
					"can't widhraw amount=" + amount + " , current amount=" + account.getAmount());
		}
	}

	public List<Operation> getAccountStatement(Account account, Date startDate, Date endDate)
			throws AccountManagementException {
		if (endDate.before(startDate)) {
			throw new AccountManagementException(
					"start date = " + startDate + " should be before end date = " + endDate);
		} else {
			return operationDao.findAllByAccount_IdAndOperationDateBetween(account.getId(), startDate, endDate);
		}
	}

}