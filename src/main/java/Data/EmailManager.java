package Data;

import com.sendgrid.*;
import java.io.IOException;

public class EmailManager {

	public static final String KEY = System.getenv("SENDGRID_API_KEY");

	public static void sendEmail(String origin, String to, String subject, String msg) {
		Email from = new Email(origin);
		Email target = new Email(to);
		Content content = new Content("text/plain", msg);
		Mail mail = new Mail(from, subject, target, content);
		SendGrid sg = new SendGrid(KEY);
		Request request = new Request();
		try {
			request.method = Method.POST;
			request.endpoint = "mail/send";
			request.body = mail.build();
			Response response = sg.api(request);
			System.out.println(response.statusCode);
			System.out.println(response.body);
			System.out.println(response.headers);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
