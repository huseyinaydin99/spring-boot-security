package tr.com.huseyinaydin.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.inMemoryAuthentication()
        	.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN").authorities("ACCESS_TEST1", "ACCESS_TEST2") // Permission Authorization
        	.and()
        	.withUser("huseyin").password(passwordEncoder().encode("123")).roles("USER") 
	        .and()
        	.withUser("manager").password(passwordEncoder().encode("manager")).roles("MANAGER").authorities("ACCESS_TEST2"); // Permission Authorization
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .authorizeRequests()
             .antMatchers("/index.html").permitAll()
             .antMatchers("/profile/**").authenticated()
             .antMatchers("/admin/**").hasRole("ADMIN")
             .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
             // http://localhost:8080/api/public/test1
             // .antMatchers("/api/public/test1").authenticated()
             // .antMatchers("/api/public/test2").authenticated()
             // .antMatchers("/api/public/**").authenticated()
             // .antMatchers("/api/public/**").hasRole("ADMIN")
             .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
             .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
             .and()
             .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}