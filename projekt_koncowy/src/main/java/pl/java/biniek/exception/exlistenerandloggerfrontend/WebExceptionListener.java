/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.exception.exlistenerandloggerfrontend;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;
import com.sun.faces.application.ActionListenerImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import pl.java.biniek.endpoints.service.LoggerSetup;
import pl.java.biniek.web.beans.controlers.AplicationController;

/**
 * @author java
 */
public class WebExceptionListener extends ActionListenerImpl implements ActionListener {

    @Override
    public void processAction(ActionEvent event) throws AbortProcessingException {
        boolean excepfionOccured = false;
        LoggerSetup.setUpLogger();
        StringBuilder sb = new StringBuilder();
        FacesContext fContext = FacesContext.getCurrentInstance();
        sb.append("WebListener called entering: " + event.toString() + ", element id " + event.getComponent().getClientId());//event.getPhaseId()
        sb.append(", SessionID : " + fContext.getExternalContext().getSessionId(false));
        sb.append(", by user: " + fContext.getExternalContext().getRemoteUser() + " contextname: " + fContext.getExternalContext().getContextName());
        sb.append(", element: " + ((HttpServletRequest) fContext.getExternalContext().getRequest()).getLocalName());

        try {
            super.processAction(event);
        } catch (Throwable ex) {
            Logger.getGlobal().log(Level.SEVERE, "UNCAUGCHT EXCEPTION IN FRONTEND " + this.getClass() + " Session ID"
                    + fContext.getExternalContext().getSessionId(false), ex);
            sb.append(" Session ID" + fContext.getExternalContext().getSessionId(false));
            excepfionOccured = true;
            //AplicationController.resetSession();     
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "failure.page.message");
            // NavigationHandler naviHandler = fContext.getApplication().getNavigationHandler().
            // naviHandler.handleNavigation(fContext, null, "failure");

        } finally {
            if (excepfionOccured) {
                Logger.getGlobal().log(Level.SEVERE, sb.toString());
            } else {
                Logger.getGlobal().log(Level.INFO, sb.toString());
            }
        }
    }

}
