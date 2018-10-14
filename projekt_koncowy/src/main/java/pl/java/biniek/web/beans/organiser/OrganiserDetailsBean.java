package pl.java.biniek.web.beans.organiser;

import pl.java.biniek.web.beans.controlers.UzerController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.model.Administrator;
import pl.java.biniek.model.Organiser;
import pl.java.biniek.web.beans.controlers.CourseController;
//import org.primefaces.showcase.service.CarService;
//@ManagedBean
//@RequestScoped

@Named
@ViewScoped
@BinderPageBean
public class OrganiserDetailsBean implements Serializable {

    @Inject
    UzerController uzerControler;
    @Inject
    CourseController courseController;

    Organiser org;

    public Organiser getOrg() {
        return org;
    }

    public void setOrg(Organiser org) {
        this.org = org;
    }

    @PostConstruct
    public void init() {
        org = uzerControler.getViewedOrganiser();
    }

    public UzerController getUzerControler() {
        return uzerControler;
    }

    public String editOrganiser() throws WrongUzerApplicationException {
        /**
         * inny sposób blokady wejścia do formatki edycji, inaczej niż w course
         */
        if ((uzerControler.getLoggedUser().getId() == uzerControler.getViewedOrganiser().getId()) || uzerControler.getLoggedUser() instanceof Administrator) {
            uzerControler.setEditetOrganiser(org);
            uzerControler.setViewedOrganiser(null);
            return "organiserEdit";
        } else {
            throw new WrongUzerApplicationException();
        }
    }

    public String back() {
        uzerControler.setViewedOrganiser(null);
        return "listOfOrganisers";
    }

    public String deleteViewedOrganizer() throws BasicApplicationException {

        return uzerControler.deleteViewedOrganiser(org);

    }

    public String listOfOrganiserRuns() {

        courseController.setOrganiser(org);
        uzerControler.setViewedOrganiser(null);
        return "listOfOrrganiserCourses";

    }

}
