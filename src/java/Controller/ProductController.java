package Controller;

import Query.DataQuery;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "product")
@SessionScoped
public class ProductController {

    private Part file;
    private String render = "/resources/img/images.png";
    private int id_category = 0;
    private String name = "";
    private int cant = 0;
    private double price = 0;
    private String desc = "";
    private DataQuery query = new DataQuery();

    public void save() {
        RequestContext.getCurrentInstance().update("growlf");
        try (InputStream input = file.getInputStream()) {
            String name = file.getSubmittedFileName();
            Files.copy(input, new File("D:\\Users\\isaqu\\Documents\\GitHub\\neshop\\web\\resources\\img\\", name).toPath());
            render = "/resources/img/" + name;
        } catch (IOException e) {
            System.out.println("Image error");
        }
    }

    public void ProductControl() {
        RequestContext  req = RequestContext.getCurrentInstance();
        RequestContext.getCurrentInstance().update("growlpro");
        FacesContext context = FacesContext.getCurrentInstance();
        if (!(name.equals("")) && !(desc.equals("")) && cant != 0 && price != 0) {
            query.RegisterProduct(name, cant, id_category, price, desc, render);
            req.execute("PF('wdialogs').show();");
        } else {
            if (name.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo Nombre de Producto"));
            }
            if (desc.equals("")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de llenar el campo Descripción"));
            }
            if (cant == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de ingresar una cantidad"));
            }
            if (price == 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Advertencia", "Favor de ingresar un precio"));
            }
        }
    }
    
    public String toAdmin(){
        return "admin.xhtml?faces-redirect=true";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId_category() {
        return id_category;
    }

    public void setId_category(int id_category) {
        this.id_category = id_category;
    }

    public String getRender() {
        return render;
    }

    public void setRender(String render) {
        this.render = render;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }

}
