package lk.ijse.pos.bo.custom.impl;

import javafx.collections.FXCollections;
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
import lk.ijse.pos.entity.Customer;
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
    public boolean saveOrder(Connection connection, OrdersDTO ordersDTO) {

        Connection con = null;

        try {

            con = connection;

            connection.setAutoCommit(false);

            boolean orderAvailable = false;
            orderAvailable = orderDAO.ifOrderExist(ordersDTO.getOrderId(), connection);

            if (orderAvailable) {
                return false;
            }

            Orders orders = new Orders(ordersDTO.getOrderId(), ordersDTO.getcId(), ordersDTO.getOrderDate(),
                    ordersDTO.getTotal(), ordersDTO.getDiscount(), ordersDTO.getSubTotal());

            boolean orderAdded = orderDAO.add(orders, connection);

            if (orderAdded) {
                if (saveOrderDetail(connection, ordersDTO)) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }

            } else {
                connection.rollback();
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return  true;
    }

    @Override
    public boolean saveOrderDetail(Connection connection, OrdersDTO ordersDTO) throws SQLException, ClassNotFoundException {

        for (OrderDetailsDTO item : ordersDTO.getOrderDetail()) {

            OrderDetails orderDetails = new OrderDetails(
                    item.getoId(), item.getiCode(), item.getoQty(), item.getPrice(), item.getTotal());

            boolean ifOrderDetailSaved = orderDetailsDAO.add(orderDetails,
                    connection
            );
            if (ifOrderDetailSaved){
                return updateQtyOnHand(connection, item.getiCode(), item.getoQty());
            }else {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateQtyOnHand(Connection connection, String id, int qty) throws SQLException, ClassNotFoundException {
        return itemDAO.updateQtyOnHand(connection, id, qty);
    }


    @Override
    public ObservableList<OrdersDTO> getAllOrders(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<Orders> orders = orderDAO.getAll(connection);

        ObservableList<OrdersDTO> obList = FXCollections.observableArrayList();

        for (Orders temp : orders) {
            OrdersDTO ordersDTO = new OrdersDTO(
                    temp.getOrderId(),temp.getcId(),temp.getOrderDate(),temp.getTotal(), temp.getDiscount(), temp.getSubTotal()
            );

            obList.add(ordersDTO);
        }
        return obList;
    }

    @Override
    public ObservableList<OrderDetailsDTO> getAllOrderDetails(Connection connection) throws SQLException, ClassNotFoundException {
        ObservableList<OrderDetails> orderDetails = orderDetailsDAO.getAll(connection);

        ObservableList<OrderDetailsDTO> obList = FXCollections.observableArrayList();

        for (OrderDetails temp : orderDetails) {
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO(
                    temp.getoId(), temp.getiCode(), temp.getQty(), temp.getPrice(), temp.getTotal()
            );

            obList.add(orderDetailsDTO);
        }
        return obList;
    }

    @Override
    public ArrayList<OrderDetailsDTO> searchOrderDetails(String orderId, Connection connection) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetails> orderDetails = orderDetailsDAO.searchOrderDetail(orderId, connection);
        ArrayList<OrderDetailsDTO> orderDetailDTOS = new ArrayList<>();
        for (OrderDetails orderDetail : orderDetails) {
            orderDetailDTOS.add(new OrderDetailsDTO(
                    orderDetail.getoId(),
                    orderDetail.getiCode(),
                    orderDetail.getQty(),
                    orderDetail.getPrice(),
                    orderDetail.getTotal()
            ));
        }
        return orderDetailDTOS;
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

    @Override
    public int ordersCount(Connection connection) throws SQLException, ClassNotFoundException {
        return orderDAO.ordersCount(connection);
    }


}
