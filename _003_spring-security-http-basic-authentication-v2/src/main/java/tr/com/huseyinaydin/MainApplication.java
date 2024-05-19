package tr.com.huseyinaydin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

@SpringBootApplication (exclude = {SecurityAutoConfiguration.class} )
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}
}