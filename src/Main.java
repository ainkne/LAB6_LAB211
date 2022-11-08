import exception.CardException;
import exception.TransactionDateException;
import view.Menu;

public class Main {
    public static void main(String[] args) throws TransactionDateException, CardException {
        Menu menu = new Menu();
        menu.execute();
    }
}
