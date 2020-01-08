package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "product")
@NamedQueries({
    @NamedQuery(name="product.findAll", query = "SELECT p FROM product p"),
    @NamedQuery(name = "product.Control", query = "SELECT p FROM product p WHERE p.Id_product = :id")
})
public class product {
    @Id
    @Column(name="Id_product")
    private int Id_product;
    @Column(name="name_product")
    private String name_product;
    @Column(name="stock")
    private int stock;
    @Column(name="category")
    private int category;
    @Column(name="price")
    private double price;
    @Column(name="description")
    private String description;
    @Column(name="image")    
    private String image;
    public product(){
        
    }
    
    public product(int Id_product, String name_product, int stock, int category, double price, String description, String image) {
        this.Id_product = Id_product;
        this.name_product = name_product;
        this.stock = stock;
        this.category = category;
        this.price = price;
        this.description = description;
        this.image = image;
    }

    public int getId_product() {
        return Id_product;
    }

    public void setId_product(int Id_product) {
        this.Id_product = Id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
