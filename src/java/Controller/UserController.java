/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Util.SessionControl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "user")
@SessionScoped
public class UserController {
    
        public String Expired(){
        String page;
        HttpSession hs = SessionControl.getSession();
        RequestContext.getCurrentInstance().update("info");
        if(hs.getAttribute("username")==null){
            page = "login.xhtml?faces-redirect=true";
        }else{
            page = "";
        }
        return page;
    }
}
