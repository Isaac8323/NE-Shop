package Controller;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "register")
@SessionScoped
public class RegisterController implements Serializable {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private char sex;
    private String phone;
    private Date born;
    private char user_type;
    private int credit_card;
    private List<String> lista = new ArrayList<String>();
    private String radio = "Selecciones una opción";
    private String f_seleccionada;

    {
        lista.add(0, "M");
        lista.add(1, "F");
    }

    public void changeRadio(ValueChangeEvent event) {
        radio = (String) event.getNewValue();
    }

    public String toLogin() {
        return "login.xhtml?faces-redirect=true";
    }

    public String toUserPage() {
        return "user.xhtml?faces-redirect=true";
    }

    public String RegisterController() {
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        char c = radio.charAt(0);
        sex = c;
        if(username.length()<29 && password.length()<29 && name.length()<49 && lastname.length()<49 && sex!='S' && phone.length()==12){
            
        }
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"username" ,username));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"password" ,password));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"name" ,name));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"lastname" ,lastname));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"sex" ,String.valueOf(sex)));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"phone" ,phone));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"born" ,f_seleccionada));
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"card" ,String.valueOf(credit_card)));
        
        return "";
    }

    public void actualizar_fecha(SelectEvent event) {
        SimpleDateFormat fecha1 = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder cadena_fecha1_11 = new StringBuilder(fecha1.format(event.getObject()));
        f_seleccionada = cadena_fecha1_11.toString();        
    }

    public String getF_seleccionada() {
        return f_seleccionada;
    }

    public void setF_seleccionada(String f_seleccionada) {
        this.f_seleccionada = f_seleccionada;
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

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public char getUser_type() {
        return user_type;
    }

    public void setUser_type(char user_type) {
        this.user_type = user_type;
    }

    public int getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(int credit_card) {
        this.credit_card = credit_card;
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

 
}
