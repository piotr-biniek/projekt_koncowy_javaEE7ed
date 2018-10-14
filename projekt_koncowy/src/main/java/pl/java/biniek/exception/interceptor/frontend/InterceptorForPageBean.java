package pl.java.biniek.exception.interceptor.frontend;

import pl.java.biniek.exceptions.BasicApplicationException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pl.java.biniek.web.beans.controlers.AplicationController;

@Interceptor
@BinderPageBean
public class InterceptorForPageBean implements Serializable {

    private boolean success = true;
    Object result;


    @AroundInvoke
    public Object interceptorMethodForPageBean(InvocationContext invocation) throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("PageBean method called: " + invocation.getTarget().getClass().getName() + "." + invocation.getMethod().getName());
        sb.append(", by user: " + fc.getExternalContext().getRemoteUser());
        sb.append(", SessionID:  " + fc.getExternalContext().getSessionId(false));
        //  sb.append(" SesionID "+fc.getExternalContext().getSessionId(false));
        try {
        

            Object[] parameters = invocation.getParameters();
            if (null != parameters) {
                for (Object param : parameters) {
                    if (param != null) {
                        sb.append(" with params: " + param.getClass().getName() + "=" + param.toString());
                    } else {
                        sb.append("  with NULL");
                    }
                }
            }
            result = invocation.proceed();
            if (result != null) {
                sb.append(" returned " + result.getClass().getName() + "=" + result.toString());
            } else {
                sb.append(" returned null");
            }

            success = true;
            return result;
        } catch (BasicApplicationException ex) {
            result = null;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in PAGEBEAN: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_INFO, "messages.excep.other");

        } catch (Exception ex) {
            result = "/error/error";

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in PAGEBEAN: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "error.page.message");

        } catch (Throwable ex) {
            result = "/error/failure";
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "failure.page.message");
            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in PAGEBEAN: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.resetSession();

        } finally {

            if (!success) {
                Logger.getGlobal().log(Level.SEVERE, sb.toString());
            } else {
                Logger.getGlobal().log(Level.INFO, sb.toString());
            }
        }
      
        return result;

    }

}
