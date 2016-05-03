package albert.practice.mail;

import java.io.File;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import albert.practice.mail.params.EmailParams;

@Slf4j
public class ExchangeServerMailTest {

    public static void main(String[] args) {
        // set email parameters
        EmailParams params = new EmailParams();
        params.setReceiverEmail("albert_kuo@cht.com.tw");
        params.setSubject("測試一下");
        params.setContent("測試測試測試測試");

        // call sendMail API
        new ExchangeServerMailTest().sendMail(params);
    }

    public void sendMail(EmailParams params) {
        JavaMailSenderImpl sender = getJavaMailSender();
        MimeMessage message = sender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(params.getReceiverEmail());
            helper.setFrom("albert_kuo@cht.com.tw");
            helper.setSubject(params.getSubject());
            helper.setText(params.getContent(), false);

            // set attachment
            FileSystemResource attachment = new FileSystemResource(new File(
                    "D:\\dropbox\\退匯明細表.pdf"));
            helper.addAttachment("退匯明細表.pdf", attachment);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        sender.send(message);

        log.debug("mail sent..");
    }

    private JavaMailSenderImpl getJavaMailSender() {
        Properties props = new Properties();
        props.setProperty("mail.debug", "true");
        props.put("mail.smtp.auth", true);
        // Fix Excpetion: Mail server connection failed; nested exception is
        // javax.mail.MessagingException: Could not convert socket to TLS
        // If set, and a socket factory hasn't been specified, enables use of a
        // MailSSLSocketFactory. If set to "*", all hosts are trusted. If set to a whitespace
        // separated list of hosts, those hosts are trusted. Otherwise, trust depends on the
        // certificate the server presents.
        props.put("mail.smtp.ssl.trust", "*");

        // mail server configuration
        String host = "email.cht.com.tw";
        int port = 25;
        String userName = "userName";
        String password = "password";

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);
        sender.setDefaultEncoding("UTF-8");

        return sender;
    }

}
