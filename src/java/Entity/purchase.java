package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
public class purchase {
    @Id
    @Column(name="id_purchase")
    private int id_purchase;
    @Column(name="date")
    private String date;
    @Column(name="quantity")
    private int quantity;
    @Column(name="total")
    private double total;
    @Column(name="subtotal")
    private double subtotal;
    @Column(name="user")
    private String user;
    @Column(name="product")
    private int product;

    public purchase(){
        
    }
    
    public purchase(int id_purchase, String date, int quantity, double total, double subtotal, String user, int product) {
        this.id_purchase = id_purchase;
        this.date = date;
        this.quantity = quantity;
        this.total = total;
        this.subtotal = subtotal;
        this.user = user;
        this.product = product;
    }

    public int getId_purchase() {
        return id_purchase;
    }

    public void setId_purchase(int id_purchase) {
        this.id_purchase = id_purchase;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }
    
}
