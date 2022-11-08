package model;

import java.time.LocalDate;

public abstract class Card implements Comparable<Card> {
    private String cardID, cardNumber;
    private LocalDate dateRelease;
    private int type;

    public Card() {
    }

    public Card(String cardID, String cardNumber, LocalDate dateRelease, int type) {
        this.cardID = cardID;
        this.cardNumber = cardNumber;
        this.dateRelease = dateRelease;
        this.type = type;
    }

    public abstract void showCardInfo();

    @Override
    public int compareTo(Card o) {
        int i = this.dateRelease.compareTo(o.dateRelease);

        //if dateRelease equal, then compare base on cardId
        if (i != 0) return i;

        return this.cardID.compareTo(o.cardID);
    }

    public String getCardID() {
        return cardID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getDateRelease() {
        return dateRelease;
    }

    public int getType() {
        return type;
    }


    public void setCardID(String cardID) {
        this.cardID = cardID;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setDateRelease(LocalDate dateRelease) {
        this.dateRelease = dateRelease;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return getType() + ", " + getCardID() + ", " + getCardNumber() + ", " + getDateRelease() + ", ";
    }
}
