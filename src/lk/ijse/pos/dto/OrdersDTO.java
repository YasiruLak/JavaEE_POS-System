package lk.ijse.pos.dto;

import java.sql.Date;
import java.sql.Time;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class OrdersDTO {

    private String orderId;
    private String cId;
    private Date orderDate;
    private Time orderTime;
    private double total;
    private double discount;
    private double subTotal;

    public OrdersDTO() {
    }

    public OrdersDTO(String orderId, String cId, Date orderDate, Time orderTime, double total, double discount, double subTotal) {
        this.orderId = orderId;
        this.cId = cId;
        this.orderDate = orderDate;
        this.orderTime = orderTime;
        this.total = total;
        this.discount = discount;
        this.subTotal = subTotal;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getcId() {
        return cId;
    }

    public void setcId(String cId) {
        this.cId = cId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Time getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Time orderTime) {
        this.orderTime = orderTime;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    @Override
    public String toString() {
        return "OrdersDTO{" +
                "orderId='" + orderId + '\'' +
                ", cId='" + cId + '\'' +
                ", orderDate=" + orderDate +
                ", orderTime=" + orderTime +
                ", total=" + total +
                ", discount=" + discount +
                ", subTotal=" + subTotal +
                '}';
    }
}
