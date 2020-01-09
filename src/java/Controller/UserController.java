/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Query.DataQuery;
import Util.SessionControl;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "user")
@SessionScoped
public class UserController {
    
    private String name;
    private String lastname;
    private char sex;
    private String phone;
    private String born;

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
    
    public void doDelete(){
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("infouser");
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext req = RequestContext.getCurrentInstance();
        DataQuery h = new DataQuery();
        String un = (String) hs.getAttribute("username");
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", un));
        if(h.deleteUser(un)){
            req.execute("PF('dialoguser').show();");
            hs.invalidate();
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "No se ha podido dar de baja al usuario"));
        }
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
    
    public String toLogin(){
        return "login.xhtml?faces-redirect=true";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
        this.born = born;
    }
    
}
