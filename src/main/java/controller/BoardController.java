package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {

	@RequestMapping("/free")
	public String board() {
		return "freeboard";
	}
	@RequestMapping("/boardWrite")
	public String boardWrite() {
		return "board_write";
	}

	@RequestMapping("/boardDetail")
	public String boardDetail() {
		return "board_detail";
	}

}
