package pl.java.biniek.web.beans.runners;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Runner;
import pl.java.biniek.web.beans.controlers.UzerController;

@ViewScoped
@Named
@BinderPageBean
public class RunnerEditBean implements Serializable {

    @Inject
    UzerController uzerControler;

     Runner runner;

    @PostConstruct
    public void init() {
        runner = uzerControler.getEditetRunner();
    }

    public Runner getRunner() {
        return runner;
    }

    public void setRunner(Runner runner) {
        this.runner = runner;
    }
    
    
    
    public UzerController getRunnerControler() {
        return uzerControler;
    }

    public String save() throws BasicApplicationException {
// 

        return uzerControler.saveEditedRunner(runner);

    }

}
