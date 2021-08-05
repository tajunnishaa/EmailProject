package emailproj;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	void emailrecipiens()throws IOException, AddressException, MessagingException{
		EmailSender mail1=new EmailSender();
		
		FileInputStream fis= new FileInputStream("C:\\HITJAVA\\basic\\src\\emailproj\\mailid.properties");
		//FileInputStream fis= new FileInputStream("mailid.properties");
		Properties prop = new Properties();
		prop.load(fis);
		
		Map<String,String>hm=(Map)prop;
		for(Map.Entry<String,String>me:hm.entrySet()) {
			String mail=(String)me.getKey();
			String[] name = mail.split("@");
			String name1 = name[0];
			if(mail.contains("@")) {
			System.out.println("Sending mail to "+name1);
			String mailid1= me.getKey();
			String gitlink = me.getValue();
			mail1.setupServerProperties();
			mail1.draftEmail(name1,gitlink,mailid1);
			mail1.sendEmail();
			try {Thread.sleep(1000);}
			catch(InterruptedException e) {e.printStackTrace();}
			}
			else {System.out.println("valid Email id Found:"+mail);
			}
		}
	}
Session newSession=null;
MimeMessage mimemessage=null;
void sendEmail() throws MessagingException {
	String fromUser = "hitaspire00@gmail.com";
	String fromUserPassword ="Nisha-naga001";
	String emailHost ="smtp.gmail.com";
	Transport transport = newSession.getTransport("smtp");
	transport.connect(emailHost,fromUser,fromUserPassword);
	transport.sendMessage(mimemessage,mimemessage.getAllRecipients());
	transport.close();
	System.out.println("\t Email Successfully Sent");
}
MimeMessage draftEmail(String name, String gitlink, String mailid) throws AddressException,MessagingException {
	String emailSubject = "SPAR INDIA";
	String Signature = "<b style=\"color:red;\">Thanks & Regrads</b><br>Spar Team<br>";
			
//	String emailBody="Hi "+name+"<br><br>To know more of your account:"+"<a.href="+gitlink+"/>"+gitlink+"</a><br><br>"+Signature;
	String emailBody="Dear "+name+", <br><br>Thanks for transacting at SPAR store. \r\n"
			+ "Please take this quick survey to help us serve you better.\r\n"
			+ " https://od.vg/btz0PM5 - SPAR India</a><br><br>"+Signature;
			
	mimemessage = new MimeMessage(newSession);
	
	mimemessage.addRecipient(Message.RecipientType.TO,new InternetAddress(mailid));
	mimemessage.setSubject(emailSubject);
	MimeBodyPart bodypart = new MimeBodyPart();
	bodypart.setContent(emailBody,"text/html");
	
	MimeMultipart multipart = new MimeMultipart();
	multipart.addBodyPart(bodypart);
	mimemessage.setContent(multipart);
	return mimemessage;
	
}
void setupServerProperties() {
	Properties properties = new Properties();
	properties.put("mail.smtp.port","587");
	properties.put("mail.smpt.auth","true");
	properties.put("mail.smtp.starttls.enable","true");
	properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
	newSession=Session.getDefaultInstance(properties,null);
}
}