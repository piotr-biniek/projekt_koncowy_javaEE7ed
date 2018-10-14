/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author samsung
 */
@ApplicationException(rollback = true)
public class AftertTimeException extends BasicApplicationException {

    static final long serialVersionUID = 80L;

    public AftertTimeException() {
    }

    public AftertTimeException(String message) {
        super(message);
    }

    public AftertTimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public AftertTimeException(Throwable cause) {
        super(cause);
    }

}
