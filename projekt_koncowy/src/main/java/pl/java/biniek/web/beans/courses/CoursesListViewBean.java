package pl.java.biniek.web.beans.courses;

import pl.java.biniek.web.beans.controlers.CourseController;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.model.Course;

@Named("dtListOfCourses")
@ViewScoped
@BinderPageBean
public class CoursesListViewBean implements Serializable {

    @Inject
    CourseController courseControler;

private List<Course> futureCourses;
    
    @PostConstruct
    public void init(){
        futureCourses=courseControler.getFutureCourses();
        
    }

    public List<Course> getAllCourses() {
        return futureCourses;
    }

    public void setAllCourses(List<Course> futureCourses) {
        this.futureCourses = futureCourses;
    }
    
    
    
    
    public CourseController getCourseControler() {
        return courseControler;
    }
//

    public String getOutputDateTime(Date date) {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh.mm");
        return formatter.format(date);
    }

   
    
    public String setViewed(Course viewedCourse) {
        courseControler.setViewedCourse(viewedCourse);
        return "courseDetails";
    }

    public String delete(Course course) throws BasicApplicationException {

        return courseControler.deleteCourse(course);

    }

    public String editCourse(Course viewedCourse) throws BasicApplicationException {
        return courseControler.editCourseFromList(viewedCourse);
    }
}
