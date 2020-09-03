package com.crossyf.dubbo.springtest.test.testEmail;

import com.sun.mail.util.MailSSLSocketFactory;
import java.io.File;
import java.util.*;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {

    private String from;
    private List<String> to;
    private MailProperties properties;

    public SendEmail(String from, List<String> to, MailProperties properties) {
        this.from = from;
        this.to = to;
        this.properties = properties;
    }

    public void send() throws Exception {

        //获取系统属性
        Properties props = System.getProperties();

        //SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);
        props.setProperty("mail.smtp.host", properties.getHost()); //设置系统属性
        props.put("mail.smtp.auth", "true");

        //获取发送邮件会话、获取第三方登录授权码
        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, properties.getPassword());
            }
        });

        Message message = new MimeMessage(session);

        //防止邮件被当然垃圾邮件处理，披上Outlook的马甲
        message.addHeader("X-Mailer","Microsoft Outlook Express 6.00.2900.2869");

        message.setFrom(new InternetAddress(from));

        List<Address> ads = new ArrayList<>();
        for(String too : to){
            ads.add(new InternetAddress(too));
        }
        message.addRecipients(Message.RecipientType.TO, ads.toArray(new Address[ads.size()])); // 设置多收件人

        message.setSubject("This is the subject line!"); //邮件标题

        /*message.setText("This is actual message"); // 没有附件时可用来设置消息体*/

        // 邮件正文
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText("我发送了文件给你");
        Multipart multipart = new MimeMultipart();

        //附件
        List<String> ls = getFilePaths(properties.getFilePath());
        for(String fileName: ls){
            bodyPart = new MimeBodyPart();
            DataSource dataSource = new FileDataSource(fileName);
            bodyPart.setDataHandler(new DataHandler(dataSource));
            bodyPart.setFileName(dataSource.getName());

            multipart.addBodyPart(bodyPart);
        }

        message.setContent(multipart);

        // 发送消息
        Transport.send(message);
        System.out.println("Sent message successfully....");
    }

    // 获取文件的文件列表
    public List<String> getFilePaths(String filePath){
        List<String> files = new ArrayList<>();

        File fe = new File(filePath);
        if(fe.isDirectory()){
            // 递归考虑下
            File[] fs = fe.listFiles();
            for(File fChild : fs){
                if(fChild.isDirectory()){
                    files.addAll(getFilePaths(fChild.getPath()));
                }else{
                    files.add(fChild.getPath());
                }
            }
        }else{
            files.add(filePath);
        }

        return files;
    }
}
