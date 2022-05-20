package lk.ijse.pos.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dto.CustomerDTO;
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
public class CustomerBOImpl implements CustomerBO {

    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public boolean addCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                customerDTO.getCusId(),customerDTO.getCusName(),customerDTO.getCusAddress(),customerDTO.getCusContact()

        );
        return customerDAO.add(customer,connection);
    }

    @Override
    public ObservableList<CustomerDTO> getAllCustomer(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<Customer> customers = customerDAO.getAll(connection);

        ObservableList<CustomerDTO> obList = FXCollections.observableArrayList();

        for (Customer temp : customers) {
            CustomerDTO customerDTO = new CustomerDTO(
                    temp.getId(),temp.getName(),temp.getAddress(),temp.getContact()
            );

            obList.add(customerDTO);
        }
        return obList;

    }

    @Override
    public CustomerDTO searchCustomer(String id, Connection connection) throws SQLException, ClassNotFoundException {

        Customer customer = customerDAO.search(id, connection);

        CustomerDTO customerDTO = new CustomerDTO(
                customer.getId(),customer.getName(),customer.getAddress(),customer.getContact()
        );
        return customerDTO;
    }

    @Override
    public boolean deleteCustomer(Connection connection, String id) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(id, connection);
    }

    @Override
    public boolean updateCustomer(Connection connection, CustomerDTO customerDTO) throws SQLException, ClassNotFoundException {
        Customer customer = new Customer(
                customerDTO.getCusId(),customerDTO.getCusName(),customerDTO.getCusAddress(),customerDTO.getCusContact()

        );
        return customerDAO.update(customer,connection);
    }


}
