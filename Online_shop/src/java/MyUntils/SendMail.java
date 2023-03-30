/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MyUntils;


import java.io.PrintWriter;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;


public class SendMail {

    public static void SendMail(String to, String sub,
            String msg, final String user, final String pass) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });
       
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject(sub);
            message.setContent(msg, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    
    public static void SendMailFunction(String denMailNao, String tieuDeMail,
            String noiDungMail){
        SendMail.SendMail(denMailNao, tieuDeMail, noiDungMail, "hieuhbvipstyle@gmail.com", "eclkgwxiotrgzbqy");
    }

    public static void main(String[] args) {
        String subject = "Confirm Your email !";
        String message = "This is your Code:" + 123;
         SendMailFunction("hieutdhe153401@fpt.edu.vn", "oke hiu", "2333");
    }
}
