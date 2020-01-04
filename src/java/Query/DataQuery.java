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
    
    public void RegisterUser(String name, String lastname, String username, String password, char sex, String phone, String born, char user_type, int credit_card){
        user u = new user();
        u.setName(name);
        u.setLastname(lastname);
        u.setUsername(username);
        u.setPassword(password);
        u.setSex(sex);
        u.setPhone(phone);
        u.setBorn(born);
        u.setUser_type(user_type);
        u.setCredit_card(credit_card);
        em.persist(u);
        em.getTransaction().commit();
    }
    
    public void Insertar(){
        category c = new category();
        c.setId_category(1);
        c.setName_category("Juegos");
        em.persist(c);
        em.getTransaction().commit();
    }
    
}
