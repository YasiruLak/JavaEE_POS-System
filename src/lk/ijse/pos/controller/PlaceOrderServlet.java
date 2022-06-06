package lk.ijse.pos.controller;

import javafx.collections.ObservableList;
import lk.ijse.pos.bo.BOFactory;
import lk.ijse.pos.bo.custom.OrderBO;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.dto.OrderDetailsDTO;
import lk.ijse.pos.dto.OrdersDTO;

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
import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/11/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
@WebServlet(urlPatterns = "/orders")
public class PlaceOrderServlet extends HttpServlet {

    @Resource(name = "java:comp/env/jdbc/pool")
    DataSource dataSource;

    private final OrderBO orderBO = (OrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ORDERS);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {

        String option = req.getParameter("option");
        String orderID = req.getParameter("orderId");
        resp.setContentType("application/json");
        Connection connection = dataSource.getConnection();
        PrintWriter writer = resp.getWriter();

        switch (option){

            case "SEARCH":

                ArrayList<OrderDetailsDTO> orderDetails = orderBO.searchOrderDetails(orderID, connection);

                JsonArrayBuilder arrayBuilder1 = Json.createArrayBuilder();

                for (OrderDetailsDTO orderDetail : orderDetails) {
                    JsonObjectBuilder ob = Json.createObjectBuilder();
                    ob.add("oId",orderDetail.getoId());
                    ob.add("iCode",orderDetail.getiCode());
                    ob.add("qty",orderDetail.getoQty());
                    ob.add("price",orderDetail.getPrice());
                    ob.add("total",orderDetail.getTotal());

                    arrayBuilder1.add(ob.build());
                }
                writer.write(String.valueOf(arrayBuilder1.build()));

                break;

            case "GETID":

                JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();
                jsonObjectBuilder.add("orderId",orderBO.generateNewOrderId(connection));
                writer.print(jsonObjectBuilder.build());

                break;

            case "GETALL":

                ObservableList<OrdersDTO> allOrders = orderBO.getAllOrders(connection);
                JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

                for (OrdersDTO ordersDTO : allOrders){

                    JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                    objectBuilder.add("orderId", ordersDTO.getOrderId());
                    objectBuilder.add("cid", ordersDTO.getcId());
                    objectBuilder.add("orderDate", String.valueOf(ordersDTO.getOrderDate()));
                    objectBuilder.add("total", ordersDTO.getTotal());
                    objectBuilder.add("discount", ordersDTO.getDiscount());
                    objectBuilder.add("subTotal", ordersDTO.getSubTotal());
                    arrayBuilder.add(objectBuilder.build());

                }

                JsonObjectBuilder response = Json.createObjectBuilder();
                response.add("status", 200);
                response.add("message", "Done");
                response.add("data", arrayBuilder.build());
                writer.print(response.build());

                break;

            case "COUNT":

                writer.print(orderBO.ordersCount(connection));

                break;
        }

        connection.close();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.setContentType("application/json");
            Connection connection = dataSource.getConnection();
            PrintWriter writer = resp.getWriter();

            JsonReader reader = Json.createReader(req.getReader());
            JsonObject jsonObject = reader.readObject();
            JsonArray oDetail = jsonObject.getJsonArray("ODetail");

            ArrayList<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
            for (JsonValue orderDetail: oDetail) {
                JsonObject asJsonObject = orderDetail.asJsonObject();
                orderDetailsDTOS.add(new OrderDetailsDTO(
                        asJsonObject.getString("oId"),
                        asJsonObject.getString("itemCode"),
                        Integer.parseInt(asJsonObject.getString("qty")),
                        Double.parseDouble(asJsonObject.getString("price")),
                        Double.parseDouble(asJsonObject.getString("total"))
                ));

            }

            OrdersDTO ordersDTO = new OrdersDTO(
                    jsonObject.getString("orderID"),
                    jsonObject.getString("cId"),
                    Date.valueOf(jsonObject.getString("orderDate")),
                    Double.parseDouble(jsonObject.getString("total")),
                    Double.parseDouble(jsonObject.getString("discount")),
                    Double.parseDouble(jsonObject.getString("subTotal")),
                    orderDetailsDTOS
            );

            if (orderBO.saveOrder(connection, ordersDTO)){
                resp.setStatus(HttpServletResponse.SC_OK);
                JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
                objectBuilder.add("status",resp.getStatus());
                objectBuilder.add("message","Successfully Added");
                objectBuilder.add("data","");

                writer.print(objectBuilder.build());
            }

            connection.close();

            } catch (SQLException | ClassNotFoundException throwables) {

            resp.setStatus(HttpServletResponse.SC_OK);
            JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
            objectBuilder.add("status", resp.getStatus());
            objectBuilder.add("message", "Error");
            objectBuilder.add("data", throwables.getLocalizedMessage());
            throwables.printStackTrace();

            throwables.printStackTrace();
        }

    }
}
