package Controller;

import Query.DataQuery;
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
    private int credit_card;
    private int cvc;
    private List<String> lista = new ArrayList<String>();
    private String radio = "Selecciones una opción";
    private String f_seleccionada;
    private DataQuery query = new DataQuery();

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

    public void RegisterController() {
        RequestContext.getCurrentInstance().update("growl");
        FacesContext context = FacesContext.getCurrentInstance();
        char c = radio.charAt(0);
        sex = c;
        boolean numero = false;
        if (query.searchCVC(cvc)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error", "Esta tarjeta ya existe"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", username));
            if (phone.contains("1") || phone.contains("2") || phone.contains("3") || phone.contains("4") || phone.contains("5") || phone.contains("6") || phone.contains("7") || phone.contains("8") || phone.contains("9") || phone.contains("0")) {
                numero = true;
            }
            if (username.length() <= 29 && password.length() <= 29 && name.length() <= 49 && lastname.length() <= 49 && sex != 'S' && phone.length() == 12 && cvc > 99 && credit_card > 0 && numero == true) {
                DataQuery q = new DataQuery();
                q.RegisterUser(name, lastname, username, password, sex, phone, f_seleccionada, credit_card, cvc);
                if (query.loginControl(username, password)) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Se registró con éxito"));
                }
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "Fallo en el registro"));
            }
            if (username.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo Nombre de Usuario"));
            }
            if (password.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo Contraseña"));
            }
            if (name.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo Nombres"));
            }
            if (lastname.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo Apellidos"));
            }
            if (phone.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo Telefono"));
            }
            if (cvc == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo CVC"));
            }
            if (credit_card == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo No. de tarjeta"));
            }
            if (username.length() > 29) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "El nombre de usuario no debe exceder los 30 caracteres"));
            }
            if (password.length() > 29) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "La contraseña no debe exceder los 30 caracteres"));
            }
            if (name.length() > 49) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Los nombres no debe exceder los 50 caracteres"));
            }
            if (username.length() > 49) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Los apellidos no debe exceder los 50 caracteres"));
            }
            if (sex == 'S') {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Seleccione un sexo"));
            }
            if (phone.length() != 12) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "El formato del número de teléfono debe ser de 12 dígitos"));
            }
            if (numero == false) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Número telefónico", "Favor de ingresar solo números"));
            }
        }
    }

    public void actualizar_fecha(SelectEvent event) {
        SimpleDateFormat fecha1 = new SimpleDateFormat("yyyy-MM-dd");
        StringBuilder cadena_fecha1_11 = new StringBuilder(fecha1.format(event.getObject()));
        f_seleccionada = cadena_fecha1_11.toString();
    }

    public Date getBorn() {
        return born;
    }

    public void setBorn(Date born) {
        this.born = born;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
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
