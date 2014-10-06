package droolsbook.bank.model;

import java.math.BigDecimal;

public class AccountInfo {
	BigDecimal averageAmount;
	BigDecimal averageBalance;
	Long number;
	BigDecimal averageNumberOfTransactions;
	Long numberOfTransactions1Day;
	
	
	
	
	
	
	public BigDecimal getAverageNumberOfTransactions() {
		return averageNumberOfTransactions;
	}

	public void setAverageNumberOfTransactions(
			BigDecimal averageNumberOfTransactions) {
		this.averageNumberOfTransactions = averageNumberOfTransactions;
	}

	public Long getNumberOfTransactions1Day() {
		return numberOfTransactions1Day;
	}

	public void setNumberOfTransactions1Day(Long numberOfTransactions1Day) {
		this.numberOfTransactions1Day = numberOfTransactions1Day;
	}

	public Long getNumber() {
		return number;
	}

	public void setNumber(Long number) {
		this.number = number;
	}

	public BigDecimal getAverageBalance() {
		return averageBalance;
	}

	public void setAverageBalance(BigDecimal averageBalance) {
		this.averageBalance = averageBalance;
	}

	public BigDecimal getAverageAmount() {
		return averageAmount;
	}

	public void setAverageAmount(BigDecimal averageAmount) {
		this.averageAmount = averageAmount;
	}
}
