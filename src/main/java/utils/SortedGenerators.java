package utils;

import java.util.ArrayList;
import java.util.List;

public class SortedGenerators {
    public static List<String> isSortedDescending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        sortedList.sort(String.CASE_INSENSITIVE_ORDER.reversed());

        return sortedList;
    }

    public static List<String> isSortedAscending(List<String> list) {
        List<String> sortedList = new ArrayList<>(list);
        sortedList.sort(String.CASE_INSENSITIVE_ORDER);

        return sortedList;
    }

    public static boolean isPriceSorted(List<String> list) {
        List<Double> listConverted = PriceConvertor.convertPrice(list);
        for (int i = 0; i < listConverted.size() - 1; i++) {
            if (listConverted.get(i) < listConverted.get(i + 1)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPriceSortedDescending(List<String> list) {
        List<Double> listConverted = PriceConvertor.convertPrice(list);
        for (int  i = 0; i < listConverted.size() - 1; i++) {
            if (listConverted.get(i) > listConverted.get(i + 1)) {
                return true;
            }
        }
        return false;
    }
}
