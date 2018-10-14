
package pl.java.biniek.web.beans.courses;

import pl.java.biniek.web.beans.controlers.CourseController;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Course;

//@ManagedBean
//@RequestScoped
//to do chyba ok ale przemyslec
@ViewScoped
@Named
@BinderPageBean
public class CourseEditBean implements Serializable {

    @Inject
    CourseController courseControler;
    
    Course editedCourse;
    
    @PostConstruct
    public void init() {
        editedCourse = courseControler.getEditetCourse();
    }

    public Course getEditedCourse() {
        return editedCourse;
    }

    public void setEditedCourse(Course editedCourse) {
        this.editedCourse = editedCourse;
    }

  
    public CourseController getCourseControler() {
        return courseControler;
    }

    public String save() {// 
        return courseControler.saveEditedCourse(editedCourse);

    }

}
