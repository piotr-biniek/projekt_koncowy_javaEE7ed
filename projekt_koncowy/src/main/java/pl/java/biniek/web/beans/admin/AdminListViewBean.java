//ok
package pl.java.biniek.web.beans.admin;

import pl.java.biniek.web.beans.controlers.UzerController;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Administrator;

@Named
@ViewScoped
@BinderPageBean
public class AdminListViewBean implements Serializable {

    @Inject
    UzerController uzerControler;

    public UzerController getUzerControler() {
        return uzerControler;
    }

  
       
   //w zasadzie dostęp do listy organizatorów to może mieć każdy
    public List<Administrator> getAllAdministrator(){
        return uzerControler.getAllAdmins();
    }
}
