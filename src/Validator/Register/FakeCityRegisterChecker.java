package Validator.Register;

import Domain.Car;
import Domain.PassengerCar;
import Domain.VehicleDocument;
import exception.CityRegisterException;
import exception.TransportException;

public class FakeCityRegisterChecker implements CityRegisterChecker {

    public static final String GOOD_1 = "1000";
    public static final String GOOD_2 = "2000";
    public static final String BAD_1 = "1001";
    public static final String BAD_2 = "2001";
    public static final String ERROR_1 = "1002";
    public static final String ERROR_2 = "2002";
    public static final String ERROR_TRANSPORT1 = "1003";
    public static final String ERROR_TRANSPORT2 = "2003";



    public CityRegisterResponse checkCar(Car car)
            throws CityRegisterException, TransportException {
        CityRegisterResponse res = new CityRegisterResponse();

        if (car instanceof PassengerCar) {

            PassengerCar p = (PassengerCar) car;
            String pc = p.getCarLicensePlate();
            if (pc.equals(GOOD_1) || pc.equals(GOOD_2)) {
                res.setExisting(true);
                res.setTemporal(false);
            }
            if (pc.equals(BAD_1) || pc.equals(BAD_2)) {
                res.setExisting(false);
            }
            if (pc.equals(ERROR_1) || pc.equals(ERROR_2)) {
                CityRegisterException ex = new CityRegisterException("1", "CAR REGISTRY ERROR" + pc);
                throw ex;
            }
            if (pc.equals(ERROR_TRANSPORT1) || pc.equals(ERROR_TRANSPORT2)) {
                TransportException ex = new TransportException("Transport ERROR" + pc);
                throw ex;
            }
        }


        if(car instanceof VehicleDocument){
            res.setExisting(true);
            res.setTemporal(true);
        }
        System.out.println(res);

        return res;
    }
}
