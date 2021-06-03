package kata.bam.services;

import java.sql.Date;
import java.util.List;

import kata.bam.entities.Account;
import kata.bam.entities.Operation;
import kata.bam.exceptions.AccountManagementException;

public interface AccountManegementService {

	public double deposit(Account account, double amount) throws AccountManagementException;

	public double withdraw(Account account, double amount) throws AccountManagementException;

	public List<Operation> getAccountStatement(Account account, Date startDate, Date endDate)
			throws AccountManagementException;

}
