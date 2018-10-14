//ok
package pl.java.biniek.web.beans.organiser;

import pl.java.biniek.web.beans.controlers.UzerController;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.model.Administrator;
import pl.java.biniek.model.Organiser;
//import org.primefaces.showcase.service.CarService;

@Named("dtListOfOrganisers")
@RequestScoped
@BinderPageBean
public class OrganiserListViewBean implements Serializable {

    @Inject
    UzerController uzerControler;

    public UzerController getUzerControler() {
        return uzerControler;
    }

    public String setViewed(Organiser viewedOrganiser) {
        uzerControler.setViewedOrganiser(viewedOrganiser);
        return "organiserDetails";
    }

    public String delete(Organiser organiser) throws BasicApplicationException {

        return uzerControler.delete(organiser);

    }

    public String editOrganiser(Organiser viewedOrganiser) throws WrongUzerApplicationException {
        if (uzerControler.getLoggedUser() instanceof Administrator) {// dodoatlow zabezpieczenie przed wiejsciem w formatkę edycji 
            uzerControler.setEditetOrganiser(viewedOrganiser);
            uzerControler.setViewedOrganiser(null);
            return "organiserEdit";
        } else {
            throw new WrongUzerApplicationException();
        }

    }

    @Deprecated//w zasadzie dostęp do listy organizatorów to może mieć każdy
    public List<Organiser> getAllOrganisers() {
        if (uzerControler.getLoggedUser() instanceof Administrator) {
            List<Organiser> organisers = uzerControler.getAllOrganisers();
            return organisers;
        }
        return null;
    }
}
