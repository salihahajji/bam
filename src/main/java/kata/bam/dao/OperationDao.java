package kata.bam.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kata.bam.entities.Operation;

@Repository
public interface OperationDao extends JpaRepository<Operation, Integer> {
	List<Operation> findAllByAccount_IdAndOperationDateBetween(Integer id, Date startDate, Date endDate);
}