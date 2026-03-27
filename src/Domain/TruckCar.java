package Domain;

public class TruckCar extends Car{

    public TruckCar() {
    }

    public TruckCar(String carMark, String carModel, String carCountry, int carYear,
                    String carLicensePlate, String carColour, String carFuelType,
                    double carWeight, int carMaxAllowedWeight) {
        super(carMark, carModel, carCountry, carYear, carLicensePlate, carColour,
                    carFuelType, carWeight, carMaxAllowedWeight);
    }


}
