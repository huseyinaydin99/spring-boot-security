package tr.com.huseyinaydin;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.security.cert.Certificate;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

public class _005_LoadPrivateKey {

	public static void main(String[] args) {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream("src/main/resources/bootsecurity.p12"), "123456789".toCharArray());

			Key pvtKey = keyStore.getKey("bootsecurity", "123456789".toCharArray());
			System.out.println(pvtKey.toString());

			Certificate[] certificates = keyStore.getCertificateChain("bootsecurity");
			for (Certificate cert : certificates) {
				System.out.println(cert.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}