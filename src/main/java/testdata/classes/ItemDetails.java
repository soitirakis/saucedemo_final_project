package testdata.classes;

import utils.Reader;

public class ItemDetails {
    private String title;
    private String description;
    private String price;
    private String tax;
    private String totalPrice;
    private String priceTag;

    public ItemDetails(String filename) {
        this.title = Reader.json(filename).get("title").toString();
        this.description = Reader.json(filename).get("description").toString();
        this.price = Reader.json(filename).get("price").toString();
        this.tax = Reader.json(filename).get("tax").toString();
        this.totalPrice = Reader.json(filename).get("totalPrice").toString();
        this.priceTag = Reader.json(filename).get("priceTag").toString();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
    }
}
