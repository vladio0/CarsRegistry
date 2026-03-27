package Domain;
import java.time.LocalDate;


public abstract class Car {
    private String carMark;
    private String carModel;
    private String carCountry;
    private int carYear;
    //private String driverName;
    //private LocalDate dateOfRegister; // дата реєстрації
    private String carLicensePlate; // номерний знак
    private String carColour;// колір машини
    private String carFuelType;
    private double carWeight;
    private int carMaxAllowedWeight;
    //private int mileage; // пробіг
    //private double price; // ціна
    private mvsOffice docsDepartment;// з якого відділення документи
    private RegisterOffice registerOffice;
    private Address address;


    public Car() {
    }


    //constructor for passenger and truck
    public Car(String carMark, String carModel, String carCountry,
               int carYear, String carLicensePlate, String carColour,
               String carFuelType, double carWeight, int carMaxAllowedWeight) {
        this.carMark = carMark;
        this.carModel = carModel;
        this.carCountry = carCountry;
        this.carYear = carYear;
        this.carLicensePlate = carLicensePlate;
        this.carColour = carColour;
        this.carFuelType = carFuelType;
        this.carWeight = carWeight;
        this.carMaxAllowedWeight = carMaxAllowedWeight;
    }


    //constructor for vehicle_document
    public Car(String carMark, String carModel, String carCountry,
               int carYear, String carLicensePlate, String carColour,
               String carFuelType, double carWeight) {
        this.carMark = carMark;
        this.carModel = carModel;
        this.carCountry = carCountry;
        this.carYear = carYear;
        this.carLicensePlate = carLicensePlate;
        this.carColour = carColour;
        this.carFuelType = carFuelType;
        this.carWeight = carWeight;
    }

    public String getCarMark() {
        return carMark;
    }

    public void setCarMark(String carMark) {
        this.carMark = carMark;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarCountry() {
        return carCountry;
    }

    public void setCarCountry(String carCountry) {
        this.carCountry = carCountry;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getCarColour() {
        return carColour;
    }

    public void setCarColour(String carColour) {
        this.carColour = carColour;
    }

    public String getCarFuelType() {
        return carFuelType;
    }

    public void setCarFuelType(String carFuelType) {
        this.carFuelType = carFuelType;
    }

    public double getCarWeight() {
        return carWeight;
    }

    public void setCarWeight(double carWeight) {
        this.carWeight = carWeight;
    }

    public int getCarMaxAllowedWeight() {
        return carMaxAllowedWeight;
    }

    public void setCarMaxAllowedWeight(int carMaxAllowedWeight) {
        this.carMaxAllowedWeight = carMaxAllowedWeight;
    }

    public mvsOffice getDocsDepartment() {
        return docsDepartment;
    }

    public void setDocsDepartment(mvsOffice docsDepartment) {
        this.docsDepartment = docsDepartment;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public RegisterOffice getRegisterOffice() {
        return registerOffice;
    }

    public void setRegisterOffice(RegisterOffice registerOffice) {
        this.registerOffice = registerOffice;
    }
}
