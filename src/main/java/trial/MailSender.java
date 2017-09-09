package trial;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailSender {
	
	@Autowired
	private JavaMailSender sender;
	
	public void send(String to, String subject, String content) throws MessagingException {
		MimeMessage msg = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(msg, true);
		helper.setSubject(subject);
		helper.setTo(to);
		helper.setText(content);
		sender.send(msg);
	}
}
