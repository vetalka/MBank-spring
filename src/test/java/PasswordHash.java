import junit.framework.TestCase;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordHash extends TestCase{


	public void testBCryptHash() {
		
		String password = "system";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		System.out.println(hashedPassword);
	}

//	public void testMD5Hash() {
//		
//		String password = "system";
//		Md5PasswordEncoder passwordEncoder = new Md5PasswordEncoder();
//		String hashedPassword = passwordEncoder.encodePassword(password, null);
//		System.out.println(hashedPassword);
//	}

}
