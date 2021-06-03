package kata.bam.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "client")
@Setter
@Getter
public class Client {

	@Id
	@GeneratedValue
	private Integer id;
	
	private String name;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
	private Account account;
}
