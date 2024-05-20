package tr.com.huseyinaydin;

import java.io.FileOutputStream;
import java.security.KeyStore;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

public class _001_CreatePKCS12Keystore {

	public static void main(String[] args) {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(null, null);

			keyStore.store(new FileOutputStream("src/main/resources/output.p12"), "123456789".toCharArray());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}