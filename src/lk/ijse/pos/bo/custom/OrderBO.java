package lk.ijse.pos.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.OrdersDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/20/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public interface OrderBO extends SuperBO {

    boolean saveOrder(Connection connection, OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;

    boolean saveOrderDetail(Connection connection, OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException;

    boolean updateQtyOnHand(Connection connection, String id, int qty) throws SQLException, ClassNotFoundException;

    ObservableList<OrdersDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException;

    ObservableList<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException;

    ArrayList<OrderDetailsDTO> searchOrderDetails(String orderId, Connection connection) throws SQLException, ClassNotFoundException;

    String generateNewOrderId(Connection connection)throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDTO> getAllCustomers(Connection connection)throws SQLException, ClassNotFoundException;

    ArrayList<ItemDTO> getAllItems(Connection connection)throws SQLException, ClassNotFoundException;

    int ordersCount(Connection connection) throws SQLException, ClassNotFoundException;
}
