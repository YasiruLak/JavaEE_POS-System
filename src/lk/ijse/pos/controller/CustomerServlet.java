package lk.ijse.pos.controller;

import javax.annotation.Resource;
import javax.json.*;
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
import java.sql.ResultSet;
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
        try {

        String option = req.getParameter("option");
        String customerID = req.getParameter("customerID");
        resp.setContentType("application/json");
        Connection connection = dataSource.getConnection();
        PrintWriter writer = resp.getWriter();

        resp.addHeader("Access-Control-Allow-Origin", "*");

        switch (option){
            case "SEARCH":

                Connection connection1 = dataSource.getConnection();
                PreparedStatement preparedStatement = connection1.prepareStatement("SELECT * FROM Customer where id=?");
                preparedStatement.setObject(1,customerID);
                ResultSet resultSet1 = preparedStatement.executeQuery();
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                while (resultSet1.next()){
                    String id = resultSet1.getString(1);
                    String name = resultSet1.getString(2);
                    String address = resultSet1.getString(3);
                    String contact = resultSet1.getString(4);

                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("id", id);
                    objectBuilder.add("name", name);
                    objectBuilder.add("address", address);
                    objectBuilder.add("contact", contact);
                    arrayBuilder.add(objectBuilder.build());
                }

                JsonObjectBuilder response1 = Json.createObjectBuilder();
                response1.add("status", 200);
                response1.add("message", "Done");
                response1.add("data", arrayBuilder.build());
                writer.print(response1.build());

                break;

            case "GETALL":

                ResultSet resultSet = connection.prepareStatement("SELECT * FROM Customer").executeQuery();
                JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                while (resultSet.next()){
                    String id = resultSet.getString(1);
                    String name = resultSet.getString(2);
                    String address = resultSet.getString(3);
                    String contact = resultSet.getString(4);

                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("id", id);
                    objectBuilder.add("name", name);
                    objectBuilder.add("address", address);
                    objectBuilder.add("contact", contact);
                    arrayBuilder1.add(objectBuilder.build());
                }

                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("status", 200);
                response.add("message", "Done");
                response.add("data", arrayBuilder1.build());
                writer.print(response.build());

                break;



        }

        connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

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

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String customerID = req.getParameter("customerID");
        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");
        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Delete from Customer where id=?");
            preparedStatement.setObject(1, customerID);

            if (preparedStatement.executeUpdate() > 0){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status",200);
                objectBuilder.add("data","");
                objectBuilder.add("message","Successfully Deleted");
                writer.print(objectBuilder.build());
            }else {
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("data", "Wrong Id Inserted");
                objectBuilder.add("message", "");
                writer.print(objectBuilder.build());
            }

            connection.close();

        } catch (SQLException e) {
            resp.setStatus(200);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            e.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        JsonReader reader = Json.createReader(req.getReader());
        JsonObject jsonObject = reader.readObject();
        String customerID = jsonObject.getString("id");
        String customerName = jsonObject.getString("name");
        String customerAddress = jsonObject.getString("address");
        String customerContact = jsonObject.getString("contact");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("Application/json");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Customer SET name=?,address=?,contact=? WHERE id=?");
            preparedStatement.setObject(1,customerName);
            preparedStatement.setObject(2,customerAddress);
            preparedStatement.setObject(3,customerContact);
            preparedStatement.setObject(4,customerID);

            if (preparedStatement.executeUpdate() > 0){
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 200);
                objectBuilder.add("message", "Successfully Updated");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }else{
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status", 400);
                objectBuilder.add("message", "Update Failed");
                objectBuilder.add("data", "");
                writer.print(objectBuilder.build());
            }

            connection.close();

        } catch (SQLException e) {
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", 500);
            objectBuilder.add("message", "Update Failed");
            objectBuilder.add("data", e.getLocalizedMessage());
            writer.print(objectBuilder.build());
            e.printStackTrace();
        }
    }
}
