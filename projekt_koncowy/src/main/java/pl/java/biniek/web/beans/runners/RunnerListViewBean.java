//ok
package pl.java.biniek.web.beans.runners;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exception.interceptor.frontend.BinderStringToNull;
import pl.java.biniek.model.Administrator;
import pl.java.biniek.model.Runner;
import pl.java.biniek.web.beans.controlers.UzerController;

@Named("dtListOfRunners")
@ViewScoped
@BinderPageBean
public class RunnerListViewBean implements Serializable {

    @Inject
    UzerController uzerControler;

    List<Runner> runnersList;
    



    public UzerController getRunnerControler() {
        return uzerControler;
    }

    public String getOutputDate(Date date) {

        SimpleDateFormat outDate = new SimpleDateFormat("yyyy-mm-dd");
        return outDate.format(date);

    }
@BinderStringToNull
    public List<Runner> getAllRunners2() throws WrongUzerApplicationException {
        runnersList =  uzerControler.getAllRunners();
        return runnersList;

       }

 

    public String setViewed(Runner viewedRunner) {
        uzerControler.setViewedRunner(viewedRunner);
        return "runnerDetails";
    }

    public String delete(Runner runner) throws BasicApplicationException {

        return uzerControler.deleteRunner(runner);

    }

    public String editRunner(Runner viewedRunner) throws WrongUzerApplicationException {
        if (uzerControler.getLoggedUser() instanceof Administrator) {
            uzerControler.setEditetRunner(viewedRunner);
            uzerControler.setViewedRunner(null);
            return "runnerEdit";
        } else {
            throw new WrongUzerApplicationException();
        }
    }
}
