package testdata.classes;

import utils.Reader;

public class CheckoutInformation {
    private String firstName;
    private String lastName;
    private String zipCode;

    public CheckoutInformation(String filename) {
        this.firstName = Reader.json(filename).get("firstName").toString();
        this.lastName = Reader.json(filename).get("lastName").toString();
        this.zipCode = Reader.json(filename).get("zipCode").toString();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
