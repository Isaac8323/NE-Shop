package Controller;

import Entity.product;
import Query.DataQuery;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.http.Part;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "product")
@SessionScoped
public class ProductController {

    private int id_prod;
    private Part file;
    private String render = "/resources/img/images.png";
    private int id_category = 0;
    private String name = "";
    private int cant = 0;
    private double price = 0;
    private String desc = "";
    private DataQuery query = new DataQuery();
    EntityManager em;
    EntityManagerFactory emf;
    List<product> products;
    private int aux;

    public ProductController() {
        emf = Persistence.createEntityManagerFactory("neshopPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
    }

    public void getList() {
        FacesContext context = FacesContext.getCurrentInstance();
        RequestContext.getCurrentInstance().update("growlpro");
        try {
            products = em.createNamedQuery("product.findAll", product.class).getResultList();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exception", e.getMessage()));
        }
    }

    public void save() {
        try (InputStream input = file.getInputStream()) {
            String namei = file.getSubmittedFileName();
            Files.copy(input, new File("D:\\Users\\isaqu\\Documents\\GitHub\\neshop\\web\\resources\\img\\", namei).toPath());
            render = "/resources/img/" + name;
        } catch (IOException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    public void ProductControl() {
        RequestContext req = RequestContext.getCurrentInstance();
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

    public void ProductRender() {
        RequestContext.getCurrentInstance().update("growlpro");
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            product p = em.createNamedQuery("product.Control", product.class).setParameter("id", aux).getSingleResult();
            name = p.getName_product();
            cant = p.getStock();
            price = p.getPrice();
            desc = p.getDescription();
            id_prod = p.getId_product();
            id_category = p.getCategory();
            render = p.getImage();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Exception", e.getMessage()));
        }
    }

    public void doUpdate() {
        RequestContext.getCurrentInstance().update("growlpro");
        RequestContext req = RequestContext.getCurrentInstance();
        FacesContext context = FacesContext.getCurrentInstance();
        if (!(name.equals("")) && !(desc.equals("")) && !(render.equals("")) && price > 0 && cant >= 0) {
            query.updateProduct(id_prod, name, cant, id_category, price, desc, render);
            req.execute("PF('wdialogs').show();");
        } else {
            if (name.equals("") || desc.equals("") || render.equals("") || price < 0 || cant < 0) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Advertencia", "Favor de ingresar los campos correctamente"));
            }
        }
    }

    public String eraseFields() {
        name = "";
        cant = 0;
        price = 0;
        desc = "";
        id_prod = 0;
        id_category = 0;
        render = "";
        return "product.xhtml?faces-redirect=true";
    }

    public int getAux() {
        return aux;
    }

    public void setAux(int aux) {
        this.aux = aux;
    }

    public List<product> getProducts() {
        return products;
    }

    public void setProducts(List<product> products) {
        this.products = products;
    }

    public int getId_prod() {
        return id_prod;
    }

    public void setId_prod(int id_prod) {
        this.id_prod = id_prod;
    }

    public String toAdmin() {
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
