package dao;

import Config.Config;
import Domain.MVSOfficesCity;
import Domain.RegisterOffice;
import Domain.Street;
import Domain.mvsOffice;
import exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class DictionaryDaoImp implements DictionaryDao {

    private static final String GET_STREET = "SELECT street_code, street_name" +
            " FROM street WHERE UPPER(street_name) LIKE UPPER(?)";

    private static final String GET_MVS = "SELECT * " +
            " FROM mvs_office WHERE office_city_id = ?";

    private static final String GET_REGISTER = "SELECT * " +
            " FROM register_office WHERE r_office_city_id = ?";

    private static final String GET_CITY = "SELECT * " +
            " FROM offices_struct WHERE city_id like ? and city_id<> ?";

    //TODO refactoring the method
    private Connection getConnection() throws SQLException {
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    public List<Street> findStreets(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_STREET)) {

            stmt.setString(1, "%" + pattern + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Street str = new Street(
                        rs.getLong("street_code"),
                        rs.getString("street_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    public List<mvsOffice> findMVSOffices(String cityId) throws DaoException {
        List<mvsOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_MVS)) {

            stmt.setString(1, cityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                mvsOffice str = new mvsOffice(
                        rs.getLong("office_id"),
                        rs.getString("office_city_id"),
                        rs.getString("office_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findREGOffices(String cityId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(DictionaryDaoImp.GET_REGISTER)) {

            stmt.setString(1, cityId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RegisterOffice str = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_city_id"),
                        rs.getString("r_office_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<MVSOfficesCity> findCites(String cityId) throws DaoException {
        List<MVSOfficesCity> result = new LinkedList<>();

        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_CITY)) {

            String param1 = buildParam(cityId);
            String param2 = "city_id";

            stmt.setString(1, param1);
            stmt.setString(2, param2);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                MVSOfficesCity str = new MVSOfficesCity(
                        rs.getString("city_id"),
                        rs.getString("city_name"));
                result.add(str);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex);
        }
        return result;
    }

    private String buildParam(String cityId) throws SQLException {
        if(cityId == null || cityId.trim().isEmpty()) {
            return "__0000000000";
        }
        if(cityId.endsWith("0000000000")){
            return(cityId.substring(0, 2) + "___0000000");
        }
        else if(cityId.endsWith("0000000")){
            return(cityId.substring(0, 5) + "___0000");
        }
        else if(cityId.endsWith("0000")){
            return(cityId.substring(0, 8) + "____");
        }
        throw new SQLException("INVALID PARAMETR 'cityId':" + cityId);
    }

}
