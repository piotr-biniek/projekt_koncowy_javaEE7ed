package pl.java.biniek.exception.interceptor.backend;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.faces.context.FacesContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.TransactionRequiredException;
import pl.java.biniek.exceptions.DBException;
import pl.java.biniek.exceptions.DBExistException;
import pl.java.biniek.exceptions.OverLimitRunnersException;
import pl.java.biniek.exceptions.UnexpectedDBException;

public class ExceptionAndLoggingInterceptorWithRepackingExceptionsForFACADE {

    @Resource
    private SessionContext sessionContext;

    boolean success = true;

    @AroundInvoke
    public Object interceptorMethodforFacades(InvocationContext invocation) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append("Method facade called: " + invocation.getTarget().getClass().getName() + "." + invocation.getMethod().getName());
        sb.append("bu user: " + sessionContext.getCallerPrincipal().getName());
        sb.append(", SessionID:  " + FacesContext.getCurrentInstance().getExternalContext().getSessionId(false));

        try {
            //    System.out.println("*** DefaultInterceptor intercepting " + ctx.getMethod().getName());

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

        } catch (EntityExistsException ex) {          
            success = false;
            sb.append(" occured EXCEPTION " + ex);
            throw new DBException(DBException.ENTITY_EXISTS_EXCEPTION, ex);

        } catch (EntityNotFoundException ex) {
            sb.append(" occured EXCEPTION " + ex);
            success = false;
            throw new DBException(DBException.ENTITY_NOT_FOUND_EXCEPTION, ex);

        } catch (NoResultException ex) {
            sb.append(" occured EXCEPTION " + ex);
            success = false;
            throw new DBException(DBException.NO_RESULT_EXCEPTION, ex);

        } catch (TransactionRequiredException ex) {
            sb.append(" occured EXCEPTION " + ex);
            success = false;
            throw new DBException(DBException.TRANSACTION_REQUIRED_EXCEPTION, ex);

        } catch (OptimisticLockException ex) {
            sb.append(" occured EXCEPTION " + ex);
            success = false;
            throw new DBException(DBException.OPTIMISTIC_LOCK_EXCEPTION, ex);
        } catch (OverLimitRunnersException ex) {
            sb.append(" occured EXCEPTION " + ex);
            success = false;
            throw ex;
        } catch (Exception ex) {
            sb.append(" occured EXCEPTION in facade " + ex);
            success = false;
            throw new UnexpectedDBException(ex);          

        } finally {
            if (!success) {
                Logger.getGlobal().log(Level.SEVERE, sb.toString());
            } else {
                Logger.getGlobal().log(Level.INFO, sb.toString());
            }
        }
    }
}
