package lk.ijse.pos.dao.custom;

import lk.ijse.pos.dao.CrudDAO;
import lk.ijse.pos.entity.OrderDetails;
import lk.ijse.pos.entity.Orders;

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
public interface OrderDetailsDAO extends CrudDAO<OrderDetails, String, Connection> {
    ArrayList<OrderDetails> searchOrderDetail(String id, Connection connection) throws SQLException, ClassNotFoundException;
}
