// chyba raczej ok 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.web.beans.courses;

import pl.java.biniek.web.beans.controlers.CourseController;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.inject.Inject;
import org.primefaces.event.timeline.TimelineSelectEvent;
import org.primefaces.model.timeline.TimelineEvent;
import org.primefaces.model.timeline.TimelineModel;
import pl.java.biniek.exception.interceptor.frontend.BinderPageBean;
import pl.java.biniek.model.Course;

@Named("courseTimelineView")
@ViewScoped
@BinderPageBean

public class CourseTimelineView implements Serializable {

    @Inject
    CourseController courseControler;
    List<Course> courses;

    private TimelineModel model;

    private boolean selectable = true;
    private boolean zoomable = true;
    private boolean moveable = true;
    private boolean stackEvents = true;
    private String eventStyle = "box";
    private boolean axisOnTop;
    private String selectedCoursValue = " ";

    private boolean showCurrentTime = true;
    private boolean showNavigation = true;
    private Date date;

    private boolean showDetailsButton = false;



    @PostConstruct
    protected void initialize() {
        // System.out.println("Destroy: " + this.getClass().getCanonicalName());

        model = new TimelineModel();
        date = new Date();

        courses = courseControler.getAllCourses();
        for (Course course : courses) {
            model.add(new TimelineEvent(course.getShortName(), course.getDateOfStart()));

        }
    }
   public Course findCourseByShortName(String shortName) {
       return courseControler.findByShortName(shortName);
    }

    public void setShowDetailsButton(boolean showDetailsButton) {
        this.showDetailsButton = showDetailsButton;
    }

    public boolean isShowDetailsButton() {
        return showDetailsButton;
    }

    public void onSelect(TimelineSelectEvent e) {
        TimelineEvent timelineEvent = e.getTimelineEvent();
        Course course = findCourseByShortName((String) timelineEvent.getData()); 
        courseControler.setViewedCourse(course);
        setShowDetailsButton(true);
        setSelectedCoursValue("Info: " + course.getName() + ", " + String.valueOf(course.getDistance()) + "km, " + course.getCityOfCours());

    }

    public String getSelectedCoursValue() {
        return selectedCoursValue;
    }

    public void setSelectedCoursValue(String selectedCoursValue) {
        this.selectedCoursValue = selectedCoursValue;
    }

    public TimelineModel getModel() {
        return model;
    }

    public boolean isSelectable() {
        return selectable;
    }

    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    public boolean isZoomable() {
        return zoomable;
    }

    public void setZoomable(boolean zoomable) {
        this.zoomable = zoomable;
    }

    public boolean isMoveable() {
        return moveable;
    }

    public void setMoveable(boolean moveable) {
        this.moveable = moveable;
    }

    public boolean isStackEvents() {
        return stackEvents;
    }

    public void setStackEvents(boolean stackEvents) {
        this.stackEvents = stackEvents;
    }

    public String getEventStyle() {
        return eventStyle;
    }

    public void setEventStyle(String eventStyle) {
        this.eventStyle = eventStyle;
    }

    public boolean isAxisOnTop() {
        return axisOnTop;
    }

    public void setAxisOnTop(boolean axisOnTop) {
        this.axisOnTop = axisOnTop;
    }

    public boolean isShowCurrentTime() {
        return showCurrentTime;
    }

    public void setShowCurrentTime(boolean showCurrentTime) {
        this.showCurrentTime = showCurrentTime;
    }

    public boolean isShowNavigation() {
        return showNavigation;
    }

    public void setShowNavigation(boolean showNavigation) {
        this.showNavigation = showNavigation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   @Deprecated
    public Course findByName(String shortName) {
        for (Course course : courses) {
            if (shortName.equals(course.getShortName())) {

                return course;
            }

        }
        return null;
        //
    }
}
