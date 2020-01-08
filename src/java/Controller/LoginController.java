package Controller;

import Query.DataQuery;
import Util.SessionControl;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "login")
@SessionScoped
public class LoginController implements Serializable {

    private String username;
    private String password;
    private DataQuery query = new DataQuery();
    private char user_type;

    public String LoginControl() {
        if (query.loginControl(username, password)) {
            HttpSession hs = SessionControl.getSession();
            hs.setAttribute("username", username);
            user_type = query.getUserType(username, password);
            hs.setAttribute("type_user", user_type);
            if (user_type == 'U') {
                return "user.xhtml?faces-redirect=true";
            }
            if (user_type == 'A') {
                return "admin.xhtml?faces-redirect=true";
            }
        }
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario o contraseña invalido"));
        return "";
    }

    public String Logged() {
        String page="";
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if (hs.getAttribute("username") == null) {
            page = "";
        } else {
            if (String.valueOf(hs.getAttribute("type_user")).equals("U")) {
                page = "user.xhtml?faces-redirect=true";
            }
            if (String.valueOf(hs.getAttribute("type_user")).equals("A")) {
                page = "admin.xhtml?faces-redirect=true";
            }
        }
        return page;
    }

    public String logOut() {
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario actual", (String) hs.getAttribute("username")));
        hs.invalidate();
        return "login.xhtml?faces-redirect=true";
    }

    public char getUser_type() {
        return user_type;
    }

    public void setUser_type(char user_type) {
        this.user_type = user_type;
    }


    public String toRecover(){
        return "recover.xhtml?faces-redirect=true";
    }
    
    public String toGuest() {
        return "guest.xhtml?faces-redirect=true";
    }

    public String toRegister() {
        return "register.xhtml?faces-redirect=true";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
