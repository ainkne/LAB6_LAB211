package function;

import model.Bank;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.function.Predicate;

public class Validation {
    static Bank bank = new Bank();

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy").withLocale(Locale.ENGLISH);

    public static final String dateFormat = "d/M/yyyy";
    public static final String dateReaderFormat = "yyyy-MM-dd";
    public static DateTimeFormatter dFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withLocale(Locale.ENGLISH);
    public static Predicate<String> isCardIdValid = id -> bank.findCardById(id) < 0;
    public static Predicate<String> isCardNumValid = num -> bank.findCardByCardNum(num) < 0;
}
