package Data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Defines the security interactions when a user enters a password
 * @author christopherdimitrisastropranoto
 *
 */
public class Security {
	public static final String SALT = System.getenv("SALT");
	
	/**
	 * Compares an inputted password and its hashed password.  Returns true
	 * if the passwords match and false otherwise or if password is null
	 * @param password
	 * @param hashedPassword
	 * @return
	 */
	public static boolean compare(String password, String hashedPassword) {
		String hashed = generateHash(password);
		return password != null && hashed.equals(hashedPassword);
	}
	
	/**
	 * Takes in a password and hashes it.  Returns the hashed version of the password
	 * @param password
	 * @return
	 */
	public static String generateHash(String password) {
		password += SALT;
		StringBuffer hash = new StringBuffer();
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(password.getBytes());
			byte[] hashedBytes = md.digest();
			for(byte byt : hashedBytes) {
				hash.append(Integer.toHexString(byt & 0xff).toString());
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hash.toString();
	}
}
