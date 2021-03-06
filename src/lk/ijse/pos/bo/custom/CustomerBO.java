package lk.ijse.pos.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;

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
public interface CustomerBO extends SuperBO {

    boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    ObservableList<CustomerDTO> getAllCustomer(Connection connection) throws SQLException, ClassNotFoundException;

    CustomerDTO searchCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException;

    boolean deleteCustomer(Connection connection, String id) throws SQLException, ClassNotFoundException;

    boolean updateCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException;

    int countCustomer(Connection connection) throws SQLException, ClassNotFoundException;

}
