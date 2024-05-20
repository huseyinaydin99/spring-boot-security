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

public class _007_LoadCertificate {

	public static void main(String[] args) {		
		try{
		    KeyStore keyStore = KeyStore.getInstance("PKCS12");
		    keyStore.load(new FileInputStream("src/main/resources/output.p12"), "123456789".toCharArray());
		     
		    Certificate cert =  keyStore.getCertificate("output");
		    
		    System.out.println(cert);
		} catch (Exception ex){
		    ex.printStackTrace();
		}
	}
}