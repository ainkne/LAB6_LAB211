package thread;

import model.Bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class TransactionWriterThread implements Runnable{
    FileWriter file;
    private void writer(){
        try {
            file = new FileWriter("lib/transactions.txt");
            try (
                    BufferedWriter bufferedWriter = new BufferedWriter(file)
            ){
                Bank.transactions.forEach(transaction -> {
                    try {
                        bufferedWriter.write(transaction + "");
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        writer();
    }
}
