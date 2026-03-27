package Domain;

import dao.CarOrderDao;
import dao.CarOrderDaoImp;

import java.time.LocalDate;

public class SaveCarOrder {

    public static void main(String [] args) throws Exception {
//        List<Street> d = new DictionaryDaoImp().findStreets("");
//        for(Street s : d){
//            System.out.println(s.getStreetName());
//        }
//        List<mvsOffice> mvso = new DictionaryDaoImp().findMVSOffices("020010000000");
//        for(mvsOffice m : mvso){
//            System.out.println(m.getOfficeName());
//        }
//
//        List<RegisterOffice> ro = new DictionaryDaoImp().findREGOffices("030020020000");
//        for(RegisterOffice r : ro){
//            System.out.println(r.getOfficeName());
//        }
//
//        List<MVSOfficesCity> oc1 = new DictionaryDaoImp().findCites("");
//        for(MVSOfficesCity c : oc1){
//            System.out.println(c.getCityId() + ":" + c.getCityName());
//        }
//        System.out.println("--->");
//        List<MVSOfficesCity> oc2 = new DictionaryDaoImp().findCites("020000000000");
//        for(MVSOfficesCity c : oc2){
//            System.out.println(c.getCityId() + " : " + c.getCityName());
//        }
//        System.out.println("--->");
//        List<MVSOfficesCity> oc3 = new DictionaryDaoImp().findCites("020010000000");
//        for(MVSOfficesCity c : oc3){
//            System.out.println(c.getCityId() + " : " + c.getCityName());
//        }
//        System.out.println("--->");
//        List<MVSOfficesCity> oc4 = new DictionaryDaoImp().findCites("020010010000");
//        for(MVSOfficesCity c : oc4){
//            System.out.println(c.getCityId() + " : " + c.getCityName());
//        }

        CarOrder c = buildCarOrder(10);
        CarOrderDao dao = new CarOrderDaoImp();
        Long id = dao.saveCarOrder(c);
        System.out.println(id);
    }

        static long saveCarOrder(CarOrder carOrder){
        long answer = 199;
        System.out.println("saveCarOrder");

        return answer;
        }

        public static CarOrder buildCarOrder(long id) {
            CarOrder co = new CarOrder();
            co.setVehicleId(id);

            RegisterOffice ro = new RegisterOffice(1L, "", "");
            co.setRegisterOfficeId(1L);

            Street street = new Street(1L, "First street");

            Address address = new Address( street, "building 28", "706" );

        //passenger1 car - BMW
        PassengerCar passenger = new PassengerCar("BMW", "X5", "Germany",
                2010, "XX6789XX", "black", "95eu", 678.99,
                1000);

        mvsOffice mvso = new mvsOffice(1L, "", "");
        passenger.setDocsDepartment(mvso);
        RegisterOffice ro1 = new RegisterOffice(1L, "", "");
        passenger.setRegisterOffice(ro1);
        passenger.setAddress(address);


            TruckCar truck = new TruckCar ("BMW", "X5", "Germany",
                    2010, "XX6789XX", "black", "95eu", 678.99,
                    1000);
            RegisterOffice ro2 = new RegisterOffice(2L, "", "");
            truck.setRegisterOffice(ro2);
            truck.setAddress(address);

            VehicleDocument doc1 = new VehicleDocument("BMW", "X5", "Germany",
                    2010, "XX6789XX", "black", "95eu", 678.99);
            doc1.setDocType("register doc1");
            doc1.setDocIssuedAt(LocalDate.of(2025, 12 ,29));
            doc1.setDocExpiresAt(LocalDate.of(2026, 1, 29));
            doc1.setActive(true);
            RegisterOffice ro3 = new RegisterOffice(3L, "", "");
            doc1.setRegisterOffice(ro3);
            doc1.setAddress(address);

        co.setPassenger(passenger);
        co.setTruck(truck);
        co.addDoc(doc1);

            return co;
    }
}
