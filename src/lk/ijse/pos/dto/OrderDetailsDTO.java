package lk.ijse.pos.dto;

/**
 * @author : Yasiru Dahanayaka
 * @name : JavaEE POS System
 * @date : 5/17/2022
 * @month : 05
 * @year : 2022
 * @since : 0.1.0
 **/
public class OrderDetailsDTO {

    private String oId;
    private String iCode;
    private int oQty;
    private double price;

    public OrderDetailsDTO() {
    }

    public OrderDetailsDTO(String oId, String iCode, int oQty, double price) {
        this.oId = oId;
        this.iCode = iCode;
        this.oQty = oQty;
        this.price = price;
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

    public int getoQty() {
        return oQty;
    }

    public void setoQty(int oQty) {
        this.oQty = oQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "OrderDetailsDTO{" +
                "oId='" + oId + '\'' +
                ", iCode='" + iCode + '\'' +
                ", oQty=" + oQty +
                ", price=" + price +
                '}';
    }
}
