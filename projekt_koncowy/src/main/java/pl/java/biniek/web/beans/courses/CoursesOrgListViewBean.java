package pl.java.biniek.web.beans.courses;

import pl.java.biniek.web.beans.controlers.CourseController;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exception.interceptor.frontend.BinderStringToNull;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
//import com.mycompany._mavenproject1.BinderBeans;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Organiser;
import pl.java.biniek.web.beans.controlers.UzerController;
//import org.primefaces.showcase.service.CarService;

@Named("dtListOfOrgCourses")
@ViewScoped
@BinderPageBean
public class CoursesOrgListViewBean implements Serializable {

    @Inject
    CourseController courseControler;

    @Inject
    UzerController uzerControler;
       private List<Course> organiserCourses = null;
       Organiser viewedOrg;
@PostConstruct
public void init(){
    viewedOrg=courseControler.getOrganiser();
    courseControler.setOrganiser(null);
}
       
       
       
    @BinderStringToNull
    public List<Course> getAllCourses() throws WrongUzerApplicationException {
         if (organiserCourses == null) {
            organiserCourses = courseControler.getAlUzerCourses(viewedOrg);
            
        }
        return organiserCourses;
    }

  

    public CourseController getCourseControler() {
        return courseControler;
    }

    public String setViewed(Course viewedCourse) {
        courseControler.setViewedCourse(viewedCourse);
        courseControler.setBackNavigatedFromPrivateRuns(true);
        return "courseDetails";
    }

    public String delete(Course course) throws BasicApplicationException {

        courseControler.setIsNavigatedFromMyCourses(true);
        return courseControler.deleteCourse(course);


    }

    public String editCourse(Course viewedCourse) {

        courseControler.setEditetCourse(viewedCourse);
        courseControler.setViewedCourse(null);
        return "courseEdit";
    }



    public String getOutputDateTime(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm");
        return formatter.format(date);
    }

    

}
