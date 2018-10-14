package pl.java.biniek.exception.interceptor.backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import pl.java.biniek.exceptions.BasicApplicationException;

public class LoggingInterceptorWithRepackingForEndPoint {

    boolean success = true;

    @Resource
    private SessionContext sessionContext;

    @AroundInvoke
    public Object interceptorMethodForDAO(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("EndPoint Method called: " + invocation.getTarget().getClass().getName() + "." + invocation.getMethod().getName());
        sb.append("bu user: " + sessionContext.getCallerPrincipal().getName());
        sb.append(", SessionID:  " + FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));

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

            Object result = invocation.proceed();

            if (result != null) {
                sb.append(" returned " + result.getClass().getName() + "=" + result.toString());
            } else {
                sb.append(" returned null");
            }

            success = true;
            return result;
} catch (BasicApplicationException ex) {
            
         Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in EndPoint: " + ex.toString());
            success = false;
            sb.append(" occured exception " + ex);
            //  sb.append(" occured EXCEPTION " + ex);

            throw ex;       
            
        } catch (Exception ex) {

             
            Logger.getGlobal().log(Level.SEVERE, "EXCEPTION OCCURED in EndPoint: " + ex.toString());
            success = false;
            sb.append(" occured exception " + ex);
            //  sb.append(" occured EXCEPTION " + ex);

            throw new BasicApplicationException("przepak", ex);

         
        } finally {
            if (!success) {
                Logger.getGlobal().log(Level.SEVERE, sb.toString());
            } else {
                Logger.getGlobal().log(Level.INFO, sb.toString());
            }

        }

    }
}
