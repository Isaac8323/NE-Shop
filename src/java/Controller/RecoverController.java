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
    private String status = "nathing";
    private String phone;
    private String password;
    private String passconfirm;
    private String name;
    private DataQuery query = new DataQuery();

    public void verifyNumber() {
        RequestContext  req = RequestContext.getCurrentInstance();
        if (query.recoverControl(username, phone)) {
            visible = true;
            req.execute("Refresh();");
            name = query.getName(username, phone);
        } else {
            req.execute("PF('wdialog1').show();");
            visible = false;
        }
    }

    public void changePass() {
        RequestContext.getCurrentInstance().update("growls");
        RequestContext  req = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        if (!(password.equals("")) && !(passconfirm.equals("")) && password.equals(passconfirm)) {
            query.passControl(username, password);
            req.execute("PF('wdialog').show();");
        } else {
            if (password.equals("") || passconfirm.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Favor de no dejar campos vacíos"));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Las contraseñas no coinciden"));
            }
        }
    }

    public String eraseField() {
        username = null;
        phone = null;
        visible = false;
        return "recover.xhtml?faces-redirect=true";
    }

    public String toLogin() {
        return "login.xhtml?faces-redirect=true";
    }
    
    public String toAdmin(){
        return "admin.xhtml?faces-redirect=true";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
