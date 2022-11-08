package model;

import function.DisplayFormat;

import java.time.LocalDate;

public class ATMCard extends Card{

    private double amount;

    public ATMCard() {
    }

    public ATMCard(String cardID, String cardNumber, LocalDate dateRelease, int type, double amount) {
        super(cardID, cardNumber, dateRelease, type);
        this.amount = amount;
    }

    @Override
    public void showCardInfo() {
        System.out.printf(DisplayFormat.atmCardFormat, getCardID(), getCardNumber(), getDateRelease(), getAmount(), "\n");
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return super.toString() + getAmount();
    }
}
