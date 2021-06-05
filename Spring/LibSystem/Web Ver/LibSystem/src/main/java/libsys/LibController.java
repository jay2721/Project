package libsys;

import java.util.*;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LibController {

	@Autowired
	private RegisterService registerService;

	@Autowired
	private LoginService loginService;

	@Autowired
	private RentService rentService;
	
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private UserUpdateService userupdateService;
	
	@Autowired
	private SearchService searchService;
	
	Member remember;

	//로그인
	@RequestMapping({"/","/login"})
	public String login() {
		return "login";
	}


	//로그인 확인
	@PostMapping("/logindone")
	public String logindone(Model model, LoginRequest log){
		if(loginService.chkId(log)==true){
			Member member=loginService.login(log);  
			if(log.getId().equals(member.getId())&&log.getPwd().equals(member.getPwd())){
				remember=member;
				model.addAttribute("check", "ok"); 
			}
			else {
				model.addAttribute("check","no");
			}
		}
		else {
			model.addAttribute("check", "no");
		}
		return "logindone";
	}

	
	//회원가입
	@RequestMapping("/register")
	public String register(Model model) {  
		model.addAttribute("registerRequest", new RegisterRequest());
		return "register";  
	}


	//회원가입 확인
	@PostMapping("/registerdone")
	public String registerdone(RegisterRequest regReq, Model model){
		if(registerService.chkId(regReq)==true){
			registerService.regist(regReq);        
			model.addAttribute("check", "ok");
		}
		else {
			model.addAttribute("check", "no");
		}
		return "registerdone";
	}

	
	//메인 화면
	@RequestMapping(value="/main")
	public String main(Model model, HttpSession session) { 

		session.setAttribute("member", remember);
		List<Book> bookList = bookDao.selectAll();
		model.addAttribute("books", bookList);
		return "main";
	}

	
	//검색
	@PostMapping("/search")
	public String booksearch(Model model,SearchRequest searchreq, HttpSession session) { 

		session.setAttribute("member", remember);
		
		if(!searchreq.getSearchcontent().equals("")) {
			List<Book> bookList=searchService.search(searchreq);
			if(bookList.size()==0) {
				model.addAttribute("books", bookList);
				model.addAttribute("check", "no");
			}
			else if(bookList.size()!=0) {
				model.addAttribute("books", bookList);
				model.addAttribute("check", "ok");
			}
		}
		else if(searchreq.getSearchcontent().equals("")) {
			model.addAttribute("check","empty");
		}
		return "search";
		
	}
	

	
	//대여관리페이지
	@PostMapping("/mybooklist")
	public String mylist(Model model, HttpSession session) { 

		session.setAttribute("member", remember);
		List<Book> bookList = bookDao.selectByRent(remember.getId());
		
		if(bookList.size()==0) {
			model.addAttribute("books", bookList);
			model.addAttribute("check", "no");
		}
		else if(bookList.size()!=0) {
			model.addAttribute("books", bookList);
			model.addAttribute("check", "ok");
		}
		
//		model.addAttribute("books", bookList);
		return "mybooklist";
	}
	
	
	//대여 확인
	@PostMapping("/rentdone")
	public String rental(Model model, RentRequest rentreq, HttpSession session){
		session.setAttribute("member", remember);
		rentService.rent(rentreq);		
	    model.addAttribute("check", "ok");

		return "rentdone";
	}
	
	
	//반납 확인
	@PostMapping("/rtndone")
	public String rtn(Model model, RentRequest rentreq, HttpSession session){
		session.setAttribute("member", remember);
		rentService.rtnbook(rentreq);		
	    model.addAttribute("check", "ok");

		return "rtndone";
	}

	
	//로그아웃 확인
	@PostMapping("/logout")
	public String logout(HttpSession session){
		remember=null; 
		session.invalidate();
		return "logout";
	}

	
	//회원정보 수정
	@PostMapping("/userupdate")
	public String userupdate(Model model){
		model.addAttribute("userUpdateRequest", new UserUpdateRequest());
		return "userupdate";
	}


	//회원정보 수정 확인
    @PostMapping("/userupdatedone")
    public String userUpdatedone(UserUpdateRequest upreq, Model model){
        Member member= userupdateService.userup(upreq);

        remember=member;
        model.addAttribute("check", "ok");
        return "userupdatedone";
    }
    
    
}

