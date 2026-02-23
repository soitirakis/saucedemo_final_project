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
        String itemToFormat = itemToLower.trim().replaceAll("\\s+", "-"); //item-name-xyz
        return itemToFormat;
    }

    public static String generateItemToAddToCart(String item) {
        String prefix = "add-to-cart-"; //add-to-cart-item-name
        String result = prefix + generateItemFormat(item);
        return result;
    }
    public static String generateItemToRemoveFromCart(String item) {
        String prefix = "remove-"; //remove-item-name
        String result = prefix + generateItemFormat(item);
        return result;
    }

    public static String generateRandomName() {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder name = new StringBuilder();
        Random rnd = new Random();
        while (name.length() < 10) {
            int index = (int) (rnd.nextFloat() * characters.length());
            name.append(characters.charAt(index));
        }
        return name.toString();
    }
}
