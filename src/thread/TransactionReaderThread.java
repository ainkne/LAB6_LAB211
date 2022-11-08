package thread;

import function.Validation;
import model.Bank;
import model.Transaction;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.Scanner;

public class TransactionReaderThread implements Runnable{
    Scanner sc;
    File file;

    public TransactionReaderThread() {
        file = new File("lib/transactions.txt");
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }

    private void reader(){
        if (!file.exists()) return;
        while (sc.hasNextLine()) {
            Transaction transaction = new Transaction();
            String line = sc.nextLine();
            String[] split = line.split(", ");
            transaction.setTransactionID(split[0]);
            transaction.setCardID(split[1]);
            transaction.setTransactionDate(LocalDate.parse(split[2], Validation.dFormatter));
            transaction.setTransactionAmount(Double.parseDouble(split[3]));
            transaction.setNote(split[4]);
            Bank.transactions.add(transaction);
        }
    }

    @Override
    public void run() {
        reader();
    }
}
