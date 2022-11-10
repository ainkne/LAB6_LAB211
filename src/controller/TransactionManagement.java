package controller;

import exception.CardException;
import exception.TransactionDateException;
import function.DisplayFormat;
import function.Input;
import function.Validation;
import model.*;
import thread.CardWriterThread;
import thread.TransactionWriterThread;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

public class TransactionManagement {
    Bank bank = new Bank();
    Transaction transaction;
    public TransactionManagement() {
        bank.loadTransactionInfo();
    }
    public void addTransaction() throws CardException, TransactionDateException {

        transaction = new Transaction();

        while (true) {
            System.out.println();
            System.out.println("--- ADDING TRANSACTION ---");
            System.out.println();
            transaction.setTransactionID(transaction.generateTransactionID());

            transaction.setCardID(Input.inputCardIdForTransaction("Enter card's ID for this transaction: "));

            LocalDate transactionDate = LocalDate.parse(LocalDate
                    .now()
                    .format(DateTimeFormatter.ofPattern(Validation.dateFormat))
                    , Validation.formatter);

            transaction.setTransactionDate(checkCardDate(transaction.getCardID(), transactionDate));

            double transactionAmount = checkTransactionAmount(transaction.getCardID(),
                    Input.inputAmountOfTrans("Enter transaction money: "));
            transaction.setTransactionAmount(transactionAmount);

            transaction.setNote(Input.inputString("Enter transaction note: "));

            Bank.transactions.add(new Transaction(transaction.getTransactionID(), transaction.getCardID(),
                    transaction.getTransactionAmount(), transaction.getTransactionDate(), transaction.getNote()));
            Collections.sort(Bank.transactions);
            String keepGoing = Input.inputYesNo("Do you want to add more transaction (y) continue, (n) to discard: ");
            if (keepGoing.equals("n")) break;

        }
        try {
            Thread.sleep(0, 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        TransactionWriterThread writerThread = new TransactionWriterThread();
        new Thread(writerThread).start();
        CardWriterThread writerThreadCard = new CardWriterThread();
        new Thread(writerThreadCard).start();
    }

    public void printTransaction(){
        System.out.println();
        System.out.println("                                        ---- TRANSACTION INFO ----");
        System.out.println();
        DisplayFormat.displayTransaction();
        Bank.transactions.forEach(t -> System.out.printf(DisplayFormat.transactionFormat, t.getTransactionID(),
                t.getCardID(), t.getTransactionDate(), t.getTransactionAmount(),t.getNote(), "\n"));
    }
    private double checkTransactionAmount(String cardId, double transactionAmount) throws CardException {
        double n = 0;
        Card card = Bank.cards.get(bank.findCardById(cardId));
        if (card instanceof CreditCard) {
            if (transactionAmount + ((CreditCard) card).getAmountDue() > ((CreditCard) card).getAmountMax()){
                throw new CardException("Total payment is greater than your amount max!");
            }
            n = transactionAmount;
            ((CreditCard) card).setAmountDue(((CreditCard) card).getAmountMax() - transactionAmount);
        }
        if (card instanceof DebitCard) {
            if (transactionAmount > ((DebitCard) card).getAmountRemain()){
                throw new CardException("Total payment is greater than your amount remain in your card!");
            }
            n = transactionAmount;
            ((DebitCard) card).setAmountRemain(((DebitCard) card).getAmountRemain() - transactionAmount);
        }
        if (card instanceof ATMCard) {
            if (transactionAmount > ((ATMCard) card).getAmount()){
                throw new CardException("Total withdraws is greater than your amount max!");
            }
            n =  transactionAmount;
            ((ATMCard) card).setAmount(((ATMCard) card).getAmount() - transactionAmount);
        }
        return n;
    }
    private LocalDate checkCardDate(String cardId, LocalDate date) throws TransactionDateException {
        LocalDate returnDate = null;
        Card card = Bank.cards.get(bank.findCardById(cardId));
        if (card instanceof ATMCard){
            returnDate =  date;
        }

        if (card instanceof CreditCard){
            if (date.isBefore(card.getDateRelease()) && date.isAfter(((CreditCard) card).getDateValid())){
                throw new TransactionDateException("Your Credit Card (" + cardId +  ") is not accepted, try again!");
            }
            returnDate = date;
        }

        if (card instanceof DebitCard){
            if (date.isBefore(card.getDateRelease()) && date.isAfter(((DebitCard) card).getDateValid())){
                throw new TransactionDateException("Your Debit Card (" + cardId +") is not accepted, try again!");
            }
            returnDate = date;
        }
        return returnDate;
    }
    public void selectTop3Transactions(){
        System.out.println("                                        ---- TOP 3 TRANSACTION ----");
        System.out.println();
        DisplayFormat.displayTransaction();
        for (int i = 0; i <= 2; i++){
            Transaction t = Bank.transactions.get(i);
            System.out.printf(DisplayFormat.transactionFormat, t.getTransactionID(), t.getCardID(), t.getTransactionDate(),
                    t.getTransactionAmount(), t.getNote(), "\n");
        }
    }
}