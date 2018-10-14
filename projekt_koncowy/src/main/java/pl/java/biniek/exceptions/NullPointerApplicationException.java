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
public class NullPointerApplicationException extends BasicApplicationException {

    static final long serialVersionUID = 50L;

    public NullPointerApplicationException() {
    }

    public NullPointerApplicationException(String message) {
        super(message);
    }

    public NullPointerApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPointerApplicationException(Throwable cause) {
        super(cause);
    }

}
