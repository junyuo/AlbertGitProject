package albert.practice.mail;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

public class MailTest {

    private VelocityEngine velocityEngine;

    public static void main(String[] args) {
        new MailTest().testMail2();
    }

    private void testMail1() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");

        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "junyuo";
        String password = "xxx";

        String mailTo = "junyuo@gmail.com";
        String subject = "網路投保完成通知";

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);

        // set email default encoding
        sender.setDefaultEncoding("UTF-8");

        String content = "<p>親愛的客戶您好:</p>";
        content += "<p>您的網路申請驗證碼為<font color='red'>12345</font>，僅限會員申請使用，請於收到e-mail <font color='red'>10分鐘內完成驗證，10分鐘後將自動失效</font></p>";
        content += "<p>XXXX敬上</p>";

        MimeMessage message = sender.createMimeMessage();
        try {

            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setTo(mailTo);
            helper.setSubject(subject);

            // true means it is a html format email
            helper.setText(content, true);

            FileSystemResource panda01 = new FileSystemResource(new File(
                    "D://dropbox//picture//201602011033331.png"));
            helper.addInline("pand01", panda01);

            FileSystemResource panda02 = new FileSystemResource(new File(
                    "D://dropbox//picture//panda.png"));
            helper.addInline("panda02", panda02);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        sender.send(message);
        System.out.println("mail sent..");
    }

    // http://www.codingpedia.org/ama/how-to-compose-html-emails-in-java-with-spring-and-velocity/
    private void testMail2() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");

        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "junyuo";
        String password = "xxx";

        String mailTo = "junyuo@gmail.com";
        String subject = "網路投保完成通知";

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);
        sender.setDefaultEncoding("UTF-8");

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        velocityEngine = (VelocityEngine) context.getBean("velocityEngine");

        Customer customer = new Customer();
        customer.setPolicyNumber("12345678");
        customer.setName("測試");
        customer.setApplyDate("20160325");
        customer.setFromDate("20160401");
        customer.setToDate("20160410");
        customer.setPlace("日本關西");

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("customer", customer);

        String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
                "albert/practice/mail/insurance.vm", "UTF-8", model);

        MimeMessage message = sender.createMimeMessage();
        try {

            MimeMessageHelper helper;
            helper = new MimeMessageHelper(message, true);
            helper.setTo(mailTo);
            helper.setSubject(subject);

            // true means it is a html format email
            helper.setText(text, true);

            FileSystemResource panda01 = new FileSystemResource(new File(
                    "D://dropbox//picture//201602011033331.png"));
            helper.addInline("pand01", panda01);

            FileSystemResource panda02 = new FileSystemResource(new File(
                    "D://dropbox//picture//panda.png"));
            helper.addInline("panda02", panda02);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        sender.send(message);
        System.out.println("mail sent..");

    }
}
