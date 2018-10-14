/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.endpoints.service;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


/**
 *
 * @author piotr
 */
public class LoggerSetup {

    static boolean isSet = false;
    static Handler fileHandler = null;

    public static void setUpLogger() {
        if (!isSet) {

            try {

                fileHandler = new FileHandler("./logfile.txt");//file
                SimpleFormatter simple = new SimpleFormatter();
                fileHandler.setFormatter(simple);

            } catch (IOException e) {
                Logger.getGlobal().log(Level.SEVERE, "Exception in LOGGER INIT!!!! : " + e.toString());
            }

        }

    }

}
