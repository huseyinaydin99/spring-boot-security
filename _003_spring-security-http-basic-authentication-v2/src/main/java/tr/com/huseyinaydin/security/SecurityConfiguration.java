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
        	//.withUser("admin").password("123").roles("ADMIN")
        	.withUser("admin").password(passwordEncoder().encode("toor")).roles("ADMIN")
        	.and()
        	.withUser("huseyin").password(passwordEncoder().encode("toor")).roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
             .authorizeRequests()
             .anyRequest().authenticated()
             .and()
             .httpBasic();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}