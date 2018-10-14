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
public class DBExistException extends DBException{
      String  name ="DBExistException";

    public DBExistException() {
    }

    public DBExistException(String message) {
        super(message);
    }

    public DBExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public DBExistException(Throwable cause) {
        super(cause);
    }
    
    
    
}
