package Query;
import Entity.category;
import Entity.user;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DataQuery {
    EntityManagerFactory emf;
    EntityManager em;

    public DataQuery() {
        emf = Persistence.createEntityManagerFactory("neshopPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }
    
    public boolean loginControl(String username, String password){
        try{
            user u = em.createNamedQuery("user.Control", user.class).setParameter("username", username).setParameter("password", password).getSingleResult();
            if(u != null){
                return true;
            }
            return false;
        }catch (Exception e){
            return false;
        }
    }
    
    public void Insertar(){
        category c = new category();
        c.setId_category(1);
        c.setName_category("Juegos");
        em.persist(c);
        em.getTransaction().commit();
    }
    
}
