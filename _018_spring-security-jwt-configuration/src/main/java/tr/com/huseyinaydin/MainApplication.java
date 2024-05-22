package tr.com.huseyinaydin;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	public ServletWebServerFactory servletContainer() {
		// Enable SSL Traffic
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
			@Override
			protected void postProcessContext(Context context) {
				SecurityConstraint securityConstraint = new SecurityConstraint();
				securityConstraint.setUserConstraint("CONFIDENTIAL");
				SecurityCollection collection = new SecurityCollection();
				collection.addPattern("/*");
				securityConstraint.addCollection(collection);
				context.addConstraint(securityConstraint);
			}
		};

		// Add HTTP to HTTPS redirect
		tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());
		return tomcat;
	}

	/*
	 * We need to redirect from HTTP to HTTPS. Without SSL, this application used
	 * port 8082. With SSL it will use port 8443. So, any request for 8082 needs to
	 * be redirected to HTTPS on 8443.
	 */
	/*
	 * HTTP'den HTTPS'ye yönlendirmemiz gerekiyor. Bu uygulama SSL olmadan kullanıldı
	 * bağlantı noktası 8082. SSL ile bağlantı noktası 8443'ü kullanacaktır. Bu nedenle, 8082 için herhangi bir isteğin
	 * 8443'te HTTPS'ye yönlendirilecektir.
	*/
	private Connector httpToHttpsRedirectConnector() {
		Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
		connector.setScheme("http");
		connector.setPort(8082);
		connector.setSecure(false);
		connector.setRedirectPort(8443);
		return connector;
	}
}