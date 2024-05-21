package tr.com.huseyinaydin.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tr.com.huseyinaydin.model.User;
import tr.com.huseyinaydin.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

@Service
public class UserService implements CommandLineRunner {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public void run(String... args) {
		// Delete all
		this.userRepository.deleteAll();

		// Create users
		User admin = new User("admin", passwordEncoder.encode("admin"), "ADMIN", "ACCESS_TEST1,ACCESS_TEST2");
		User huseyin = new User("huseyin", passwordEncoder.encode("huseyin"), "USER", "");
		User manager = new User("manager", passwordEncoder.encode("manager"), "MANAGER", "ACCESS_TEST1");

		List<User> users = Arrays.asList(admin, huseyin, manager);

		// Save to DB
		this.userRepository.saveAll(users);
	}
}
