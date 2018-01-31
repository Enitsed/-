package controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dto.BoardDTO;
import dto.MemDTO;
import dto.PageDTO;
import service.BoardService;

@Controller
public class BoardController {
	BoardService service;
	private int currentPage;
	private PageDTO pdto;

	public BoardController() {

	}

	public void setService(BoardService service) {
		this.service = service;
	}

	@RequestMapping("/free")
	public ModelAndView listMethod(PageDTO pv, Integer board_category, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		if (request.getParameter("board_category") != null)
			board_category = Integer.parseInt(request.getParameter("board_category"));

		int totalRecord = service.countProcess(board_category);

		if (totalRecord >= 1) {
			if (pv.getCurrentPage() == 0) {
				currentPage = 1;
			} else {
				currentPage = pv.getCurrentPage();
			}
			pdto = new PageDTO(currentPage, totalRecord);
			HashMap<String, Integer> param = new HashMap<String, Integer>();
			param.put("startRow", pdto.getStartRow());
			param.put("endRow", pdto.getEndRow());
			param.put("board_category", board_category);
			mav.addObject("pv", pdto);
			mav.addObject("aList", service.listProcess(param));
			mav.addObject("board_category", board_category);
		}
		mav.setViewName("freeboard");
		return mav;
	} // end listMethod()

	@RequestMapping(value = "/boardWrite", method = RequestMethod.GET)
	public ModelAndView boardWritePage(HttpServletRequest request, HttpSession session, PageDTO pv, BoardDTO dto) {
		ModelAndView mav = new ModelAndView();
		session = request.getSession();
		MemDTO userDTO = (MemDTO) session.getAttribute("userDTO");
		if (dto.getBoard_relnum() != 0) {
			mav.addObject("currentPage", pv.getCurrentPage());
			mav.addObject("dto", dto);
		}
		mav.addObject("userDTO", userDTO);
		mav.setViewName("board_write");
		return mav;
	}

	@RequestMapping(value = "/boardWrite", method = RequestMethod.POST)
	public ModelAndView boardWrite(BoardDTO dto) {
		ModelAndView mav = new ModelAndView("redirect:/free");
		// 답변글이면
		if (dto.getBoard_relnum() != 0) {
			service.reStepProcess(dto);
		} else {
			// 제목글이면
			service.insertProcess(dto);
		}
		return mav;
	}

	@RequestMapping("/boardDetail")
	public ModelAndView boardDetail(int currentPage, int num) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.contentProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("board_detail");
		return mav;
	}

	@RequestMapping(value = "/boardUpdate", method = RequestMethod.GET)
	public ModelAndView updateMethod(int num, int currentPage) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("dto", service.updateSelectProcess(num));
		mav.addObject("currentPage", currentPage);
		mav.setViewName("board_update");
		return mav;
	} // end updateMethod()

	@RequestMapping(value = "/boardUpdate", method = RequestMethod.POST)
	public ModelAndView updateProc(BoardDTO dto, int currentPage, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		service.updateProcess(dto, request);
		mav.addObject("currentPage", currentPage);
		mav.setViewName("redirect:/free");
		return mav;
	} // end updateProc()

	@RequestMapping(value = "/boardDelete", method = RequestMethod.GET)
	public ModelAndView deleteMethod(int num, int currentPage, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		service.deleteProcess(num, request);
		Integer board_category = null;
		if (request.getParameter("board_category") != null) {
			board_category = Integer.parseInt(request.getParameter("board_category"));
		}
		PageDTO pv = new PageDTO(currentPage, service.countProcess(board_category));
		mav.addObject("currentPage", pv.getTotalPage());
		mav.setViewName("redirect:/free");
		return mav;
	} // end deleteMethod()

}
