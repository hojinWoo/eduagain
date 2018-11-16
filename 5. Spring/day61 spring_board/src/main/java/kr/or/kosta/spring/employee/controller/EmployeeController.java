package kr.or.kosta.spring.employee.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.kosta.spring.employee.command.LoginCommand;
import kr.or.kosta.spring.employee.domain.Employee;
import lombok.extern.log4j.Log4j;

/**
 * 사원 관련 요청 처리 세부 컨트롤러 (web 1.0 사용 시절)
 * @author hojin
 */

@Controller
@RequestMapping("/employee")
@Log4j
public class EmployeeController {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    // 사원 목록
    @RequestMapping("list")
    public String list(Model model) {
         String title = "KOSTA 사원 목록";                  //bizService.getTitle();
         List<Employee> list = new ArrayList<Employee>();   //bizService.list();
         list.add(new Employee(100, "기정", "김", "bangy313@naver.com"));
         list.add(new Employee(101, "기정", "이", "aangy313@naver.com"));
         list.add(new Employee(102, "기정", "박", "cangy313@naver.com"));

         model.addAttribute("title", title);	//key를 지정안하면 string이 key가 되기 떄문에 지정해 주는 것이 좋다.
         model.addAttribute("list", list);
         return "/employee/list";
    }
    
//  Get방식으로 온 경우 실행
    @GetMapping("regist")
    public void regist() {
    	log.info("사원 화면 요청");
    }

//  Post방식으로 온 경우 실행 (인자를 다 받기 때문에 잘 사용X)
/*    
    @PostMapping("regist")
    public String regist( int employeeId, String firstName, String lastName, String email, Model model) {
    	log.info("사원 등록 요청");
    	log.info("사원번호 : "+ employeeId);
    	log.info("사원번호 : "+ firstName);
    	model.addAttribute("employeeId", employeeId);
    	model.addAttribute("firstName", firstName);
    	return "/employee/result";
    }*/
    
 // 사원 등록 처리 - command 객체 활용
    @PostMapping("regist")
    public String regist(Employee employee) {
        log.info(employee.getFirstName());
        // employee 이름으로 model에 자동 저장
        return "/employee/result";
    }
    
    
    // 사원 상세정보
    @RequestMapping("view")
    public String view(@RequestParam String id) {
         log.info("사원번호 : " + id);
         return "/employee/view";
    }
    
    // 사원 정보 수정
    @RequestMapping(value = "update", params = "type=admin")
    public void update() {
         log.info("요청 파라메터 값에 따른 처리");
         // 뷰이름 반환하지 않을 경우 요청이름을 뷰이름으로 사용
    }
    
    // 서블릿 API 활용
    @RequestMapping("servlet")
    public void servlet(HttpServletRequest request, HttpServletResponse response) throws IOException{
         log.info(request.getRemoteAddr());
         response.setContentType("text/html; charset=utf-8"); 
         PrintWriter out = response.getWriter();
         out.println("<html>");
         out.println("<body>");
         out.println("<h2>Test Message</h2>");
         out.println("</body>");
         out.println("</html>");
    }
    
    // 브라우저 헤더정보
    @RequestMapping("header")
    public void header(@RequestHeader("user-agent") String userAgent) {
         log.info("브라우저 정보: " + userAgent);
    }
    
    /********** 쿠키 사용하기 ***********/
	 // 로그인 화면 (아이디 저장 있는 경우, 체크 안되어 있어도 될 수 있게 required=false)
     //										만약 reqired=true이고 클릭이 안 되어 있으면 404 error
	 @GetMapping("login")
	 public String form(@CookieValue(value="rememberId", required=false) Cookie rememberCookie, LoginCommand loginCommand) {
	      log.info("로그인 화면 요청...");
	      if(rememberCookie != null) {
	           loginCommand.setUserId(rememberCookie.getValue());
	           loginCommand.setRememberId(true);
	      }
	      return "employee/loginForm";
	 }
	 
	// 로그인 처리
	 @PostMapping("login")
	 public String loginPost(LoginCommand loginCommand, HttpServletResponse response) {
	      log.info("로그인 처리 요청...");

	      //boolean isMember = employeeService.authenticate(loginCommand.getUserId(), loginCommand.getPassword());
	      // 회원이라 가정
	      Cookie loginIdCookie = new Cookie("loginId", loginCommand.getUserId());
	      loginIdCookie.setPath("/");
	      response.addCookie(loginIdCookie);

	      Cookie rememberCookie = new Cookie("rememberId", loginCommand.getUserId());
	      rememberCookie.setPath("/");
	      if(loginCommand.isRememberId()) {
	           rememberCookie.setMaxAge(60*60*24*30);
	      }else {
	           rememberCookie.setMaxAge(0);
	      }
	      response.addCookie(rememberCookie);
	      
	      return "redirect:/";
	 }
	 
	// 쿠키 정보 읽기
	 @RequestMapping(value = "/readcookie")
	 public String readCooke(@CookieValue(value="loginId", required=false) Cookie loginCookie, LoginCommand loginCommand) {
	      if(loginCookie == null) {
	           return "login";
	      }else {
	           loginCommand.setUserId(loginCookie.getValue());
	      }
	      return "/employee/readcookie";
	 }

	 /********** RESTful 사용하기 ***********/
	 /** RESTful service - JSON 직접 출력(비권장) */
	 @GetMapping(value="/{employee_id}")
	 public void detail(@PathVariable("employee_id") int employeeId, HttpServletResponse response) throws IOException {
	      log.info("사원정보 요청 : " + employeeId);
	       // JSON 직접 출력
	      response.setContentType("text/plain; charset=utf-8"); 
	      PrintWriter out = response.getWriter();
	      String employeeJson = "{\"employeeId\": \""+employeeId+"\", \"firstName\" : \"기정\", \"lastName\" : \"김\"}";
	      out.println(employeeJson);
	 }

	 /** RESTful service - @ResponseBody 활용(Json Text 반환) */
	 @GetMapping(value="/{employeeId}/1",produces = "text/plain; charset=utf8")
	 public @ResponseBody String detail(@PathVariable int employeeId){
	      log.info("사원정보 요청 : " + employeeId);
	      String employeeJson = "{\"employeeId\": \""+employeeId+"\", \"firstName\" : \"기정\", \"lastName\" : \"김\"}";
	      return employeeJson;
	 }



}