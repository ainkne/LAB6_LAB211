package model;

import thread.CardReaderThread;
import thread.TransactionReaderThread;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    public static List<Card> cards = new ArrayList<>();

    public static List<Transaction> transactions = new ArrayList<>();

    public static Map<Integer, List<Card>> mapToDisplayCards = new HashMap<>();

    public Bank() {}

    public void loadCardInfo(){
        CardReaderThread readerThread = new CardReaderThread();
        new Thread(readerThread).start();
        try {
            Thread.sleep(0, 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadTransactionInfo(){
        TransactionReaderThread readerThread = new TransactionReaderThread();
        new Thread(readerThread).start();
        try {
            Thread.sleep(0, 10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    public int findCardById(String id){
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getCardID().equals(id)) return i;
        }
        return -1;
    }
    public int findCardByCardNum(String cardNum){
        for (int i = 0; i < cards.size(); i++){
            if (cards.get(i).getCardNumber().equals(cardNum)) return i;
        }
        return -1;
    }
}
