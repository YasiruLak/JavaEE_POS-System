package lk.ijse.pos.bo.custom.impl;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.dao.custom.OrderDAO;
import lk.ijse.pos.dao.custom.OrderDetailsDAO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.OrdersDTO;
import lk.ijse.pos.entity.Item;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

import javax.annotation.Resource;
import javax.sql.DataSource;
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
public class OrderBOImpl implements OrderBO {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    private final CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERS);
    private final OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);

    @Override
    public boolean saveOrder(Connection connection, OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {
        Connection con = null;

        connection = dataSource.getConnection();

        boolean orderAvailable = orderDAO.ifOrderExist(ordersDTO.getOrderId(), connection);
        if (orderAvailable) {
            return false;
        }

        connection.setAutoCommit(false);
        Orders orders = new Orders(ordersDTO.getOrderId(), ordersDTO.getcId(), ordersDTO.getOrderDate(),
                ordersDTO.getTotal(), ordersDTO.getDiscount(), ordersDTO.getSubTotal());

        boolean orderAdded = orderDAO.add(orders, connection);

        if (!orderAdded) {
            connection.rollback();
            connection.setAutoCommit(true);
            return false;
        }

        for (OrderDetailsDTO detailsDTO : ordersDTO.getOrderDetail()) {
            OrderDetails orderDetails = new OrderDetails(detailsDTO.getoId(), detailsDTO.getiCode(), detailsDTO.getoQty(), detailsDTO.getPrice(), detailsDTO.getTotal());
            boolean orderDetailsAdded = orderDetailsDAO.add(orderDetails, connection);
            if (!orderDetailsAdded) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            Item search = itemDAO.search(detailsDTO.getiCode(), connection);
            search.setQtyOnHand(search.getQtyOnHand() - detailsDTO.getoQty());
            boolean update = itemDAO.update(search, connection);
            if (!update) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }
        }

        connection.commit();
        connection.setAutoCommit(true);
        return true;

    }


    @Override
    public ObservableList<OrdersDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public OrdersDTO searchOrder(String orderId, Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String generateNewOrderId(Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId(connection);
    }

    @Override
    public ArrayList<CustomerDTO> getAllCustomers(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }


}
