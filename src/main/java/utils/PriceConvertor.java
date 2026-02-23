package utils;

import java.util.ArrayList;
import java.util.List;

public class PriceConvertor {
    //convert price "$0.99" to double 0.99
    public static List<Double> convertPrice(List<String> price) {
        List<Double> priceList = new ArrayList<>();
        for (int i = 0; i < price.size(); i++) {
            String priceStr = price.get(i).substring(1);
            priceList.add(Double.parseDouble(priceStr));
        }
        return priceList;
    }
}
