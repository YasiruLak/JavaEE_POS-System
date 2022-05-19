package lk.ijse.pos.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class CrudUtil {

    private static PreparedStatement getPreparedStatement(Connection connection,String sql, Object... args) throws SQLException, ClassNotFoundException {
        PreparedStatement pstm = connection.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i + 1, args[i]);
        }
        return pstm;
    }

    public static boolean  executeUpdate(Connection connection, String sql, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(connection, sql, args).executeUpdate() > 0;
    }

    public static ResultSet executeQuery(Connection connection, String sql, Object... args) throws SQLException, ClassNotFoundException {
        return getPreparedStatement(connection, sql, args).executeQuery();
    }
}
