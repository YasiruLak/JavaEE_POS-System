package lk.ijse.pos.servlet;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/9/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/

@WebServlet (urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerID = req.getParameter("customerID");
        String customerName = req.getParameter("customerName");
        String customerAddress = req.getParameter("customerAddress");
        String customerContact = req.getParameter("customerContact");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into Customer values(?,?,?,?)");
            preparedStatement.setObject(1, customerID);
            preparedStatement.setObject(2, customerName);
            preparedStatement.setObject(3, customerAddress);
            preparedStatement.setObject(4, customerContact);

            if (preparedStatement.executeUpdate() > 0){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                resp.setStatus(HttpServletResponse.SC_CREATED);
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "Successfully Added");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }

            connection.close();

        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 400);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            resp.setStatus(HttpServletResponse.SC_OK);
            e.printStackTrace();
        }
    }
}
