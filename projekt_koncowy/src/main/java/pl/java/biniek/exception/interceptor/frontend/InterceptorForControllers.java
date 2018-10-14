package pl.java.biniek.exception.interceptor.frontend; 

import pl.java.biniek.exceptions.DBException;
import pl.java.biniek.exceptions.NullUzerApplicationException;
import pl.java.biniek.exceptions.NoPaymentRequiredException;
import pl.java.biniek.exceptions.BasicApplicationException;
import pl.java.biniek.exceptions.OverLimitRunnersException;
import pl.java.biniek.exceptions.WrongUzerApplicationException;
import pl.java.biniek.exceptions.AftertTimeException;
import pl.java.biniek.exceptions.NullPointerApplicationException;
import pl.java.biniek.exceptions.UnexpectedDBException;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import pl.java.biniek.exceptions.NotEmptyPaymentListException;
import pl.java.biniek.web.beans.controlers.AplicationController;

@Interceptor
@BinderController
public class InterceptorForControllers implements Serializable {

    private boolean success = true;
    Object result;

    //@Resource
//    private SessionContext sessionContext;
    @AroundInvoke
    public Object interceptorMethodForDAO(InvocationContext invocation) throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("CONTROLLER Method called: " + invocation.getTarget().getClass().getName() + "." + invocation.getMethod().getName());
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
//        } catch (DBExistException ex) {
//            result = null;
//            Logger.getGlobal().log(Level.WARNING, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
//            success = false;
//            sb.append(" occured exception " + ex);
//            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_WARN, "messages.excep.exist");
//
//            //  } catch (DBException ex) {
        } catch (UnexpectedDBException ex) {
            result = null;
            Logger.getGlobal().log(Level.WARNING, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_WARN, "messages.excep.dbunknown");

        } catch (DBException ex) {

            result = null;
            Logger.getGlobal().log(Level.WARNING, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_WARN, ex.getMessage());

        } catch (NoPaymentRequiredException ex) {

            result = null;
            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_INFO, "messages.excep.noopaymentreq");

        } catch (NullPointerApplicationException ex) {
            result = null;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "messages.excep.noobject");

        } catch (NullUzerApplicationException ex) {

            result = "/error/error403";

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
              AplicationController.showGeneralMessage(FacesMessage.SEVERITY_INFO, "error.code403.message");


        } catch (WrongUzerApplicationException ex) {
            result = "/error/error403";

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
              AplicationController.showGeneralMessage(FacesMessage.SEVERITY_INFO, "error.code403.message");

        } catch (OverLimitRunnersException ex) {
            result = null;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_INFO, "messages.excep.noplaces");

        } catch (AftertTimeException ex) {
            result = null;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_INFO, "messages.excep.aftertime");

             } catch (NotEmptyPaymentListException ex) {
            result = null;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_WARN, "messages.course.nopermissiontodelete");

            
            
        } catch (BasicApplicationException ex) {
            result = null;

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_INFO, "messages.excep.other");

        } catch (Exception ex) {
            result = "/error/error";

            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "error.page.message");

        } catch (Throwable ex) {
            result = "/error/failure";
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "failure.page.message");
            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in Controller: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            success = false;
            sb.append(" occured exception " + ex);
            AplicationController.showGeneralMessage(FacesMessage.SEVERITY_ERROR, "failure.page.message");

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
