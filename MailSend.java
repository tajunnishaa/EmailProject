package emailproj;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;



public class MailSend {
	public static void main(String[] args) throws AddressException, MessagingException {
		EmailSender ar=new EmailSender();
		
		try {
			ar.emailrecipiens();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
	}
}