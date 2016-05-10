package albert.practice.mail;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;

import lombok.extern.slf4j.Slf4j;

import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * Used to check the status of exchange server .
 */
@Slf4j
public class CheckSmtpStatus {

    /**
     * The main method.
     * 
     * @param args
     *            the arguments
     * @throws MessagingException
     *             the messaging exception
     */
    public static void main(String[] args) throws MessagingException {
        String host = "smtp host name";
        int port = 25;
        String emailU = "username";
        String emailP = "password";

        CheckSmtpStatus checkSmtpStatus = new CheckSmtpStatus();
        boolean isConnected = checkSmtpStatus.isConnected(host, port, emailU, emailP);
        log.debug("isConnected = " + isConnected);
    }

    /**
     * Checks if is connected.
     * 
     * @param host
     *            the host
     * @param port
     *            the port
     * @param emailU
     *            the email u
     * @param emailP
     *            the email p
     * @return true, if is connected
     * @throws MessagingException
     *             the messaging exception
     */
    public boolean isConnected(String host, int port, String emailU, String emailP)
            throws MessagingException {
        boolean isConnected = false;
        Transport transport = null;
        Session session = createJavaMailSender(host, port, emailU, emailP);
        try {
            transport = session.getTransport("smtp");
            transport.connect(host, port, emailU, emailP);

            // used to check this SMTP service is currently connected or not
            isConnected = transport.isConnected();
        } catch (NoSuchProviderException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } finally {
            transport.close();
        }
        return isConnected;
    }

    private Session createJavaMailSender(String host, int port, String emailU, String emailP) {
        Properties properties = new Properties();
        properties.setProperty("mail.debug", "true");
        properties.setProperty("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "*");

        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setJavaMailProperties(properties);
        javaMailSender.setHost(host);
        javaMailSender.setPort(port);
        javaMailSender.setDefaultEncoding("UTF-8");
        javaMailSender.setUsername(emailU);
        javaMailSender.setPassword(emailP);

        return javaMailSender.getSession();
    }

}
