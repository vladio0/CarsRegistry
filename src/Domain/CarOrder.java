package Domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarOrder {
    private long vehicleId;
    private LocalDateTime carOrderDate;
    private CarOrderStatus carOrderStatus;
    private PassengerCar passenger;
    private TruckCar truck;
    private Address p_address;
    private Address t_address;
    public List<VehicleDocument> docs;
    private long registerOfficeId;
    private LocalDate registrationDate;
    private VehicleDocument vehicleDocument;


    public void addDoc(VehicleDocument doc) {
        if (docs == null) {
            docs = new ArrayList<>(5);
        }
        docs.add(doc);
    }

    public List<VehicleDocument> getDocs() {
        return docs;
    }

    public void setDocs(List<VehicleDocument> docs) {
        this.docs = docs;
    }

    public long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public LocalDateTime getCarOrderDate() {
        return carOrderDate;
    }

    public void setCarOrderDate(LocalDateTime carOrderDate) {
        this.carOrderDate = carOrderDate;
    }

    public CarOrderStatus getCarOrderStatus() {
        return carOrderStatus;
    }

    public PassengerCar getPassenger() {
        return passenger;
    }

    public void setPassenger(PassengerCar passenger) {
        this.passenger = passenger;
    }

    public TruckCar getTruck() {
        return truck;
    }

    public void setTruck(TruckCar truck) {
        this.truck = truck;
    }

    public Address getP_address() {
        return p_address;
    }

    public void setP_address(Address p_address) {
        this.p_address = p_address;
    }

    public Address getT_address() {
        return t_address;
    }

    public void setT_address(Address t_address) {
        this.t_address = t_address;
    }

    public void setCarOrderStatus(CarOrderStatus carOrderStatus) {
        this.carOrderStatus = carOrderStatus;
    }

    public long getRegisterOfficeId() {
        return registerOfficeId;
    }

    public void setRegisterOfficeId(long registerOfficeId) {
        this.registerOfficeId = registerOfficeId;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
