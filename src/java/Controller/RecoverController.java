package Controller;

import Query.DataQuery;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "recover")
@SessionScoped
public class RecoverController {

    private boolean visible = false;
    private String username;
    private String phone;
    private String password;
    private String passconfirm;
    private String name;
    private boolean found = false;
    private DataQuery query = new DataQuery();
    private boolean succesfully = false;

    public void verifyNumber() {
        if (query.recoverControl(username, phone)) {
            found = true;
            visible = true;
            name = query.getName(username, phone);
        } else {
            found = false;
            visible = false;
        }
    }

    public String changePass() {
        String page = "";
        RequestContext.getCurrentInstance().update("growls");
        FacesContext context = FacesContext.getCurrentInstance();
        if (!(password.equals("")) && !(passconfirm.equals("")) && password.equals(passconfirm)) {
            query.passControl(username, password);
            succesfully = true;
        } else {
            succesfully = false;
            if (password.equals("") || passconfirm.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Favor de no dejar campos vacíos"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Las contraseñas no coinciden"));
            }
        }
        return page;
    }

    public String eraseField() {
        username = null;
        phone = null;
        visible = false;
        succesfully = false;
        return "recover.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "login.xhtml?faces-redirect=true";
    }

    public boolean isSuccesfully() {
        return succesfully;
    }

    public void setSuccesfully(boolean succesfully) {
        this.succesfully = succesfully;
    }
    
    public String getPassconfirm() {
        return passconfirm;
    }

    public void setPassconfirm(String passconfirm) {
        this.passconfirm = passconfirm;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isFound() {
        return found;
    }

    public void setFound(boolean found) {
        this.found = found;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
