
package pl.java.biniek.web.beans.organiser;

import pl.java.biniek.web.beans.controlers.UzerController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.model.Organiser;
//import org.primefaces.showcase.service.CarService;


@Named
@ViewScoped
@BinderPageBean
public class OrganiserEditBean implements Serializable {

    @Inject
    UzerController uzerControler;

        Organiser org;

    @PostConstruct
    public void init() {
        org = uzerControler.getEditetOrganiser();
    }
    
    public UzerController getUzerControler() {
        return uzerControler;
    }

    public String save() throws BasicApplicationException {
        return uzerControler.saveEditedOrganiser(org);

    }

    public Organiser getOrg() {
        return org;
    }

    public void setOrg(Organiser org) {
        this.org = org;
    }
    

}
