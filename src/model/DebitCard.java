package model;

import function.DisplayFormat;

import java.time.LocalDate;

public class DebitCard extends Card{
    private LocalDate dateValid;
    private double amountRemain;

    public DebitCard() {
    }

    public DebitCard(String cardID, String cardNumber, LocalDate dateRelease, int type, LocalDate dateValid, double amountRemain) {
        super(cardID, cardNumber, dateRelease, type);
        this.dateValid = dateValid;
        this.amountRemain = amountRemain;
    }

    @Override
    public void showCardInfo() {
        System.out.printf(DisplayFormat.debitCardFormat, getCardID(), getCardNumber(), getDateRelease(), getDateValid(),
                getAmountRemain(), "\n");
    }

    public LocalDate getDateValid() {
        return dateValid;
    }

    public double getAmountRemain() {
        return amountRemain;
    }

    public void setDateValid(LocalDate dateValid) {
        this.dateValid = dateValid;
    }

    public void setAmountRemain(double amountRemain) {
        this.amountRemain = amountRemain;
    }

    @Override
    public String toString() {
        return super.toString() + getDateValid() + ", " + getAmountRemain();
    }
}
