package com.dgg.hdforeman.app.error;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.dgg.hdforeman.app.utils.CommonUtil;


/**
 * Created by Rex on 2016/11/28.
 */

public class EmailService extends Service {

    String TAG = "EmailService";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        CommonUtil.logDebug("onCreate()");
        super.onCreate();
    }


    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        CommonUtil.logDebug("onStartCommand()");
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String address = intent.getStringExtra("fileaddress");
                    sendMail("dgg_tech_center@126.com", "dgg_tech_center@126.com", "smtp.126.com",
                            "dgg_tech_center@126.com", "admin123", "小顶家装工人端错误报告", "详情看附件",
                            "/sdcard/HDForeman/Crash/" + address);
                    Log.d("LOG","执行了");
                } catch (AddressException e) {
                    e.printStackTrace();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * @param str_to_mail   发邮件的目的地（收件人信箱）
     * @param str_from_mail 发邮件的出发地（发件人的信箱）
     * @param str_smtp      电子信箱服务器
     * @param str_user      用户名
     * @param str_pass      密码
     * @param str_title     邮件主题
     * @param str_body      邮件文本内容
     * @param str_file_path 附件路径
     * @throws AddressException
     * @throws MessagingException
     */
    public void sendMail(String str_to_mail, String str_from_mail,
                         String str_smtp, String str_user, String str_pass,
                         String str_title, String str_body, String str_file_path)
            throws AddressException, MessagingException {


        Properties props = System.getProperties();// Get system properties

        props.put("mail.smtp.host", str_smtp);// Setup mail server

        props.put("mail.smtp.auth", "true"); // 这样才能通过验证

//		props.put("mail.smtp.port", "25");//126邮箱端口

        MyAuthenticator myauth = new MyAuthenticator(str_user, str_pass);


        Session session = Session.getDefaultInstance(props, myauth);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(str_from_mail));

        message.addRecipient(Message.RecipientType.TO, new InternetAddress(str_to_mail));

        message.setSubject(str_title);// 设置主题

        message.setText(str_body);// 设置邮件内容

        MimeBodyPart attachPart = new MimeBodyPart();

        FileDataSource fds = new FileDataSource(str_file_path); // 打开要发送的文件

        attachPart.setDataHandler(new DataHandler(fds));

        attachPart.setFileName(fds.getName());

        MimeMultipart allMultipart = new MimeMultipart("mixed"); // 附件

        allMultipart.addBodyPart(attachPart);// 添加

        message.setContent(allMultipart);

        message.saveChanges();

        Transport.send(message);// 开始发送

    }

    class MyAuthenticator extends javax.mail.Authenticator {
        private String strUser;
        private String strPwd;

        public MyAuthenticator(String user, String password) {
            this.strUser = user;
            this.strPwd = password;
        }

        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(strUser, strPwd);
        }
    }

}
