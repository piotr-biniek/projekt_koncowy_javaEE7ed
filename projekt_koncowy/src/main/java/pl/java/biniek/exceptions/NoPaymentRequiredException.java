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
public class NoPaymentRequiredException extends BasicApplicationException {

    static final long serialVersionUID = 60L;

    public NoPaymentRequiredException() {
    }

    public NoPaymentRequiredException(String message) {
        super(message);
    }

    public NoPaymentRequiredException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoPaymentRequiredException(Throwable cause) {
        super(cause);
    }

}
