package Domain;

public class Address {
    private Street street;
    private String building;
    private String apartment;

    public Address() {
    }

    public Address(Street street, String building, String apartment) {
        this.street = street;
        this.building = building;
        this.apartment = apartment;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getApartment() {
        return apartment;
    }

    public void setApartment(String apartment) {
        this.apartment = apartment;
    }
}
