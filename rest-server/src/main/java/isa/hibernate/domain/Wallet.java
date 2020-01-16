package isa.hibernate.domain;

import javax.persistence.*;
import javax.ws.rs.DefaultValue;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet")
public class Wallet {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne
	@JoinColumn(name = "developer_id")
	Developer owner;

	@Column(name = "balance", nullable = false)
	@DefaultValue("0")
	BigDecimal balance = BigDecimal.ZERO;

	@Version
	Long version;

	public Wallet() {
	}

	public Wallet(Developer owner) {
		this.owner = owner;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Developer getOwner() {
		return owner;
	}

	public void setOwner(Developer owner) {
		this.owner = owner;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal inc(BigDecimal amount) {
		this.balance = this.balance.add(amount);
		return this.balance;
	}

	public BigDecimal dec(BigDecimal amount) {
		this.balance = this.balance.subtract(amount);
		return this.balance;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}
}
