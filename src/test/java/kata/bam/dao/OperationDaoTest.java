package kata.bam.dao;

import java.sql.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kata.bam.Application;
import kata.bam.entities.Account;
import kata.bam.entities.Client;
import kata.bam.entities.Operation;
import kata.bam.enums.OperationType;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@DataJpaTest
public class OperationDaoTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private OperationDao operationDao;

	@Test
	public void testGetAccountStatementReturneExpectedResult() {
		Client client = new Client();
		Account account = new Account();
		account.setClient(client);
		Operation op1 = new Operation(OperationType.DEPOSIT, account, 120D, Date.valueOf("2021-06-01"));
		Operation op2 = new Operation(OperationType.WITHDRAWAL, account, 120D, Date.valueOf("2021-05-25"));
		Operation op3 = new Operation(OperationType.DEPOSIT, account, 120D, Date.valueOf("2021-01-01"));
		entityManager.persist(op1);
		entityManager.persist(op2);
		entityManager.persist(op3);
		entityManager.flush();

		List<Operation> result = operationDao.findAllByAccount_IdAndOperationDateBetween(1, Date.valueOf("2021-05-20"),
				Date.valueOf("2021-06-01"));
		System.out.println(result);
		Assertions.assertThat(result).hasSize(2);

	}
}
