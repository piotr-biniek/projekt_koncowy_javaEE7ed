//ok

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.web.beans.organiser;

import pl.java.biniek.web.beans.controlers.UzerController;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.model.Organiser;
import pl.java.biniek.web.beans.controlers.AplicationController;

@Named
@ViewScoped
@BinderPageBean
public class OrganiserWizard implements Serializable {

    @Inject
    UzerController uzerController;

    private Organiser organiser = new Organiser();

    private boolean skip;

    public Organiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Organiser org) {
        this.organiser = org;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    public String onFlowProcess(FlowEvent event) {

        if (uzerController.doesGivenEmailExists(organiser.getEmail())) {
            AplicationController.showMessage("formRegister:email", "messages.constraint.email");

            return "personal";
        } else if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public String save() throws BasicApplicationException {
        return uzerController.saveNew(organiser);

    }
}
