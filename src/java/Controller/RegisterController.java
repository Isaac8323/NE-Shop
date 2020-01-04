package Controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name = "register")
@SessionScoped
public class RegisterController implements Serializable {

    private String username;
    private String password;
    private String name;
    private String lastname;
    private char sex;
    private String phone;
    private String born;
    private char user_type;
    private int credit_card;
    private List<String> lista = new ArrayList<String>();
    private String radio = "Selecciones una opción";

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
        
        char c = radio.charAt(0);
        sex = c;
        return "";
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

    public String getBorn() {
        return born;
    }

    public void setBorn(String born) {
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
