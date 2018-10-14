/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.java.biniek.endpoints.service;

/**
 *
 * @author piotr
 */
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Stateless;
import javax.mail.*;
import javax.mail.internet.*;
import pl.java.biniek.model.Course;
import pl.java.biniek.model.Payment;
import pl.java.biniek.model.Runner;

@Stateless
@Singleton
public class MailServiceGmail {

    private static final String USER_NAME = "biegowyportal";  // GMail  name 
    private static final String PASSWORD = "2357portalbiegowy"; // GMail password

    @Asynchronous
    // nie static nie działa asynch
    public void fireCourseChangeEmailInformation(Course changedCourse) {
        final String messageSubject = "Change of data in course you are signed for";
        final String messageBody = "Change of data in course you are signed for " + changedCourse.toMail();
        Payment listOfPayments[] = new Payment[changedCourse.getPayments().size()];
        listOfPayments = changedCourse.getPayments().toArray(listOfPayments);
        if (listOfPayments.length > 0) {
            MailServiceGmail.sendEmailFromGmail(getEmails(changedCourse), messageSubject, messageBody);
        }

    }

    @Asynchronous
    // nie static
    public void fireSignedForCourseInformation(Runner runner, Course course) {
        final String messageSubject = "Signed for Course :" + course.getName();
        final String messageBody = "You have been signed For Course " + course.toMail();
       
        if (runner != null && course != null) {
            String[] recepient = {runner.getEmail()};

            sendEmailFromGmail(recepient, messageSubject, messageBody);
        
        }

    }

    public static void fireCourseIncomingDate(Course incomingCourse) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//todo dopisac metode i singleton do tego

    }

    private static void sendEmailFromGmail(String[] recepients, String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", USER_NAME);
        props.put("mail.smtp.password", PASSWORD);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);
        boolean succes = true;
        StringBuilder sb = new StringBuilder();
        try {
            message.setFrom(new InternetAddress(USER_NAME));
            InternetAddress[] toAddress = new InternetAddress[recepients.length];

            for (int i = 0; i < recepients.length; i++) {
                toAddress[i] = new InternetAddress(recepients[i]);
            }

            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(subject, "utf-8" );
            message.setText(body,"utf-8" );
            Transport transport = session.getTransport("smtp");
            transport.connect(host, USER_NAME, PASSWORD);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (AddressException ae) {
            sb.append("EXCEPTION  in MailService " + ae);
            Logger.getGlobal().log(Level.SEVERE, sb.toString());
            // ae.printStackTrace();
        } catch (MessagingException me) {
            sb.append("EXCEPTION in MailService " + me);
            Logger.getGlobal().log(Level.SEVERE, sb.toString());
        }

    }

    private static String[] getEmails(Course course) {//pomyslec o przerobieniu na kwerendę!

        Payment listOfPayments[] = new Payment[course.getPayments().size()];
        listOfPayments = course.getPayments().toArray(listOfPayments);
        String emails[] = new String[course.getPayments().size()];
        for (int i = 0; i < listOfPayments.length; i++) {
            emails[i] = listOfPayments[i].getRunner().getEmail();

        }
        return emails;
    }

}

       // listOfPayments = course.getPayments().toArray(listOfPayments);
