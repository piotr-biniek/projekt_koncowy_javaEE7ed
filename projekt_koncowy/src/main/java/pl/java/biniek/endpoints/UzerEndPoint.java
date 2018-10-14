/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.endpoints;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.faces.context.FacesContext;
import javax.interceptor.Interceptors;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.NullPointerApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.facade.OrganiserFacade;
import pl.java.biniek.facade.RunnerFacade;
import pl.java.biniek.facade.UzerFacade;
import pl.java.biniek.exception.interceptor.backend.LoggingInterceptorWithRepackingForEndPoint;
import pl.java.biniek.facade.AdministratorFacade;
import pl.java.biniek.model.Administrator;
import pl.java.biniek.model.Organiser;
import pl.java.biniek.model.Runner;
import pl.java.biniek.model.Uzer;
//import pl.java.biniek.model.Uzer;

/**
 *
 * @author java pbi moje!!!!!
 */
@Stateless
@Interceptors(LoggingInterceptorWithRepackingForEndPoint.class)
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class UzerEndPoint implements Serializable {

    @EJB
    private UzerFacade uzerFacade;

    @EJB
    private OrganiserFacade organiserFacade;

    @EJB
    private RunnerFacade runnerFacade;

    @EJB
   private AdministratorFacade adminFacade;
    
    @RolesAllowed("Administrator")
    public void remove(Uzer uzer) throws BasicApplicationException {
            uzerFacade.remove(uzer);
    

    }

    public List<Uzer> getAllUzers() {
        return uzerFacade.findAll();

    }

    public Uzer findUzerByEmail(String email) {
        return uzerFacade.findUzerByEmail(email);

    }

    public void createUzer(Uzer uzer) throws BasicApplicationException {
        uzerFacade.create(uzer);
    }

    public Uzer getLoggedUser() {
        //    String email = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
        Uzer uzer = findUzerByEmail(this.getLoggedUserEmail());
        if (null != uzer) {
            return uzer;
        } else {
            return null;
        }

    }

    public String getLoggedUserEmail() {
        return FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
    }
     public List<Administrator> getAllAdmins() {
        return adminFacade.findAll();

    }
    public List<Organiser> getAllOrganisers() {
        return organiserFacade.findAll();

    }

    @RolesAllowed({"Organiser", "Administrator"})
    public void saveAfterEdit(Organiser organiser) throws BasicApplicationException {

        Uzer org = getLoggedUser();
        if (organiser == null) {
            throw new NullPointerApplicationException();
        }
        if (org instanceof Organiser && ((!Objects.equals(organiser.getId(), org.getId())))) {
            throw new WrongUzerApplicationException();
        } else {
            organiserFacade.edit(organiser);

        }
    }

    @RolesAllowed({"Runner", "Administrator"})
    public void saveAfterEdit(Runner runner) throws BasicApplicationException {
        Uzer run = getLoggedUser();
        
        if (runner == null) {
            throw new NullPointerApplicationException();
        }
        if (run instanceof Runner && (!Objects.equals(runner.getId(), run.getId()))) {
           
            throw new WrongUzerApplicationException();
        } else {
            runnerFacade.edit(runner);

        }

// starczy prosta zasada - przed edit dobrze po edit Źle!!!!!!! :)
    }

    @RolesAllowed({"Administrator"})

    public List<Runner> getAllRunners() {

        return runnerFacade.findAll();

    }

    @RolesAllowed({"Runner", "Administrator", "Organiser"})
    public void saveNewPasswordForLogged(String passwd) {
        Uzer uzer = this.getLoggedUser();
        uzer.setPassword(passwd);
        uzerFacade.edit(uzer);
    }
   
    public boolean  checkEmailExistsInDB(String email){
        return uzerFacade.checkIfEmailExists(email);
    }
   @RolesAllowed({"Administrator"})   
public void remove(Administrator admin){
    adminFacade.remove(admin);
    
}
@RolesAllowed({"Administrator"})   
public List<Administrator> getAllAdministrators(){
   return adminFacade.findAll();
       
}
@RolesAllowed({"Administrator"})   
public void createAdministrator(Administrator admin) throws BasicApplicationException{
  
    adminFacade.create(admin);
  }
@RolesAllowed({"Administrator"})   
public void saveAfterEdit(Administrator admin){
   
        adminFacade.edit(admin);
       



// starczy prosta zasada - przed edit dobrze po edit Źle!!!!!!! :)

}
}
