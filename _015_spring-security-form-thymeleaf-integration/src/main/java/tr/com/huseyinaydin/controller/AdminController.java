package tr.com.huseyinaydin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//بسم الله الرحمن الرحيم
/**
*
* @author Huseyin_Aydin
* @since 1994
* @category Spring Boot Security
*
*/

@Controller
@RequestMapping("admin")
public class AdminController {

	@GetMapping("index")
	public String index() {
		return "admin/index";
	}
}