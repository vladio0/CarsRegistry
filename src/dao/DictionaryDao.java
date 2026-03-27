package dao;

import Domain.MVSOfficesCity;
import Domain.RegisterOffice;
import Domain.Street;
import Domain.mvsOffice;
import exception.DaoException;

import java.util.List;

public interface DictionaryDao {
    List<Street> findStreets(String pattern) throws DaoException;
    List<mvsOffice> findMVSOffices(String cityId) throws DaoException;
    List<RegisterOffice> findREGOffices(String cityId) throws DaoException;
    List<MVSOfficesCity> findCites(String cityId) throws DaoException;


}
