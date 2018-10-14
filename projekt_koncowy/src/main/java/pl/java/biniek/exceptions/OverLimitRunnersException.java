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
@ApplicationException(rollback=true)
public class OverLimitRunnersException extends BasicApplicationException{
static final long serialVersionUID=30L;
    public OverLimitRunnersException() {
    }

    public OverLimitRunnersException(String message) {
        super(message);
    }

    public OverLimitRunnersException(String message, Throwable cause) {
        super(message, cause);
    }

    public OverLimitRunnersException(Throwable cause) {
        super(cause);
    }
    
    
    
}
