package dao;

import Domain.CarOrder;
import exception.DaoException;

import java.util.List;

public interface CarOrderDao {
    Long saveCarOrder(CarOrder co) throws DaoException;

    List<CarOrder> gerCarOrders() throws DaoException;
}
