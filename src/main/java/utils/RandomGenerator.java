package utils;

import java.util.Locale;
import java.util.Random;

public class RandomGenerator {

    public static int generateNumber(int max) {
        Random random = new Random();
        int number = random.nextInt(max);
        return number;
    }

    public static String generateItemFormat(String item) {
        String itemToLower = item.toLowerCase(Locale.ENGLISH);
        String itemToFormat = itemToLower.replaceAll(" ", "-");
        return itemToFormat;
    }

    public static String generateItemToAddToCart(String item) {
        String prefix = "add-to-cart-";
        String result = prefix + generateItemFormat(item);
        return result;
    }
    public static String generateItemToRemoveFromCart(String item) {
        String prefix = "remove-";
        String result = prefix + generateItemFormat(item);
        return result;
    }
}
