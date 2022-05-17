package lk.ijse.pos.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class DBConnection {

    private static DBConnection dbConnection;
    private final Connection connection;

    private DBConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaeepos",
                "root",
                "19980611");
    }

    public static DBConnection getDbConnection() throws SQLException, ClassNotFoundException {
        return dbConnection == null? dbConnection = new DBConnection() : dbConnection;
    }

    public Connection getConnection(){
        return connection;
    }
}
