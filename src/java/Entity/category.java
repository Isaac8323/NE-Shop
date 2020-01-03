package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="category")
public class category {
    @Id
    @Column(name="id_category")
    private int id_category;
    @Column(name="name_category")
    private String name_category;

    public category(){
        
    }
    
    public category(int id_category, String name_category) {
        this.id_category = id_category;
        this.name_category = name_category;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }
    
}
