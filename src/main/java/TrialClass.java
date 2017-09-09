import com.sendgrid.*;
import java.io.IOException;

public class TrialClass {

	public static void main(String[] args) throws IOException {
		Email from = new Email("app76528472@heroku.com");
		String subject = "Hello World from the SendGrid Java Library!";
		Email to = new Email("sastr4284@gapps.uwcsea.edu.sg");
		Content content = new Content("text/plain", "Hello, Email!");
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("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"));
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
			throw ex;
		}
	}
	
	public static void send(String to, String from, String content) {
		
	}
}