package Validator.Register;

import Answer.AnswerCityRegister;
import Domain.Car;
import Domain.CarOrder;
import Domain.VehicleDocument;
import exception.CityRegisterException;
import exception.TransportException;

public class CityRegisterValidator {
    public static final String IN_CODE = "NO_CAR_REGISTRY";

    private CityRegisterChecker carChecker;

    public CityRegisterValidator() {
        carChecker = new FakeCityRegisterChecker();
    }

    public AnswerCityRegister checkCityRegister(CarOrder co) {
        AnswerCityRegister ans = new AnswerCityRegister();

        ans.addItem(checkCar(co.getPassenger()));
        ans.addItem(checkCar(co.getTruck()));
        for (VehicleDocument truck : co.getDocs()) {
            ans.addItem(checkCar(truck));
        }
        return ans;
    }
        private AnswerCityRegisterItem checkCar(Car car){
            AnswerCityRegisterItem.CityStatus status = null;
            AnswerCityRegisterItem.CityError error = null;
        try {
                CityRegisterResponse tpm = carChecker.checkCar(car);
                status = tpm.isExisting() ?
                        AnswerCityRegisterItem.CityStatus.YES :
                        AnswerCityRegisterItem.CityStatus.NO;
        }
            catch(CityRegisterException ex){
                ex.printStackTrace(System.out);
                status = AnswerCityRegisterItem.CityStatus.ERROR;
                error = new AnswerCityRegisterItem.CityError(ex.getCode(), ex.getMessage());
            }
            catch(TransportException ex){
                ex.printStackTrace(System.out);
                status = AnswerCityRegisterItem.CityStatus.ERROR;
                error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
            }
            catch (Exception ex){
                ex.printStackTrace(System.out);
                status = AnswerCityRegisterItem.CityStatus.ERROR;
                error = new AnswerCityRegisterItem.CityError(IN_CODE, ex.getMessage());
            }
            AnswerCityRegisterItem ans = new AnswerCityRegisterItem(status, car, error);
            return ans;
    }
}

