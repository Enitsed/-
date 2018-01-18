package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// http://192.168.10.240:8090/finalproject/main
// http://localhost:8090/finalproject/main

@Controller
public class HelloController {
	
	@RequestMapping("/main")
	public String mainPage() {
		return "main";
	}
	
	
}
