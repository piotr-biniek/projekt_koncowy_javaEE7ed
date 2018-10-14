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
public class NullUzerApplicationException extends WrongUzerApplicationException {

    static final long serialVersionUID = 40L;

    public NullUzerApplicationException() {
    }

    public NullUzerApplicationException(String message) {
        super(message);
    }

    public NullUzerApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullUzerApplicationException(Throwable cause) {
        super(cause);
    }

}
