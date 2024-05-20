package tr.com.huseyinaydin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserPrincipalDetailsService userPrincipalDetailsService;

	public SecurityConfiguration(UserPrincipalDetailsService userPrincipalDetailsService) {
		this.userPrincipalDetailsService = userPrincipalDetailsService;
	}

	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.userPrincipalDetailsService);
		return daoAuthenticationProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
		//.inMemoryAuthentication()
		//        	
		//.withUser("admin").password(passwordEncoder().encode("admin"))
		//.roles("ADMIN")
		//.authorities("ACCESS_TEST1", "ACCESS_TEST2", "ROLE_ADMIN") 
		//        	
		//.and()
		//.withUser("huseyin").password(passwordEncoder().encode("huseyin"))
		//.roles("USER") 
		//        	
		//.and()
		//.withUser("manager").password(passwordEncoder().encode("manager"))
		////.roles("MANAGER")
		//.authorities("ACCESS_TEST2","ROLE_MANAGER");    
	}//

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
        .antMatchers("/h2-console/**").permitAll()
		.antMatchers("/h2-console/login.do").permitAll()
		.antMatchers("/index.html").permitAll()
		.antMatchers("/profile/**").authenticated()
		.antMatchers("/admin/**").hasRole("ADMIN")
		.antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
		.antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
		.antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
		.antMatchers("/api/public/users").hasRole("ADMIN")
		.and()
		.headers().frameOptions().disable()
        .and()
        .csrf().ignoringAntMatchers("/h2-console/**")
        .and()
        .cors().disable()
		.httpBasic();
		
		http.csrf().ignoringAntMatchers("/h2-console/**");
        //this will allow frames with same origin which is much more safe
        http.headers().frameOptions().sameOrigin();
	}
	

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}