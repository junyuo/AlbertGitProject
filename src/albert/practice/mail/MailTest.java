package albert.practice.mail;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;

import albert.practice.file.FileBase64Util;
import albert.practice.mail.params.Attachment;
import albert.practice.mail.params.EmailParams;

public class MailTest {

    public static void main(String[] args) throws IOException, MessagingException {

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        VelocityEngine velocityEngine = (VelocityEngine) context.getBean("velocityEngine");

        // set value to template
        Customer customer = new Customer();
        customer.setPolicyNumber("12345678");
        customer.setName("測試");
        customer.setApplyDate("20160325");
        customer.setFromDate("20160401");
        customer.setToDate("20160410");
        customer.setPlace("日本關西");

        // set customer to map for velocity email template
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("customer", customer);

        // get email content from velocity email template
        String mailTemplate = "albert/practice/mail/templates/insurance.vm";
        String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mailTemplate,
                "UTF-8", model);

        // set file attachments
        File pdfFile = new File("D://dropbox//Getting Started.pdf");
        File mindmapFile = new File("D://dropbox//eBooks//The Intelligent Investor.png");

        List<File> attachments = new ArrayList<File>();
        attachments.add(pdfFile);
        attachments.add(mindmapFile);

        // set email parameters
        EmailParams params = new EmailParams();
        params.setReceiverEmail("xxx@gmail.com");
        params.setSubject("網路投保完成通知");
        params.setContent(content);
        params.setAttachments(attachments);

        // new MailTest().sendMail(params);

        File source1 = new File("D://dropbox/test.pdf");
        InputStream sourcetStream1 = null;

        sourcetStream1 = new FileInputStream(source1);
        byte[] encodedSource1 = FileBase64Util.getEncodeBase64String(source1).getBytes();

        Attachment attachment1 = new Attachment();
        attachment1.setFileName("測試.pdf");
        attachment1.setBytes(encodedSource1);

        File source2 = new File("D://dropbox/FMS 資料來源.pptx");
        InputStream sourcetStream2 = null;

        sourcetStream2 = new FileInputStream(source1);
        byte[] encodedSource2 = FileBase64Util.getEncodeBase64String(source1).getBytes();

        Attachment attachment2 = new Attachment();
        attachment2.setFileName("FMS 資料來源2.pptx");
        attachment2.setBytes(encodedSource2);

        List<Attachment> encodedAttachment = new ArrayList<Attachment>();
        encodedAttachment.add(attachment1);
        encodedAttachment.add(attachment2);

        params.setEncodedBytes(encodedAttachment);

        if (sourcetStream1 != null) {
            sourcetStream1.close();
        }

        new MailTest().sendMail2(params);

        // new MailTest().sendMail2(params);
        // new MailTest().testMail2();
    }

    public void sendMail(EmailParams params) {
        JavaMailSenderImpl sender = getJavaMailSender();
        MimeMessage message = sender.createMimeMessage();

        List<File> attachemtns = null;

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(params.getReceiverEmail());
            helper.setSubject(params.getSubject());
            helper.setText(params.getContent(), true);

            if (params.getAttachments() != null && params.getAttachments().size() > 0) {
                attachemtns = params.getAttachments();
                for (int i = 0; i < attachemtns.size(); i++) {
                    FileSystemResource attachment = new FileSystemResource(attachemtns.get(i));
                    helper.addAttachment(attachemtns.get(i).getName(), attachment);
                }
            }

            InputStreamSource logo = new ByteArrayResource(IOUtils.toByteArray(getClass()
                    .getResourceAsStream("img/panda.png")));
            helper.addInline("panda", logo, "image/png");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sender.send(message);
        System.out.println("mail sent..");
    }

    public void sendMail2(EmailParams params) {
        System.out.println("sendMail2....");
        JavaMailSenderImpl sender = getJavaMailSender();
        MimeMessage message = sender.createMimeMessage();
        List<Attachment> attachments = null;
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(params.getReceiverEmail());
            helper.setSubject(params.getSubject());
            helper.setText(params.getContent(), true);

            if (params.getAttachments() != null && params.getAttachments().size() > 0) {
                attachments = params.getEncodedBytes();
                for (int i = 0; i < attachments.size(); i++) {
                    Attachment attachment = attachments.get(i);
                    byte[] bytes = FileBase64Util.decode(attachment.getBytes());
                    File file = new File("D://dropbox/" + attachment.getFileName());
                    FileUtils.writeByteArrayToFile(file, bytes);
                    helper.addAttachment(attachment.getFileName(), file);
                }
            }

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sender.send(message);

        if (params.getAttachments() != null && params.getAttachments().size() > 0) {
            for (int i = 0; i < attachments.size(); i++) {
                Attachment attachment = attachments.get(i);
                FileUtils.deleteQuietly(new File("D://dropbox/" + attachment.getFileName()));
            }
        }
        System.out.println("mail sent..");
    }

    private void testMail1() {
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");

        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "xxx";
        String password = "xxxx";

        String mailTo = "xxx@gmail.com";
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

    private JavaMailSenderImpl getJavaMailSender() {
        // enable starttls
        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");

        // mail server configuration
        String host = "smtp.gmail.com";
        int port = 587;
        String userName = "xxx";
        String password = "xxx";

        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setJavaMailProperties(props);
        sender.setHost(host);
        sender.setPort(port);
        sender.setUsername(userName);
        sender.setPassword(password);
        sender.setDefaultEncoding("UTF-8");

        return sender;
    }

    // http://www.codingpedia.org/ama/how-to-compose-html-emails-in-java-with-spring-and-velocity/
    // http://docs.spring.io/spring/docs/current/spring-framework-reference/html/mail.html
    // http://openhome.cc/Gossip/SpringGossip/AttachedFileMail.html
    private void testMail2() throws IOException, MessagingException {

        JavaMailSenderImpl sender = getJavaMailSender();

        String mailTo = "xxx@gmail.com";
        String subject = "網路投保完成通知";

        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
        VelocityEngine velocityEngine = (VelocityEngine) context.getBean("velocityEngine");

        // set value to template
        Customer customer = new Customer();
        customer.setPolicyNumber("12345678");
        customer.setName("測試");
        customer.setApplyDate("20160325");
        customer.setFromDate("20160401");
        customer.setToDate("20160410");
        customer.setPlace("日本關西");

        // set customer to map
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("customer", customer);

        MimeMessage message = sender.createMimeMessage();
        try {

            String mailTemplate = "albert/practice/mail/templates/insurance.vm";

            String pdfFile = "D://dropbox//Getting Started.pdf";

            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(mailTo);
            helper.setSubject(subject);

            // get template string
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, mailTemplate,
                    "UTF-8", model);

            // true means it is a html format email
            helper.setText(text, true);

            InputStreamSource logo = new ByteArrayResource(IOUtils.toByteArray(getClass()
                    .getResourceAsStream("img/panda.png")));

            FileSystemResource pdf = new FileSystemResource(new File(pdfFile));

            helper.addInline("pdf", pdf);
            helper.addInline("panda", logo, "image/png");

        } catch (MessagingException e) {
            throw e;
        } catch (IOException e) {
            throw e;
        }
        sender.send(message);
        System.out.println("mail sent..");
    }
}
