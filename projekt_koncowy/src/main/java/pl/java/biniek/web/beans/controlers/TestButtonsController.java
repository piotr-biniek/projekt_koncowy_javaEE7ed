//dostęp bezpieczenstwa ok

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.web.beans.controlers;

import java.io.Serializable;

//@ManagedBean


public class TestButtonsController implements Serializable {


    /**
     * Włączanie i wyłączanie wszystkich przycisków, za pomocą 1 przycisku żeby móc przetestować wszystkie
     *przyciski i działanie ról zapisaych na metodach biznesowych
     * 
     */
   private final static boolean TEST_BUTTONS = false;
           

    /**
     * metoda zwracająca mozliwośc włączania i wyłączanie wszystkich przycisków, za pomocą 1 przycisku żeby móc przetestować wszystkie
     *przyciski i działanie ról zapisaych na metodach biznesowych
     * 
     * @return 
     */
 //  @Nonbinding
    public static boolean isTestButtons() {
        return TEST_BUTTONS;
    }


    
}
