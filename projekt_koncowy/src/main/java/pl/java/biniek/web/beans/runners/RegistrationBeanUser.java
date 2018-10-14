//dostęp bezpieczenstwa nd
//ok
package pl.java.biniek.web.beans.runners;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.flow.FlowScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Runner;
import pl.java.biniek.web.beans.controlers.AplicationController;
import pl.java.biniek.web.beans.controlers.UzerController;

@Named
@FlowScoped("registerUser")
@BinderPageBean
public class RegistrationBeanUser implements Serializable {

    @Inject
    UzerController uzerController;

    private Runner runner = new Runner();

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }

    public String save() throws BasicApplicationException {
        String passwordHash = AplicationController.hashPassword(runner.getPassword());
        runner.setPassword(passwordHash);
        //  return 
        uzerController.saveNew(runner);
        return "exitRegistration";/// zmieniono z flow sterowanego plikami na flow sterowany xmlem
        // return "registerUser-return";  
        
    }

    public String next() {
        if (uzerController.doesGivenEmailExists(runner.getEmail())) {
            AplicationController.showMessage("registerRunner:email", "messages.constraint.email");
            return null;
        }
        return "registerUser-confirmation";
    }

    @Deprecated
    public String saveNew() throws BasicApplicationException {
        FacesMessage msg = new FacesMessage("Successful", "Welcome :" + runner.getFirstName());
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "registerUser-return";
    }

    @Deprecated
    public void saveMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getFlash().setKeepMessages(true);

        context.addMessage(null, new FacesMessage("Saved"));
    }
}
