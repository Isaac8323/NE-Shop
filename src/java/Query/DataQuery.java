package Query;

import Entity.card;
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

    public boolean loginControl(String username, String password) {
        try {
            user u = em.createNamedQuery("user.Control", user.class).setParameter("username", username).setParameter("password", password).getSingleResult();
            if (u != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean recoverControl(String username, String phone) {
        try {
            user u = em.createNamedQuery("user.phone", user.class).setParameter("username", username).setParameter("phone", phone).getSingleResult();
            if (u != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void passControl(String username, String password){
        user u = em.find(user.class, username);
        u.setUsername(username);
        u.setPassword(password);
        em.merge(u);
        em.getTransaction().commit();
    }
    
    public String getName(String username, String phone){
        try {
            user u = em.createNamedQuery("user.phone", user.class).setParameter("username", username).setParameter("phone", phone).getSingleResult();
            if (u != null) {
                return u.getName();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
    
    public char getUserType(String user, String pass) {
        char nombre;
        try {
            user u = em.createNamedQuery("user.Control", user.class).setParameter("username", user).setParameter("password", pass).getSingleResult();
            nombre = u.getUser_type();
        } catch (Exception e) {
            nombre = 0;
        }
        return nombre;
    }

    public boolean searchCVC(int cvc) {
        try {
            card ca = em.createNamedQuery("card.Control", card.class).setParameter("cvc", cvc).getSingleResult();
            if (ca != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public void RegisterUser(String name, String lastname, String username, String password, char sex, String phone, String born, int credit_card, int cvc) {
        user u = new user();
        card c = new card();
        u.setName(name);
        u.setLastname(lastname);
        u.setUsername(username);
        u.setPassword(password);
        u.setSex(sex);
        u.setPhone(phone);
        u.setBorn(born);
        u.setUser_type('U');
        u.setCredit_card(credit_card);
        c.setCard_number(credit_card);
        c.setTitular(name + " " + lastname);
        c.setBalance(15000);
        c.setCvc(cvc);
        em.persist(u);
        em.persist(c);
        em.getTransaction().commit();
    }

}
