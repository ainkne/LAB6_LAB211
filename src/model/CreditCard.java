package model;

import function.DisplayFormat;

import java.time.LocalDate;

public class CreditCard extends Card{
    private LocalDate dateValid;
    private double amountDue;
    private double amountMax;

    public CreditCard() {
    }


    public CreditCard(String cardID, String cardNumber, LocalDate dateRelease, int type, LocalDate dateValid, double amountDue, double amountMax) {
        super(cardID, cardNumber, dateRelease, type);
        this.dateValid = dateValid;
        this.amountDue = amountDue;
        this.amountMax = amountMax;
    }

    @Override
    public void showCardInfo() {
        System.out.printf(DisplayFormat.creditCardFormat, getCardID(), getCardNumber(), getDateRelease(), getDateValid(),
                getAmountDue(), getAmountMax(), "\n");
    }

    public LocalDate getDateValid() {
        return dateValid;
    }

    public double getAmountDue() {
        return amountDue;
    }

    public double getAmountMax() {
        return amountMax;
    }

    public void setDateValid(LocalDate dateValid) {
        this.dateValid = dateValid;
    }

    public void setAmountDue(double amountDue) {
        this.amountDue = amountDue;
    }

    public void setAmountMax(double amountMax) {
        this.amountMax = amountMax;
    }

    @Override
    public String toString() {
        return super.toString() + getDateValid() + ", " + getAmountDue() + ", " + getAmountMax();
    }
}
