package dao;

import Config.Config;
import Domain.*;
import exception.DaoException;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class CarOrderDaoImp implements CarOrderDao{

    private static final String INSERT_ORDER =
            "INSERT INTO public.vehicle(\n" +
                    "\tcar_order_status, car_order_date, p_car_mark, p_car_model, p_car_country, p_license_plate, p_car_year, p_car_colour, p_fuel_type, p_weight, p_max_allowed_weight, p_street_code, p_building, p_apartment, t_car_mark, t_car_model, t_car_country, t_license_plate, t_car_year, t_car_colour, t_fuel_type, t_weight, t_max_allowed_weight, t_street_code, t_building, t_apartment, register_office_id, registration_date)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String INSERT_VEHICLE_DOCUMENT =
            "INSERT INTO public.vehicle_document(\n" +
                    "\tvehicle_id, d_car_mark, d_car_model, d_car_country, d_license_plate, d_car_year, d_car_colour, d_fuel_type, d_weight, doc_type, d_issued_at, d_expires_at, d_register_office_id, is_active, d_street_code, d_building, d_apartment)\n" +
                    "\tVALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

    private static final String SELECT_ORDERS =
            "SELECT * FROM vehicle WHERE car_order_status = 0 ORDER BY car_order_date";

    //TODO refactoring the method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    @Override
    public Long saveCarOrder(CarOrder co) throws DaoException {
        Long result = -1L;

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(INSERT_ORDER, new String[] {"vehicle_id"})) {

            con.setAutoCommit(false);
            try {
                //Order header
                stmt.setInt(1, CarOrderStatus.START.ordinal());
                stmt.setTimestamp(2, java.sql.Timestamp.valueOf(LocalDateTime.now()));

                //Passenger and Truck
                setParamsForPassengerANDTruck(stmt, 3, co.getPassenger());
                setParamsForPassengerANDTruck(stmt, 15, co.getTruck());

                //Registration
                stmt.setLong(27, co.getRegisterOfficeId());//register_office_id
                stmt.setDate(28, java.sql.Date.valueOf(LocalDate.now()));//registration_date

                stmt.executeUpdate();//return amount of info


                ResultSet gkRs = stmt.getGeneratedKeys();
                if (gkRs.next()) {
                    result = gkRs.getLong(1);
                }
                gkRs.close();

                saveDocument(con, co, result);

                con.commit();
            }catch(SQLException ex) {
                con.rollback();
                throw ex;
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<CarOrder> gerCarOrders() throws DaoException {
        List<CarOrder> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(SELECT_ORDERS)) {

            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                CarOrder co = new CarOrder();
                fillCarOrders(rs, co);
            }

            rs.close();
        }catch(SQLException ex) {
                 throw new DaoException(ex);
        }

            return result;
    }

    private void fillCarOrders(ResultSet rs, CarOrder co) throws SQLException {
            co.setVehicleId(rs.getLong("vehicle_id"));
    }

    private void saveDocument(Connection con, CarOrder co, Long coId) throws SQLException{
        try(PreparedStatement stmt = con.prepareStatement(INSERT_VEHICLE_DOCUMENT)) {
            for(VehicleDocument vehicleDocument : co.getDocs()) {
                stmt.setLong(1,coId);
                setParamsForDocuments(stmt, vehicleDocument);
                stmt.addBatch();//              |
            }                   //             \ /
            stmt.executeBatch();//for each command - smth changes amount
        }
    }

    private void setParamsForCar(PreparedStatement stmt, int start, Car car) throws SQLException {
        stmt.setString(start, car.getCarMark());
        stmt.setString(start + 1, car.getCarModel());
        stmt.setString(start + 2, car.getCarCountry());
        stmt.setString(start + 3, car.getCarLicensePlate());
        stmt.setInt(start + 4, car.getCarYear());
        stmt.setString(start + 5, car.getCarColour());
        stmt.setString(start + 6, car.getCarFuelType());
        stmt.setDouble(start + 7, car.getCarWeight());
    }

    private void setParamsForPassengerANDTruck(PreparedStatement stmt, int start, Car car) throws SQLException {
        setParamsForCar(stmt, start, car);
        stmt.setDouble(start + 8, car.getCarMaxAllowedWeight());
        setParamsForAddress(stmt, start + 9, car);
    }

    private void setParamsForDocuments(PreparedStatement stmt, VehicleDocument vehicleDocument) throws SQLException {
        setParamsForCar(stmt, 2, vehicleDocument);
        stmt.setString(10, vehicleDocument.getDocType());
        stmt.setDate(11, java.sql.Date.valueOf(vehicleDocument.getDocIssuedAt()));
        stmt.setDate(12, java.sql.Date.valueOf(vehicleDocument.getDocExpiresAt()));
        stmt.setLong(13, vehicleDocument.getRegisterOffice().getRegisterOfficeId());
        stmt.setBoolean(14, vehicleDocument.isActive());
        setParamsForAddress(stmt, 15, vehicleDocument);
    }

    private void setParamsForAddress(PreparedStatement stmt, int start, Car car) throws SQLException {
        Address car_address = car.getAddress();
        stmt.setLong(start, car_address.getStreet().getStreetCode());
        stmt.setString(start + 1, car_address.getBuilding());
        stmt.setString(start + 2, car_address.getApartment());
    }
}
