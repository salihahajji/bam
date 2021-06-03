package kata.bam.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.Id;

import kata.bam.enums.OperationType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "operation")
@Getter
@Setter
public class Operation {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "operation_type")
	@Enumerated(EnumType.STRING)
	private OperationType operationType;

	@Column(name = "operation_date")
	@Temporal(TemporalType.DATE)
	private Date operationDate;
	
	private Double amount;

	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account account;

	public Operation(OperationType operationType, Account account, Double amount) {
		this(operationType, account, amount, new Date(System.currentTimeMillis()));
	}
	
	public Operation(OperationType operationType, Account account, Double amount, Date operationDate) {
		this.operationType = operationType;
		this.account = account;
		this.operationDate = operationDate;
		this.amount = amount;
	}
}
