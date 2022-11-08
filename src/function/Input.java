package function;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Input {
    public static String inputString(String prompt){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.print(prompt);
            String str = sc.nextLine().trim();
            if(!str.isEmpty()) return str;
            System.out.println("You enter nothing,try again!");
            System.out.print(prompt);
        }
    }

    public static int getUserChoice(String prompt, int nOfChoices){
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.print(prompt);
            while (!sc.hasNextInt()) {
                System.out.println("Invalid input, try again!");
                System.out.print(prompt);
                sc.next();
            }
            choice = sc.nextInt();
        } while (choice <= 0 || choice > nOfChoices);
        return choice;
    }

    public static String inputCardId(String prompt){
        while (true){
            String id = inputString(prompt);
            if (Validation.isCardIdValid.test(id)) return id;
            System.out.println("Card with ID: \"" + id + "\" is already exist!" );
        }
    }

    public static String inputCardIdForTransaction(String prompt){
        while (true){
            String id = inputString(prompt);
            if (!Validation.isCardIdValid.test(id)) return id;
            System.out.println("Card with ID: \"" + id + "\" is NOT exist!" );
        }
    }

    public static String inputCardNum(String prompt){
        while (true){
            String cardNum = inputString(prompt);
            if (Validation.isCardNumValid.test(cardNum)) return cardNum;
            System.out.println("Card with Number: \"" + cardNum + "\" is already exist!" );
        }
    }


    public static String inputDate(String prompt) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print(prompt);
            try {
                return LocalDate.parse(sc.nextLine()
                                , DateTimeFormatter.ofPattern(Validation.dateFormat))
                        .format(DateTimeFormatter.ofPattern(Validation.dateFormat));
            } catch (DateTimeParseException | NullPointerException e) {
                System.out.println("Invalid date, try again! EX: (01/02/2022)");
            }
        }
    }

    public static double inputAmount(String prompt){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.print(prompt);
                double amount =  Double.parseDouble(sc.nextLine().trim());
                if (amount > 10) return amount;
                System.out.println("Amount should be at least $10");
            } catch (NumberFormatException e){
                System.out.println("Invalid amount, try again!");
            }
        }
    }
    public static double inputAmountOfTrans(String prompt){
        Scanner sc = new Scanner(System.in);
        while (true){
            try {
                System.out.print(prompt);
                double amount =  Double.parseDouble(sc.nextLine().trim());
                if (amount > 0) return amount;
                System.out.println("Amount should be more than 0");
            } catch (NumberFormatException e){
                System.out.println("Invalid amount, try again!");
            }
        }
    }
    public static String inputYesNo(String prompt){
        while (true){
            String input = inputString(prompt);
            if(input.equalsIgnoreCase("n") || input.equalsIgnoreCase("no")) return "n";
            if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) return "y";
            else System.out.println("Not valid, (Y) to continue, (N) to discard");
        }
    }
}
