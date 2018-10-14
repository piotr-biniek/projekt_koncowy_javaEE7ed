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
public class BasicApplicationException extends Exception {

    static final long serialVersionUID = 70L;

    public BasicApplicationException() {
    }

    public BasicApplicationException(String message) {
        super(message);
    }

    public BasicApplicationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BasicApplicationException(Throwable cause) {
        super(cause);
    }

}
