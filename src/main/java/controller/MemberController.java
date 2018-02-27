package controller;

import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale.Category;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import api.BoxOffice;
import api.MovieNewsApi;
import dto.CategoryDTO;
import dto.CatgDTO;
import dto.MemDTO;
import dto.MovieDTO;
import dto.PageDTO;
import dto.RatingDTO;
import dto.WishListDTO;
import service.BoardService;
import service.MemService;
import service.MovieService;

@Controller
public class MemberController {
	MemService service;
	MovieService movieservice;
	BoardService boardservice;
	
	String loginId;
	int currentPage;
	PageDTO pdto;
	public MemberController() {

	}

	public void setMovieservice(MovieService movieservice) {
		this.movieservice = movieservice;
	}

	public void setService(MemService service) {
		this.service = service;
	}
	
	public void setBoardservice(BoardService boardservice) {
		this.boardservice = boardservice;
	}

	@RequestMapping(value = "/checkId", method = RequestMethod.POST)
	public @ResponseBody boolean checkId(MemDTO userDTO) {
		// 아이디 중복 확인 메서드
		return service.idCheckProcess(userDTO);
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public String viewSignUp() {
		// 회원가입 페이지로 이동
		return "signUpForm";
	}

	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	public ModelAndView signUp(MemDTO userDTO) {
		// 회원 가입 실행
		ModelAndView mav = new ModelAndView();
		try {
			// 회원가입 시도
			service.registerProcess(userDTO);
			mav.addObject("resultSignUp", true);
		} catch (Exception e) {
			// 회원가입 실패시
			e.printStackTrace();
			mav.addObject("resultSignUp", false);
		}
		/*
		 * System.out.println("num =" + userDTO.getMem_num() + " id= " +
		 * userDTO.getMem_id() + " pw=" + userDTO.getMem_pw() + " name=" +
		 * userDTO.getMem_name() + " email=" + userDTO.getMem_email() + " address=" +
		 * userDTO.getMem_address() + " sex=" + userDTO.getMem_sex());
		 */

		mav.setViewName("signUpForm");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(MemDTO userDTO, HttpSession session) {
		ModelAndView mav = new ModelAndView();
		MovieNewsApi api = new MovieNewsApi();
		if (service.idCheckProcess(userDTO)) {
			try {
				MemDTO foundUserDTO = service.loginProcess(userDTO);
				if (foundUserDTO != null) {
					loginId = foundUserDTO.getMem_id();
					mav.addObject("loginStatus", "로그인에 성공하였습니다.");

					session.setAttribute("userDTO", foundUserDTO);

					HashMap<String, Integer> param = new HashMap<String, Integer>();
					param.put("startRow", 1);
					param.put("endRow", 5);
					mav.addObject("boardList", boardservice.listProcess(param));
				} else {
					mav.addObject("loginStatus", "비밀번호가 일치하지 않습니다.");
				}
			} catch (Exception e) {
				e.printStackTrace();
				mav.addObject("loginStatus", "로그인에 실패하였습니다.");
			}
		} else {
			mav.addObject("loginStatus", "회원이 존재하지 않습니다.");
		}

		api.MovieNewsAPI(mav);
		BoxOffice api2 = new BoxOffice();
		List<String> list = api2.boxOffice();
		List<MovieDTO> movieList = new ArrayList<MovieDTO>();
		List<MovieDTO> boxOfficeMovieList = new ArrayList<MovieDTO>();

		for (String i : list) {
			System.out.println(i);
			MovieDTO dto = movieservice.BoxOfficeInsert(i);
			if (dto != null)
				movieList.add(dto);
			else
				System.out.println("영화 없음");
		}

		for (MovieDTO i : movieList) {
			if (i != null) {
				movieservice.BoxOfficeActorInsert(i);
				movieservice.BoxOfficeDirectorInsert(i);
				movieservice.BoxOfficeCategoryInsert(i);
			}
		}

		for (String i : list) {
			try {
				MovieDTO dto = movieservice.boxOffice(i);
				if (dto.getMovie_kor_title() != null)
					boxOfficeMovieList.add(dto);
			} catch (NullPointerException e) {

			}
		}

		mav.addObject("movie",boxOfficeMovieList);
		mav.addObject("commentMovie", movieservice.maxCommentMovie());
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		loginId = "";
		return "redirect:/main";
	}

	@RequestMapping("/findId")
	public ModelAndView findIdPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("findIdForm");
		return mav;
	}

	@RequestMapping("/findPw")
	public ModelAndView findPwPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("findPwForm");
		return mav;
	}

	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public ModelAndView findId(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		MemDTO user = service.findIdProcess(userDTO);
		mav.addObject("user", user);
		if (user == null) {
			mav.addObject("findIdStatus", "일치하는 회원이 없습니다.");
		}
		if (user != null) {
			try {
				mav.addObject("findIdStatus", "회원님의 아이디는 '" + user.getMem_id() + "' 입니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		mav.setViewName("findIdForm");
		return mav;
	}

	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	public ModelAndView findPw(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		MemDTO user = service.findPwProcess(userDTO);
		mav.addObject("user", user);
		if (user != null) {
			try {
				mav.addObject("findPwStatus", "회원님의 비밀번호는 '" + user.getMem_pw() + "' 입니다.");
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			mav.addObject("findPwStatus", "일치하는 회원이 없습니다.");
		}
		mav.addObject("user", user);
		mav.setViewName("findPwForm");
		return mav;
	}

	@RequestMapping("/myPage")
	public ModelAndView myPage() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("myPage");
		return mav;
	}

	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	public ModelAndView update(MemDTO userDTO, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		service.updateProcess(userDTO);
		if (userDTO != null) {
			mav.addObject("updateInfoStatus", "회원정보를 수정하였습니다.");
			session.setAttribute("userDTO", userDTO);
		} else {
			mav.addObject("updateInfoStatus", "회원정보 수정에 실패하였습니다.");
		}
		mav.setViewName("myPage");
		return mav;
	}

	@RequestMapping("/memInfo")
	public ModelAndView memInfo(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		try {
			if (loginId.equals("admin")) {
				List<MemDTO> aList = service.memInfo(userDTO);
				mav.addObject("memList", aList);
				mav.setViewName("memInfoList");
				return mav;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping("/memUpdate")
	public ModelAndView memUpdate(MemDTO userDTO) {
		ModelAndView mav = new ModelAndView();
		List<MemDTO> aList = service.memInfo(userDTO);
		mav.addObject("memList", aList);
		mav.setViewName("memUpdate");
		return mav;
	}

	@RequestMapping(value = "/memUpdateInfo", method = RequestMethod.GET)
	public ModelAndView memL(MemDTO userDTO, HttpServletRequest request, int mem_num) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		List<MemDTO> aList = service.memInfo(userDTO);
		MemDTO user = service.mList(mem_num);
		session.setAttribute("memInfo", user);
		mav.addObject("memList", aList);
		mav.setViewName("memUpdate");
		return mav;
	}

	@RequestMapping(value = "/memUpdateInfo", method = RequestMethod.POST)
	public ModelAndView memUpdate(MemDTO memList, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		HttpSession session = request.getSession();
		service.memUpdate(memList);
		if (memList != null) {
			mav.addObject("memUpdateStatus", "등급정보를 수정하였습니다.");
			session.setAttribute("list", memList);
		} else {
			mav.addObject("memUpdateStatus", "등급정보 수정에 실패하였습니다.");
		}

		mav.setViewName("memUpdate");

		return mav;

	}

	@RequestMapping(value = "updateprofile", method = RequestMethod.POST)
	public @ResponseBody void updateProfile(MemDTO dto, HttpServletRequest request) {
		HttpSession session = request.getSession();
		MultipartFile file = dto.getMem_profile();
		String path = request.getSession().getServletContext().getRealPath("/")+"profile\\";
		String fileName = file.getOriginalFilename();
		
		String saveDirectory = path;
		System.out.println("세이브디렉토리:" + saveDirectory);

		File fe = new File(saveDirectory);
		if (!fe.exists())
			fe.mkdir();

		File ff = new File(saveDirectory, fileName);

		try {
			FileCopyUtils.copy(file.getInputStream(), new FileOutputStream(ff));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		MemDTO DTO = (MemDTO) session.getAttribute("userDTO");
		System.out.println("기존 파일명:"+DTO.getUpload());
		File delete = new File(saveDirectory,DTO.getUpload());
		delete.delete();
		
		System.out.println("저장경로:"+saveDirectory);
		dto = (MemDTO) session.getAttribute("userDTO");
		dto.setUpload(fileName);
		
		System.out.println("아이디:"+dto.getMem_id());
		System.out.println("번호:"+dto.getMem_num());
		System.out.println("프로필:"+dto.getUpload());
		service.profileUpdate(dto);
		service.commentProfileUpdate(dto);
		session.setAttribute("userDTO", dto);
	}
	
	
	@RequestMapping("/profile")
	public ModelAndView profile(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemDTO dto = (MemDTO) session.getAttribute("userDTO");
		System.out.println("유저번호:"+dto.getMem_num());
		ModelAndView mav = new ModelAndView();
		mav.addObject("rating",service.profile_rating(dto));
		mav.setViewName("profile");
		return mav;
	}
	
	@RequestMapping("/myboard")
	public ModelAndView myboard(HttpServletRequest request, PageDTO pv) {
		HttpSession session = request.getSession();
		MemDTO dto = (MemDTO) session.getAttribute("userDTO");
		
		int totalRecord = boardservice.myboardCountProcess(dto);
		if(totalRecord >= 1) {
			if(pv.getCurrentPage()==0) {
				currentPage=1;
			}else {
				currentPage = pv.getCurrentPage();
			}
		}
		pdto = new PageDTO(currentPage,totalRecord);
		HashMap<String,Integer> param = new HashMap<String, Integer>();
		param.put("mem_num", dto.getMem_num());
		param.put("startRow", pdto.getStartRow());
		param.put("endRow",pdto.getEndRow());
		ModelAndView mav = new ModelAndView();
		mav.addObject("aList",boardservice.myboardProcess(param));
		mav.addObject("comment",service.mycomment(dto));
		mav.addObject("pv",pdto);
		mav.setViewName("myboard_list");
		return mav;
	}
	
	
	@RequestMapping("/mylist")
	public ModelAndView mylist(HttpServletRequest request, String category) {
		HttpSession session = request.getSession();
		MemDTO dto = (MemDTO) session.getAttribute("userDTO");
		System.out.println("유저이름:"+dto.getMem_name());
		WishListDTO wish = new WishListDTO();
		wish.setMem_num(dto.getMem_num());
		ModelAndView mav = new ModelAndView();
		System.out.println("카테:"+category);
		
		if(category==null) {
			mav.addObject("list",service.mylist(wish));
			mav.setViewName("mylist");
		} else if(category!=null) {
			wish.setCategory_name(category);
			mav.addObject("list",service.wishlist(wish));
			mav.setViewName("mylist");
		} 		
		mav.addObject("mem_name",dto.getMem_name());
		return mav;
	}
	/*
	@RequestMapping("/wishlist")
	public ModelAndView wishlist(String category) {
		System.out.println("카테고리는:"+category);
		ModelAndView mav = new ModelAndView();
		return mav;
	}*/
	
	@RequestMapping("/pluswish")
	public @ResponseBody int pluswish(int movie_num,HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		CatgDTO ca = new CatgDTO();
		ca.setCategory_name(service.findcategory(movie_num));
		String sum="";
		List<CatgDTO> list = ca.getCategory_name();
		
		for(int i=0; i<list.size(); i++) {
			sum+=list.get(i);
		}
		HttpSession session = request.getSession();
		MemDTO dto = (MemDTO) session.getAttribute("userDTO");
		WishListDTO wish = new WishListDTO();
		wish.setCategory_name(sum);
		wish.setMovie_num(movie_num);
		wish.setMem_num(dto.getMem_num());
		
		int overlap = service.findoverlap(wish);
		
		System.out.println("overlap:"+overlap);
		if(overlap==0) {
		service.insertwishlist(wish);
		} else if(overlap==1) {
			
		}
		return overlap;
	}
}
