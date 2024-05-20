package tr.com.huseyinaydin;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

public class _002_StoreSecretKey {

	public static void main(String[] args) {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream("src/main/resources/output.p12"), "123456789".toCharArray());

			Key pvtKey = keyStore.getKey("output", "123456789".toCharArray());
			System.out.println(pvtKey.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}