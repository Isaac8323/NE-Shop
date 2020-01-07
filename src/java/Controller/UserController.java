/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Util.SessionControl;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "user")
@SessionScoped
public class UserController {
    
        public void Expired() throws IOException{
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if(hs.getAttribute("username")==null){
            hs.invalidate();
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml"); 
        }
    }
}
