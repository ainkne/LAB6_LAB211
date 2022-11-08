package thread;

import function.Validation;
import model.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class CardReaderThread implements Runnable{
    CardCategory category;
    Scanner sc;
    File file;
    public CardReaderThread() {
        file = new File("lib/cards.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void reader(){
        if (!file.exists()) return;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] split = line.split(", ");
            category = CardCategory.valueOf(Integer.parseInt(split[0]));
            switch (category) {
                case CREDIT_CARD -> {
                    CreditCard creditCard = new CreditCard();
                    creditCard.setType(CardCategory.CREDIT_CARD.getType());
                    creditCard.setCardID(split[1]);
                    creditCard.setCardNumber(split[2]);
                    creditCard.setDateRelease(LocalDate.parse(split[3], Validation.dFormatter));
                    creditCard.setDateValid(LocalDate.parse(split[4], Validation.dFormatter));
                    creditCard.setAmountDue(Double.parseDouble(split[5]));
                    creditCard.setAmountMax(Double.parseDouble(split[6]));
                    Bank.cards.add(creditCard);
                }
                case DEBIT_CARD -> {
                    DebitCard debitCard = new DebitCard();
                    debitCard.setType(CardCategory.DEBIT_CARD.getType());
                    debitCard.setCardID(split[1]);
                    debitCard.setCardNumber(split[2]);
                    debitCard.setDateRelease(LocalDate.parse(split[3], Validation.dFormatter));
                    debitCard.setDateValid(LocalDate.parse(split[4], Validation.dFormatter));
                    debitCard.setAmountRemain(Double.parseDouble(split[5]));
                    Bank.cards.add(debitCard);
                }
                case ATM_CARD -> {
                    ATMCard atmCard = new ATMCard();
                    atmCard.setType(CardCategory.ATM_CARD.getType());
                    atmCard.setCardID(split[1]);
                    atmCard.setCardNumber(split[2]);
                    atmCard.setDateRelease(LocalDate.parse(split[3], Validation.dFormatter));
                    atmCard.setAmount(Double.parseDouble(split[4]));
                    Bank.cards.add(atmCard);
                }
            }
        }
    }

    @Override
    public void run() {
        reader();
    }
}
