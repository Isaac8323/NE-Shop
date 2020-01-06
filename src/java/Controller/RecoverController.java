package Controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "recover")
@SessionScoped
public class RecoverController {
    private boolean visible = true;
    private String number;
    private String password;
    private boolean navigate = true;

    public void Show(){
        visible = true;
    }
    
    public void Hide(){
        visible = false;
    }
    
    public void verifyNumber(){
        
    }

    public boolean isNavigate() {
        return navigate;
    }

    public void setNavigate(boolean navigate) {
        this.navigate = navigate;
    }
    
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
}
