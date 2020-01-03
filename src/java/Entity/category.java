package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class category {
    @Id
    @Column(name="id_categoty")
    private int id_category;
    @Column(name="name_category")
    private String name_categoty;

    public category(){
        
    }
    
    public category(int id_category, String name_categoty) {
        this.id_category = id_category;
        this.name_categoty = name_categoty;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName_categoty() {
        return name_categoty;
    }

    public void setName_categoty(String name_categoty) {
        this.name_categoty = name_categoty;
    }
    
}
