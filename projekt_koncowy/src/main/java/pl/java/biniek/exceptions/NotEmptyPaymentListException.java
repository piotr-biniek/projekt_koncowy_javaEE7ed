/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.exceptions;

import javax.faces.application.FacesMessage;
import pl.java.biniek.web.beans.controlers.AplicationController;

/**
 *
 * @author piotr
 */
public class NotEmptyPaymentListException extends BasicApplicationException {
    
     static final long serialVersionUID = 80L;

    
  //   
    
    public NotEmptyPaymentListException() {
        super();
        
    }
    
}
