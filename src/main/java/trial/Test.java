package trial;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test {
	public static void main(String[] args) throws FileNotFoundException {
		
		File config = new File("azureConfig");
		Scanner scanner = new Scanner(config);
		String name = scanner.next();
		String key = scanner.next();
		System.out.println(name);
		System.out.println(key);
		scanner.close();
	}
}