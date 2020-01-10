package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "purchase")
@NamedQueries({
    @NamedQuery(name="purchase.findAll", query = "SELECT p FROM purchase p")
})
public class purchase {
    @Id
    @Column(name="id_purchase")
    private int id_purchase;
    @Column(name="total")
    private double total;
    @Column(name="user")
    private String user;
    @Column(name="product")
    private int product;

    public purchase(){
        
    }
    
    public purchase(int id_purchase, double total, String user, int product) {
        this.id_purchase = id_purchase;
        this.total = total;
        this.user = user;
        this.product = product;
    }

    public int getId_purchase() {
        return id_purchase;
    }

    public void setId_purchase(int id_purchase) {
        this.id_purchase = id_purchase;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
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
