package Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="register")
@SessionScoped
public class RegisterController implements Serializable{
    
    private List<String> lista = new ArrayList<String>();
    private String radio = "Selecciones una opción";
    
    {
        lista.add(0, "M");
        lista.add(1, "F");
    }
    
    public void changeRadio(ValueChangeEvent event){
        radio = (String)event.getNewValue();
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public String getRadio() {
        return radio;
    }

    public void setRadio(String radio) {
        this.radio = radio;
    }
    
    public String toLogin(){
        return "login.xhtml?faces-redirect=true";
    }
}
