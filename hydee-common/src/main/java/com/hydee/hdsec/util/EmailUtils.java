package com.hydee.hdsec.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
* @ClassName: Sendmail
* @Description: 发送Email
* @author: 孤傲苍狼
* @date: 2015-1-12 下午9:42:56
*
*/ 
public class EmailUtils {
	// 平台绑定邮箱host
	private static String emailHost;
	// 平台绑定邮箱用户名
	private static String emailUsername;
	// 平台绑定邮箱密码
	private static String emailPassword;
	// 注册验证码模板内容
	private static String emailVerifiCodeTitle;
	private static String emailVerifiCodeContent;
	// 邮箱发送props
	private static Properties prop;
	
	static{
		prop = new Properties();  
		InputStream inputStream = null;
		BufferedReader bf = null;
		try {
			inputStream = EmailUtils.class.getResourceAsStream("/config.mail.properties"); 
			bf = new BufferedReader(new InputStreamReader(inputStream)); 
			prop.load(bf);  
			
			emailUsername = prop.getProperty("mail.username");
			emailPassword = prop.getProperty("mail.password");
			emailVerifiCodeTitle = prop.getProperty("mail.mode.resetpwd.title");
			emailVerifiCodeContent = prop.getProperty("mail.mode.resetpwd.content");
		} catch (Exception e) {
			Logger.error(e);
		} finally{
			try {
				inputStream.close();
				bf.close();
			} catch (IOException ex) {
				Logger.error(ex);
			}
		}
	}
	
	public static void sendVerifiCode(String target,String code,String username) throws Exception{
		send(target, emailVerifiCodeTitle, String.format(emailVerifiCodeContent, code, username));
		Logger.info(String.format("重置密码邮件发送成功,目标%s(邮箱：%s)。验证码为%s。", username, target, code));
	}
	
    /**
     * @Method: send
     * @Description: 发送邮件
     * @Anthor:Luof
     *
     * @param session
     * @return
     * @throws Exception
     */
    public static void send(String target,String title, String msg) throws Exception {
    	Transport ts = null;
    	try {
    		// 创建session
    		Session session = Session.getInstance(prop);
    		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
    		// session.setDebug(true);
    		// 通过session得到transport对象
    		ts = session.getTransport();
    		// 使用邮箱的用户名和密码连上邮件服务器，发送邮件时，发件人需要提交邮箱的用户名和密码给smtp服务器，用户名和密码都通过验证之后才能够正常发送邮件给收件人。
    		ts.connect(emailHost, emailUsername, emailPassword);
    		// 创建邮件
    		Message message = createSimpleMail(session, target, title, msg);
    		// 发送邮件
    		ts.sendMessage(message, message.getAllRecipients());
		} catch (Exception e) {
			throw e;
		} finally{
			if(ts != null) ts.close();
		}
    }
    
    /**
    * @Method: createSimpleMail
    * @Description: 创建一封只包含文本的邮件
    * @Anthor:Luof
    *
    * @param session
    * @return
    * @throws Exception
    */ 
    public static MimeMessage createSimpleMail(Session session,String target,String title, String msg) throws Exception {
        //创建邮件对象
        MimeMessage message = new MimeMessage(session);
        //指明邮件的发件人
        message.setFrom(new InternetAddress(emailUsername));
        //指明邮件的收件人
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(target));
        //邮件的标题
        message.setSubject(title);
        //邮件的文本内容
        message.setContent(msg, "text/html;charset=UTF-8");
        //返回创建好的邮件对象
        return message;
    }
    
    /**
     * @Method: createAttachMail
     * @Description: 创建一封带附件的邮件
     * @Anthor:孤傲苍狼
     *
     * @param session
     * @return
     * @throws Exception
     */ 
     public static MimeMessage createAttachMail(Session session,String target,String title, String msg,String filePath) throws Exception{
         MimeMessage message = new MimeMessage(session);
         //设置邮件的基本信息
         //发件人
         message.setFrom(new InternetAddress(emailUsername));
         //收件人
         message.setRecipient(Message.RecipientType.TO, new InternetAddress(target));
         //邮件标题
         message.setSubject(title);
         
         //创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
         MimeBodyPart text = new MimeBodyPart();
         text.setContent(msg, "text/html;charset=UTF-8");
         
         //创建邮件附件
         MimeBodyPart attach = new MimeBodyPart();
         DataHandler dh = new DataHandler(new FileDataSource(filePath));
         attach.setDataHandler(dh);
         attach.setFileName(dh.getName());  //
         
         //创建容器描述数据关系
         MimeMultipart mp = new MimeMultipart();
         mp.addBodyPart(text);
         mp.addBodyPart(attach);
         mp.setSubType("mixed");
         
         message.setContent(mp);
         message.saveChanges();
         //将创建的Email写入到E盘存储
         // message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
         //返回生成的邮件
         return message;
     }
    
    public static void main(String[] args) {
		try {
			sendVerifiCode("liu13787301517@sina.com", "9490", "admin");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}