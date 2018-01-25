package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dto.MemDTO;

@Controller
public class BoardController {

	@RequestMapping("/free")
	public String board() {
		return "freeboard";
	}

	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public ModelAndView boardWritePage(HttpServletRequest request, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		MemDTO userDTO = (MemDTO) session.getAttribute("userDTO");
		mav.addObject("userDTO", userDTO);
		return mav;
	}

	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public String boardWrite() {
		return "board_write";
	}

	@RequestMapping("/boardDetail")
	public String boardDetail() {
		return "board_detail";
	}

}
