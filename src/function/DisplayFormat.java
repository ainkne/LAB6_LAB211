package function;

public class DisplayFormat {
    public static String transactionFormat = "%-20s%-15s%-25s%-30s%-45s%-1s";
    public static String creditCardFormat = "%-10s%-25s%-20s%-20s%-20s%-10s%-1s";
    public static String debitCardFormat = "%-10s%-25s%-20s%-20s%-20s%-1s";
    public static String  atmCardFormat = "%-10s%-25s%-20s%-20s%-1s";
    public static void displayTransaction(){
        System.out.printf(transactionFormat, "Transaction ID", "Card ID", "Transaction Date", "Transaction Amount(USD)", "Note", "\n");
    }
    public static void displayCreditCard(){
        System.out.printf(creditCardFormat, "Card ID", "Card Number", "Date Release", "Date Valid", "Amount Due(USD)", "Amount Max(USD)", "\n");
    }
    public static void displayDebitCard(){
        System.out.printf(debitCardFormat, "Card ID", "Card Number", "Date Release", "Date Valid", "Amount Remain(USD)", "\n");
    }
    public static void displayATMCard(){
        System.out.printf(atmCardFormat, "Card ID", "Card Number", "Date Release", "Amount(USD)", "\n");
    }
}
