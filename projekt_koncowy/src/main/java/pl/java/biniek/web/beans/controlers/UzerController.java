//ok

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.web.beans.controlers;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import pl.java.biniek.endpoints.UzerEndPoint;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.model.Administrator;
import pl.java.biniek.model.Organiser;
import pl.java.biniek.model.Uzer;
import pl.java.biniek.exception.interceptor.frontend.BinderController;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Runner;

@Named
@SessionScoped
@BinderController
//@Interceptors(LoggingInterceptorForController.class)
public class UzerController implements Serializable {

    @EJB
    UzerEndPoint uzerEndPoint;

    private Runner editetRunner;

    private Runner viewedRunner;

    private Organiser editetOrganiser;

    private Organiser viewedOrganiser;

    public Organiser getViewedOrganiser() {
        return viewedOrganiser;
    }

    public void setViewedOrganiser(Organiser viewedOrganiser) {
        this.viewedOrganiser = viewedOrganiser;
    }

    public int birthYear(Date date) {
        LocalDate ld;
        LocalDate lDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return lDate.getYear();

    }

  
    public String getSex(boolean isMale) {// uzupelnic internacjonalizacje
        if (isMale) {
            return AplicationController.getInternationalMessage("orms.runner.sexmale");
        }
        return AplicationController.getInternationalMessage("forms.runner.sexfemale");
    }
// getbirthyare

    public List<Organiser> getAllOrganisers() {
        return uzerEndPoint.getAllOrganisers();
    }

    public List<Administrator> getAllAdmins() {
        return uzerEndPoint.getAllAdmins();
    }

    public Organiser getEditetOrganiser() {
        return editetOrganiser;
    }

    public void setEditetOrganiser(Organiser editetOrganiser) {
//        if (editetOrganiser != null) {
//            this.editetOrganiser = (Organiser) uzerEndPoint.findUzerByEmail(editetOrganiser.getEmail());
//        } else {
        this.editetOrganiser = editetOrganiser;
//        }
    }

    public String saveNew(Uzer uzer) throws BasicApplicationException {
        uzerEndPoint.createUzer(uzer);
        AplicationController.showInfoSuccesMessage(null);
        return "registerUser-return";

    }

    public String saveNew(Administrator admin) throws BasicApplicationException {

        uzerEndPoint.createAdministrator(admin);// 
        AplicationController.showInfoSuccesMessage("");
        return "registerUser-return";

    }

    public String saveEditedOrganiser(Organiser org) throws BasicApplicationException {

        this.saveAfterEdit(org);
        AplicationController.showInfoSuccesMessage(null);
        this.setViewedOrganiser((Organiser) this.getUzerByEmail(org.getEmail()));
        this.setEditetOrganiser(null);
       
        return "organiserDetails";
    }

    public void saveAfterEdit(Organiser org) throws BasicApplicationException {

        uzerEndPoint.saveAfterEdit(org);
    }

    public String saveEditedRunner(Runner run) throws BasicApplicationException {
        //    Runner run = editetRunner;

        //  this.setEditetRunner(null);//domosc faces
        this.saveAfterEdit(run);
        AplicationController.showInfoSuccesMessage(null);

        this.setViewedRunner((Runner) this.getUzerByEmail(run.getEmail()));
        this.setEditetRunner(null);
        return "runnerDetails";

    }

    public void saveAfterEdit(Runner runer) throws BasicApplicationException {
        uzerEndPoint.saveAfterEdit(runer);
    }


    public String delete(Organiser organiserForDelete) throws BasicApplicationException {
        this.removeUzer(organiserForDelete);
        return "listOfOrganisers";

    }

    public String getOutputDate(Date date) {

        SimpleDateFormat outDate = new SimpleDateFormat("yyyy-mm-dd");
        return outDate.format(date);

    }

    @BinderPageBean
    public Runner getViewedRunner() {
        return viewedRunner;
    }

    @BinderPageBean
    public void setViewedRunner(Runner viewedRunner) {
        this.viewedRunner = viewedRunner;
    }

    public List<Runner> getAllRunners() throws WrongUzerApplicationException {

        if (getLoggedUser() instanceof Administrator) {
            return uzerEndPoint.getAllRunners();
        } else {
            throw new WrongUzerApplicationException();
        }
    }



    public Runner getEditetRunner() {
        return editetRunner;
    }
//@BinderPageBean

    public void setEditetRunner(Runner editetRunner) {
        this.editetRunner = editetRunner;
    }

    ///////////////////////////////////////////////////////////////////
    ///////// przeuniete z pagebeanow
    ///////////////////////////////////////////////////////////////////
    public String saveNew(Runner runner) throws BasicApplicationException {
        uzerEndPoint.createUzer(runner);
        AplicationController.showInfoSuccesMessage("");
        return "registerUser-return";

    }


    public void removeUzer(Uzer uzer) throws BasicApplicationException {
        uzerEndPoint.remove(uzer);
    }

    public String deleteRunner(Runner runnerForDelete) throws BasicApplicationException {
        this.removeUzer(runnerForDelete);
        return "listOfRunners";
    }

    public Uzer getUzerByEmail(String email) {
        return uzerEndPoint.findUzerByEmail(email);

    }

    public Uzer getLoggedUser() {
        return uzerEndPoint.getLoggedUser();
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!11

    public String deleteViewedOrganiser(Organiser org) throws BasicApplicationException {
        this.removeUzer(org);
        this.setViewedOrganiser(null);
        AplicationController.showInfoSuccesMessage(null);

        //  this.setViewedOrganiser(null);
        return "listOfOrganisers";
    }

    public String deleteViewedRunner(Runner runer) throws BasicApplicationException {
        //Runner run = viewedRunner;
        this.removeUzer(runer);
        this.setViewedRunner(null);
        AplicationController.showInfoSuccesMessage(null);

        return "listOfRunners";

    }

    public String editLoggedUzer() throws BasicApplicationException {
        Uzer loggedUzer = getLoggedUser();
        if (loggedUzer instanceof Runner) {
            this.setEditetRunner((Runner) loggedUzer);
            return "/runnerEdit";
        } else if (loggedUzer instanceof Organiser) {
            this.setEditetOrganiser((Organiser) loggedUzer);
            return "/organiserEdit";
        }
        throw new WrongUzerApplicationException();

    }

    /**
     * metoda zwracająca mozliwośc testowania pierdół, interceptorów itp
     * controlera in vivo
     *
     */
    @Deprecated
    public String test() throws Throwable {
        // throw new Error("test");
        //String email="run1@run1.pl";
  
        return null;
    }

    public String getUzersName() {

        if (null != FacesContext.getCurrentInstance().getExternalContext().getRemoteUser()) {
            //  setLoggedUzer();

            return this.getLoggedUser().getName();

        } else {
            return "";
        }
    }

    public String saveNewPasswordForLoggedUzer(String passwd) {
        uzerEndPoint.saveNewPasswordForLogged(passwd); //To change body of generated methods, choose Tools | Templates.
        AplicationController.showInfoSuccesMessage(null);
        return "index";
    }

    public boolean doesGivenEmailExists(String email) {

        return uzerEndPoint.checkEmailExistsInDB(email);

    }

//    @Deprecated
//    public List<Uzer> getAllUzers() {
//        return uzerEndPoint.getAllUzers();
//    }
}
