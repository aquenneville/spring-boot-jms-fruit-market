<<<<<<< HEAD
package github.aq.market.common.jms;

import java.io.Serializable;
import java.time.LocalDate;

import javax.print.attribute.standard.DateTimeAtCompleted;

import github.aq.market.common.Order;
import github.aq.market.common.Transaction;

public class TransactionMessage implements Serializable {

	private LocalDate transactionTime;
	private Transaction transaction;
	public LocalDate getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(LocalDate transactionTime) {
		this.transactionTime = transactionTime;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
=======
package github.aq.market.common.jms;

import java.io.Serializable;
import java.time.LocalDate;

import javax.print.attribute.standard.DateTimeAtCompleted;

import github.aq.market.common.Order;
import github.aq.market.common.Transaction;

public class TransactionMessage implements Serializable {

	private LocalDate transactionTime;
	private Transaction transaction;
	public LocalDate getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(LocalDate transactionTime) {
		this.transactionTime = transactionTime;
	}
	public Transaction getTransaction() {
		return transaction;
	}
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}
	
}
>>>>>>> 8c09f5362fec9c4677e317a88320840f48d842b1
