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

public class _006_LoadCertificateChain {

	public static void main(String[] args) {
		try {
			KeyStore keyStore = KeyStore.getInstance("PKCS12");
			keyStore.load(new FileInputStream("src/main/resources/output.p12"), "123456789".toCharArray());

			Key pvtKey = keyStore.getKey("output", "123456789".toCharArray());
			System.out.println(pvtKey.toString());

			Certificate[] chain = keyStore.getCertificateChain("output");
			for (Certificate cert : chain) {
				System.out.println(cert.toString());
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}