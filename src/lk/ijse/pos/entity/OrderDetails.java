package lk.ijse.pos.entity;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class OrderDetails {

    private String oId;
    private String iCode;

    private int qty;
    private double price;
    private double total;

    public OrderDetails() {
    }

    public OrderDetails(String oId, String iCode, int qty, double price, double total) {
        this.oId = oId;
        this.iCode = iCode;
        this.qty = qty;
        this.price = price;
        this.total = total;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getiCode() {
        return iCode;
    }

    public void setiCode(String iCode) {
        this.iCode = iCode;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
