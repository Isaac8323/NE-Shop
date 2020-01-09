/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Entity.card;
import Entity.user;
import Query.DataQuery;
import Util.SessionControl;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "user")
@SessionScoped
public class UserController {

    private int id;
    private double balance;
    private int cvc;
    private String titular;
    EntityManager em;
    EntityManagerFactory emf;
    private double ingreso;

    public UserController() {
        emf = Persistence.createEntityManagerFactory("neshopPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public String Expired() throws IOException {
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if (hs.getAttribute("username") == null) {
            hs.invalidate();
            return "login.xhtml?faces-redirect=true";
        }
        return "";
        //Hello moto
    }

    public String getCard() {
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if (hs.getAttribute("username") == null) {
            return "login.xhtml?faces-redirect=true";
        } else {
            char user = (char) hs.getAttribute("type_user");
            if (user == 'U') {
                FacesContext context = FacesContext.getCurrentInstance();
                RequestContext.getCurrentInstance().update("growlpro");
                try {
                    String un = (String) hs.getAttribute("username");
                    user u = em.find(user.class, un);
                    int aux = u.getCredit_card();
                    card c = em.find(card.class, aux);
                    id = c.getCard_number();
                    balance = c.getBalance();
                    cvc = c.getCvc();
                    titular = c.getTitular();
                } catch (Exception e) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exception", e.getMessage()));
                }
            } else {
                return "login.xhtml?faces-redirect=true";
            }
            return "";
        }
    }

    public void doDelete() {
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("infouser");
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext req = RequestContext.getCurrentInstance();
        DataQuery h = new DataQuery();
        String un = (String) hs.getAttribute("username");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", un));
        if (h.deleteUser(un)) {
            req.execute("PF('dialoguser').show();");
            hs.invalidate();
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "No se ha podido dar de baja al usuario"));
        }
    }
    
    public String Sum(){
        RequestContext.getCurrentInstance().update("infouser");
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext req = RequestContext.getCurrentInstance();
        DataQuery h1 = new DataQuery();
        if(h1.Suma(id, ingreso)){
            req.execute("PF('dialogsca').show();");
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "No se pudieron añadir los fondos"));
        }
        return "";
    }

    public String validateAdmin() {
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if (hs.getAttribute("username") == null) {
            hs.invalidate();
            return "login.xhtml?faces-redirect=true";
        } else {
            char user = (char) hs.getAttribute("type_user");
            if (user != 'A') {
                return "login.xhtml?faces-redirect=true";
            }
        }
        return "";
    }

    public String validateUser() {
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if (hs.getAttribute("username") == null) {
            hs.invalidate();
            return "login.xhtml?faces-redirect=true";
        } else {
            char user = (char) hs.getAttribute("type_user");
            if (user != 'U') {
                return "login.xhtml?faces-redirect=true";
            }
        }
        return "";
    }

    public String validateGuest() {
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if (hs.getAttribute("username") == null) {
            hs.invalidate();
            return "login.xhtml?faces-redirect=true";
        } else {
            char user = (char) hs.getAttribute("type_user");
            if (user != 'G') {
                return "login.xhtml?faces-redirect=true";
            }
        }
        return "";
    }

    public String toUser(){
        return "user.xhtml?faces-redirect=true";
    }
    
    public String toLogin() {
        return "login.xhtml?faces-redirect=true";
    }

    public double getIngreso() {
        return ingreso;
    }

    public void setIngreso(double ingreso) {
        this.ingreso = ingreso;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

}
