package lk.ijse.pos.servlet;

import javax.annotation.Resource;
import javax.json.Json;
import javax.json.JsonArrayBuilder;
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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/10/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/

@WebServlet(urlPatterns = "/item")
public class ItemServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try{

            String option = req.getParameter("option");
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            resp.addHeader("Access-Control-Allow-Origin", "*");

            switch (option){
                case "SEARCH":

                    break;

                case "GETALL":

                    ResultSet resultSet = connection.prepareStatement("SELECT * FROM Item").executeQuery();
                    JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                    while (resultSet.next()){
                        String itemCode = resultSet.getString(1);
                        String name = resultSet.getString(2);
                        String qtyOnHand = resultSet.getString(3);
                        String price = resultSet.getString(4);

                        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                        objectBuilder.add("itemCode", itemCode);
                        objectBuilder.add("name", name);
                        objectBuilder.add("qtyOnHand", qtyOnHand);
                        objectBuilder.add("price", price);
                        arrayBuilder.add(objectBuilder.build());
                    }

                    JsonObjectBuilder response = Json.createObjectBuilder();
                    response.add("status", 200);
                    response.add("message", "Done");
                    response.add("data", arrayBuilder.build());
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

        String itemCode = req.getParameter("itemCode");
        String itemName = req.getParameter("itemName");
        String itemQuantity = req.getParameter("itemQuantity");
        String itemPrice = req.getParameter("itemPrice");

        resp.addHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = resp.getWriter();
        resp.setContentType("application/json");

        try {
            Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("Insert into Item values(?,?,?,?)");
            preparedStatement.setObject(1, itemCode);
            preparedStatement.setObject(2, itemName);
            preparedStatement.setObject(3, itemQuantity);
            preparedStatement.setObject(4, itemPrice);

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


    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
