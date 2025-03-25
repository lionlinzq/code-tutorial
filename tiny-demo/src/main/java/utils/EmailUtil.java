package utils;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * 邮件工具
 * @since: 2022/9/15
 * @author: hongdahao
*/
public class EmailUtil {

    public static class EmailBuiler {
        private String emailHost;
        private String transportType = "smtp";
        private String fromEmail;
        private String authCode;
        private List<Address> toEmails = new LinkedList<>();
        private String subject;
        private String content;

        public String getEmailHost() {
            return emailHost;
        }

        public EmailBuiler setEmailHost(String emailHost) {
            this.emailHost = emailHost;
            return this;
        }

        public String getTransportType() {
            return transportType;
        }

        public EmailBuiler setTransportType(String transportType) {
            this.transportType = transportType;
            return this;
        }

        public String getFromEmail() {
            return fromEmail;
        }

        public EmailBuiler setFromEmail(String fromEmail) {
            this.fromEmail = fromEmail;
            return this;
        }

        public String getAuthCode() {
            return authCode;
        }

        public EmailBuiler setAuthCode(String authCode) {
            this.authCode = authCode;
            return this;
        }

        public List<Address> getToEmails() {
            return toEmails;
        }

        public EmailBuiler addToEmail(String toEmail) {
            InternetAddress address = new InternetAddress();
            address.setAddress(toEmail);
            this.toEmails.add(address);
            return this;
        }

        public EmailBuiler addToEmails(String[] toEmails) {
            for (String toEmail : toEmails) {
                InternetAddress address = new InternetAddress();
                address.setAddress(toEmail);
                this.toEmails.add(address);
            }
            return this;
        }

        public String getSubject() {
            return subject;
        }

        public EmailBuiler setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public String getContent() {
            return content;
        }

        public EmailBuiler setContent(String content) {
            this.content = content;
            return this;
        }

        public void send() throws Exception {
            EmailUtil.send(this);
        }
    }


    public static EmailBuiler build(){
        return new EmailBuiler();
    }


    public static void send(EmailBuiler emailBuiler) throws Exception {
        //初始化默认参数
        Properties props = new Properties();
        props.setProperty("mail.transport.protocol", emailBuiler.getTransportType());
        props.setProperty("mail.host", emailBuiler.getEmailHost());
        props.setProperty("mail.user", emailBuiler.getFromEmail());
        props.setProperty("mail.from", emailBuiler.getFromEmail());
        //获取Session对象
        Session session = Session.getInstance(props, null);
        //开启后有调试信息
        session.setDebug(true);

        //通过MimeMessage来创建Message接口的子类
        MimeMessage message = new MimeMessage(session);

        //下面是对邮件的基本设置
        //设置发件人：
        String fromName = MimeUtility.encodeWord(emailBuiler.getFromEmail());
        InternetAddress from = new InternetAddress(fromName);
        message.setFrom(from);

        //设置收件人：
        message.setRecipients(Message.RecipientType.TO, emailBuiler.getToEmails().toArray(new Address[]{}));

        //设置邮件主题
        message.setSubject(emailBuiler.getSubject());

        //设置邮件内容
        message.setText(emailBuiler.getContent(), "utf-8");

        //保存上面设置的邮件内容
        message.saveChanges();

        //获取Transport对象
        Transport transport = session.getTransport();
        //smtp验证，就是你用来发邮件的邮箱用户名密码（若在之前的properties中指定默认值，这里可以不用再次设置）
        transport.connect(null, null, emailBuiler.getAuthCode());
        //发送邮件
        transport.sendMessage(message, message.getAllRecipients());
    }

}
