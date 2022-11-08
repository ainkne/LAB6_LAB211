package view;

import controller.BankManagement;
import controller.TransactionManagement;
import exception.CardException;
import exception.TransactionDateException;
import function.Input;

public class Menu {
    private static final String[] menuContents = {"SHOW CARDS", "ADD CARD", "SHOW TRANSACTIONS", "ADD TRANSACTION", "QUIT"};
    BankManagement bankManagement = new BankManagement();
    TransactionManagement transactionManagement = new TransactionManagement();
    public Menu() {}
    public void execute() throws TransactionDateException, CardException {
        System.out.println("CARD MANAGEMENT PROGRAM");
        while (true){
            System.out.println();
            for (int i = 0; i < menuContents.length; i++){
                System.out.println((i+1) + ". " + menuContents[i]);
            }
            System.out.println();
            int choice = Input.getUserChoice("Enter your choice: ", menuContents.length);
            switch (choice){
                case 1 -> bankManagement.displayCards();
                case 2 -> bankManagement.inputCard();
                case 3 -> transactionManagement.printTransaction();
                case 4 -> transactionManagement.addTransaction();
                case 5 -> System.exit(0);
            }
        }
    }
}
