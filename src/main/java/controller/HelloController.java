package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

<<<<<<< HEAD
// http://192.168.10.240:8090/finalproject/main
=======
// http://localhost:8090/finalproject/main

>>>>>>> b6b726e261e53331cd617b72f27c2babb7723141
@Controller
public class HelloController {
	
	@RequestMapping("/main")
	public String mainPage() {
		return "main";
	}
	
	
}
