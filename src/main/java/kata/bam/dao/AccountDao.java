package kata.bam.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import kata.bam.entities.Account;

@Repository
public interface AccountDao extends JpaRepository<Account, Integer> {
	
	@Modifying
	@Query("update Account act set act.amount =?1 where act.id = ?2 ")
	void setAmount(double amount, Integer id);

}