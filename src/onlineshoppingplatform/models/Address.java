package onlineshoppingplatform.models;

public class Address {

    private final String street;
    private final String state;
    private final String city;
    private final String zipCode;

    public Address(String street, String state, String city, String zipcode){
        this.state = state;
        this.street = street;
        this.city = city;
        this.zipCode = zipcode;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", state,street,city,zipCode);
    }
}
