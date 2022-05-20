package lk.ijse.pos.dao;

import javafx.collections.ObservableList;
import lk.ijse.pos.entity.Customer;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public interface CrudDAO <T, ID>extends SuperDAO{

    boolean add(T t, Connection connection) throws SQLException, ClassNotFoundException;

    boolean delete(ID id, Connection connection) throws SQLException, ClassNotFoundException;

    boolean update(T t, Connection connection) throws SQLException, ClassNotFoundException;

    T search(ID id, Connection connection) throws SQLException, ClassNotFoundException;

    ObservableList<T> getAll(Connection connection) throws SQLException, ClassNotFoundException;
}
