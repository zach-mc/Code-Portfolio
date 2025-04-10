import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SimpleRegex {
    public static void main(String[] args) {

        String input = "371234567890123";
        checkAmex(input);

    }

    public static void checkAmex(String input){
        Pattern amexPattern = Pattern.compile("^3[4,7][\\d]{13}");
        Matcher amexMatcher = amexPattern.matcher(input);

        System.out.println(amexMatcher.matches());
    }
}