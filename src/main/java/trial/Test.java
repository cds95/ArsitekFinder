package trial;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.*;

@SuppressWarnings("deprecation")
public class Test {
	public static void main(String[] args) {
		
		ApplicationContext ctx = new ClassPathXmlApplicationContext("application.xml");
		MailMail m = (MailMail) ctx.getBean("mailMail");
		String sender = "terminator2.dim@gmail.com";// write here sender gmail
													// id
		String receiver = "sastr4284@gapps.uwcsea.edu.sg";// write here receiver
															// id
		m.sendMail(sender, receiver, "hi", "welcome");

		System.out.println("success");
	}
}