package Query;

import Entity.card;
import Entity.category;
import Entity.product;
import Entity.purchase;
import Entity.user;
import java.util.List;
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
    
    public void passControl(String username, String password) {
        user u = em.find(user.class, username);
        u.setUsername(username);
        u.setPassword(password);
        em.merge(u);
        em.getTransaction().commit();
    }
    
    public String getName(String username, String phone) {
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
    
    public boolean Suma(int cn, double in) {
        try {
            card ca = em.find(card.class, cn);
            double bef = ca.getBalance();
            double aft = bef + in;
            ca.setBalance(aft);
            em.merge(ca);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean Compra(int card, int id, String user) {
        try {
            user us = em.find(user.class, user);
            product pr = em.find(product.class, id);
            card c = em.find(card.class, card);
            purchase pc = new purchase();
            double cant = c.getBalance();
            double res = cant - pr.getPrice();
            c.setBalance(res);
            pc.setTotal(pr.getPrice());
            pc.setUser(us.getUsername());
            pc.setProduct(pr.getId_product());
            em.merge(c);
            em.persist(pc);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public List typeU() {
        try {
            List<user> users = em.createNamedQuery("user.type", user.class).setParameter("type", 'U').getResultList();
            return users;
        } catch (Exception e) {
            return null;
        }
    }
    
    public List listProducts() {
        try {
            List<product> products = em.createNamedQuery("product.findAll", product.class).getResultList();
            return products;
        } catch (Exception e) {
            return null;
        }
    }
    
    public void deleteProduct(int id) {
        product p = em.find(product.class, id);
        em.remove(p);
        em.getTransaction().commit();
    }
    
    public boolean deleteUser(String username) {
        try {
            user u = em.find(user.class, username);
            int credit = u.getCredit_card();
            card c = em.find(card.class, credit);
            em.remove(u);
            em.remove(c);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public void RegisterProduct(String name_product, int stock, int category, double price, String description, String image) {
        product p = new product();
        p.setName_product(name_product);
        p.setStock(stock);
        p.setCategory(category);
        p.setPrice(price);
        p.setDescription(description);
        p.setImage(image);
        em.persist(p);
        em.getTransaction().commit();
    }
    
    public void updateProduct(int id, String name, int stock, int category, double price, String description, String img) {
        product p = em.find(product.class, id);
        p.setName_product(name);
        p.setStock(stock);
        p.setCategory(category);
        p.setPrice(price);
        p.setDescription(description);
        p.setImage(img);
        em.merge(p);
        em.getTransaction().commit();
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
