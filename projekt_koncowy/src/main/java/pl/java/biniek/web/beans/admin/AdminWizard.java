
package pl.java.biniek.web.beans.admin;
import pl.java.biniek.web.beans.controlers.UzerController;
import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.model.Administrator;
import pl.java.biniek.web.beans.controlers.AplicationController;

 
@Named
@ViewScoped
//@BinderBeans
public class AdminWizard implements Serializable {
    
 //    @EJB
 //   OrganiserEndPoint organiserEndPoint;
    
@Inject
UzerController uzrController;

 
    private Administrator admin = new Administrator();

    public UzerController getUzrController() {
        return uzrController;
    }

    public void setUzrController(UzerController uzrController) {
        this.uzrController = uzrController;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }
     
    

       public String save() throws BasicApplicationException {        
       return  uzrController.saveNew(admin);
        
    }
       
        public String onFlowProcess(FlowEvent event) {
         if (uzrController.doesGivenEmailExists(admin.getEmail())) {
            AplicationController.showMessage("formRegister:email", "messages.constraint.email");

            return "personal";
       
        } else {
            return event.getNewStep();
        }
       //     return "confirm";
       
        }
    
}
