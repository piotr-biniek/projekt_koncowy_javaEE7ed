//ok


package pl.java.biniek.web.beans.courses;

import pl.java.biniek.web.beans.controlers.CourseController;
import java.io.Serializable;
import java.util.Date;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.FlowEvent;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exception.interceptor.frontend.BinderStringToNull;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.model.Course;
import pl.java.biniek.web.beans.controlers.AplicationController;

//@ManagedBean
@ViewScoped
//@RequestScoped
@Named
@BinderPageBean

public class CourseWizard implements Serializable {

    @Inject
    CourseController courseController;
    
    @Inject
    AplicationController uzerController;

    private boolean skip;

    private Course course = new Course();

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }
@BinderStringToNull
    public String onFlowProcess(FlowEvent event) {
        if(courseController.doesGivenNameExists(course.getName())){ //uzerController.doesGivenEmailExists(organiser.getEmail())
            AplicationController.showMessage("addCourse:name", "messages.constraint.namecourse");
            return "coursedetails";
        }
        if(courseController.doesGivenShortNameExists(course.getShortName())){ 
            AplicationController.showMessage("addCourse:shortName", "messages.constraint.namecourse");
            return "coursedetails";
        }
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }

    public Date getToday() {

        return new Date();
    }


    public String save()  throws BasicApplicationException{ 
      return  courseController.saveNew(course);
      
       
    }
}
