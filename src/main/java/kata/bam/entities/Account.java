package kata.bam.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "account")
@Getter
@Setter
public class Account {

	@Id
	@GeneratedValue
	private Integer id;

	private double amount;

	@OneToOne(mappedBy = "account")
	private Client client;
	
	@OneToMany(mappedBy="account")
	private List<Operation> operations;
	
	public boolean canWidhraw(double amount) {
		return this.amount > amount && amount > 0;
	}
	
	public double widhraw(double amount) {
		this.amount -= amount;
		return amount;
	}
	
	public double deposit(double amount) {
		this.amount += amount;
		return amount;
	}
	
}
