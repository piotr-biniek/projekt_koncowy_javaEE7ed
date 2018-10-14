package pl.java.biniek.exception.interceptor.frontend;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

@Interceptor
@BinderStringToNull
public class InterceptorChangingStringToNull implements Serializable {

    Object result;

    @AroundInvoke
    public Object interceptorMethodForDAO(InvocationContext invocation) throws Exception {
        FacesContext fc = FacesContext.getCurrentInstance();
        StringBuilder sb = new StringBuilder();
        try {

            result = invocation.proceed();
        } catch (ClassCastException ex) {
            Logger.getGlobal().log(Level.WARNING, "EXCEPTION  occured in InterceptorChangingStringToNull: " + ex.toString() + " SesionID " + fc.getExternalContext().getSessionId(false));
            sb.append(" occured exception " + ex);
            sb.append(" " + invocation.getTarget().getClass().getName() + "." + invocation.getMethod().getName());
            sb.append(", by user: " + fc.getExternalContext().getRemoteUser());
            sb.append(", SessionID:  " + fc.getExternalContext().getSessionId(false));
            Logger.getGlobal().log(Level.WARNING, sb.toString());
            result = null;
        } finally {
            return result;
        }
    }
}
