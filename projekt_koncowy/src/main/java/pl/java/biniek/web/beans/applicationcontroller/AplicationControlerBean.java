package pl.java.biniek.web.beans.applicationcontroller;

//package org.primefaces.showcase.view.message;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.web.beans.controlers.AplicationController;
import pl.java.biniek.web.beans.controlers.TestButtonsController;
import pl.java.biniek.web.beans.controlers.UzerController;
//import pl.java.biniek.web.themes.Theme;

@RequestScoped
@Named
@BinderPageBean
public class AplicationControlerBean implements Serializable {

    @Inject
    UzerController uzercontroller;
    @Inject
    AplicationController aplicationController;

    //Theme theme;
    public void resetSession() throws Exception {
     AplicationController.resetSession();
      
      
    }

    public String getName() {

        return uzercontroller.getUzersName();
    }

    public String starttest() throws Throwable {
        return uzercontroller.test();
        // return null;
    }



    public boolean getTestButtons() {
        return TestButtonsController.isTestButtons();
    }

    public String editUzer() throws BasicApplicationException {
        return uzercontroller.editLoggedUzer();
    }

}
