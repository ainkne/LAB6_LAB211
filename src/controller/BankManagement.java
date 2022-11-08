package controller;

import exception.TransactionDateException;
import function.DisplayFormat;
import function.Input;
import function.Validation;
import model.*;
import thread.CardWriterThread;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.stream.Collectors;

public class BankManagement {
    private static final String[] cardCategories = {"Credit Card", "Debit Card", "ATM Card"};
    CardCategory category ;
    Bank bank = new Bank();
    public BankManagement() {
        bank.loadCardInfo();
    }
    public void inputCard() throws TransactionDateException {
        System.out.println();
        displayCards();
        System.out.println();
        System.out.println("--- INPUT CARD INFO ---");
        System.out.println();
        for (int i = 0; i < cardCategories.length; i++){
            System.out.println( (i+1) + ". " + cardCategories[i]);
        }
        System.out.println();
        category = CardCategory.valueOf(Input.getUserChoice("Enter your choice of card: ", cardCategories.length));
        switch (category){
            case CREDIT_CARD -> inputCreditCard();
            case DEBIT_CARD -> inputDebitCard();
            case ATM_CARD -> inputAtmCard();
        }
        Collections.sort(Bank.cards);
        try {
            Thread.sleep(0, 5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        CardWriterThread writerThread = new CardWriterThread();
        new Thread(writerThread).start();
    }

    private void inputCreditCard() throws TransactionDateException {
        CreditCard c = new CreditCard();
        c.setCardID(Input.inputCardId("Enter CreditCard's ID: "));
        c.setCardNumber(Input.inputCardNum("Enter CreditCard's Number: "));
        c.setType(CardCategory.CREDIT_CARD.getType());
        LocalDate rD = LocalDate.parse(Input.inputDate("Enter CreditCard's date release: "), Validation.formatter);
        c.setDateRelease(checkReleaseDate(rD));
        c.setAmountDue(0);
        c.setDateValid(c.getDateRelease().plusYears(15));
        c.setAmountMax(Input.inputAmount("Enter CreditCard's Max Amount: "));
        Bank.cards.add(new CreditCard(c.getCardID(), c.getCardNumber(), c.getDateRelease(), c.getType(), c.getDateValid(),
                c.getAmountDue(), c.getAmountMax()));
    }

    private void inputDebitCard() throws TransactionDateException {
        DebitCard d = new DebitCard();
        d.setCardID(Input.inputCardId("Enter DebitCard's ID: "));
        d.setCardNumber(Input.inputCardNum("Enter DebitCard's Number: "));
        d.setType(CardCategory.DEBIT_CARD.getType());
        LocalDate rD = LocalDate.parse(Input.inputDate("Enter DebitCard's date release: "), Validation.formatter);
        d.setDateRelease(checkReleaseDate(rD));
        d.setDateValid(d.getDateRelease().plusYears(15));
        d.setAmountRemain(Input.inputAmount("Enter DebitCard's remain amount: "));
        Bank.cards.add(new DebitCard(d.getCardID(), d.getCardNumber(), d.getDateRelease(), d.getType(), d.getDateValid(), d.getAmountRemain()));
    }

    private void inputAtmCard() throws TransactionDateException {
        ATMCard a = new ATMCard();
        a.setCardID(Input.inputCardId("Enter ATMCard's ID: "));
        a.setCardNumber(Input.inputCardNum("Enter ATMCard's Number: "));
        a.setType(CardCategory.ATM_CARD.getType());
        LocalDate rd = LocalDate.parse(Input.inputDate("Enter ATMCard's date release: "), Validation.formatter);
        a.setDateRelease(checkReleaseDate(rd));
        a.setAmount(Input.inputAmount("Enter ATMCard's amount: "));
        Bank.cards.add(new ATMCard(a.getCardID(), a.getCardNumber(), a.getDateRelease(), a.getType(), a.getAmount()));
    }

    public void displayCards(){
        Bank.mapToDisplayCards = Bank.cards
                .stream()
                .collect(Collectors.groupingBy(Card::getType));
        Bank.mapToDisplayCards.forEach((type, cards) -> {
            if (type == CardCategory.CREDIT_CARD.getType()){
                System.out.println();
                System.out.println("                                            CREDIT CARD INFO");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                DisplayFormat.displayCreditCard();
            }
            if (type == CardCategory.DEBIT_CARD.getType()){
                System.out.println();
                System.out.println("                                            DEBIT CARD INFO");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                DisplayFormat.displayDebitCard();
            }
            if (type == CardCategory.ATM_CARD.getType()) {
                System.out.println();
                System.out.println("                                            ATM CARD INFO");
                System.out.println("---------------------------------------------------------------------------------------------------------------------------");
                DisplayFormat.displayATMCard();
            }
            cards.forEach(Card::showCardInfo);
        });
    }
    private LocalDate checkReleaseDate(LocalDate date) throws TransactionDateException {
        LocalDate requiredDate = LocalDate.of(1900, 1, 1);
        requiredDate.format(DateTimeFormatter.ofPattern(Validation.dateFormat));
        if (date.isBefore(requiredDate)){
            throw new TransactionDateException("Card is out of date, try another one");
        } return date;
    }
}
