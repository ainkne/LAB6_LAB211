package thread;

import model.Bank;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CardWriterThread implements Runnable{

    FileWriter file;

    private void writer(){
        try {
            file = new FileWriter("lib/cards.txt");
            try (
                    BufferedWriter bufferedWriter = new BufferedWriter(file)
            ){
                Bank.cards.forEach(card -> {
                    try {
                        bufferedWriter.write(card + "");
                        bufferedWriter.newLine();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        writer();
    }
}
