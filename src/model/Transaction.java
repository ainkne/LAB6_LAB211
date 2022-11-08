package model;

import java.time.LocalDate;

public class Transaction implements Comparable<Transaction> {
    private String transactionID, cardID;
    private double transactionAmount;
    private LocalDate transactionDate;

    private String note;

    public Transaction() {}

    public Transaction(String transactionID, String cardID, double transactionAmount, LocalDate transactionDate, String note) {
        this.transactionID = transactionID;
        this.cardID = cardID;
        this.transactionAmount = transactionAmount;
        this.transactionDate = transactionDate;
        this.note = note;
    }

    public String getTransactionID() {
        return transactionID;
    }

    public String getCardID() {
        return cardID;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getNote() {
        return note;
    }

    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }

    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setTransactionAmount(double transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public void setTransactionDate(LocalDate transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String generateTransactionID(){
        return "TR" + (int) ((Math.random() * (1000))) + (int)((Math.random() * (10000))) ;
    }

    @Override
    public String toString() {
        return getTransactionID() + ", " + getCardID() + ", " + getTransactionDate() + ", " + getTransactionAmount() + ", " + getNote();
    }
    @Override
    public int compareTo(Transaction o) {
        return Double.compare(o.transactionAmount, this.transactionAmount); //desc order
    }
}
