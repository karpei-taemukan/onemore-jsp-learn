package controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.tools.DocumentationTool.Location;

import dto.Member;
import oracle.net.aso.l;
import service.MemberService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet({"/memberJoinForm", 
	"/memberLoginForm", "/main", "/memberJoin", "/memberIdCheck", "/memberLogin"	
	,"/memberLogout","/myInfo"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = request.getServletPath();
		String path = request.getContextPath();
		
		
		MemberService msvc = new MemberService();
		
		HttpSession session =  request.getSession();// request에 session 정보가 있음
		
		
		switch (url) {
		case "/memberJoinForm":
			System.out.println("join page");
			response.sendRedirect(path+"/JoinForm.jsp");
			break;

		case "/memberLoginForm":
			System.out.println("login page");
			response.sendRedirect(path+"/LoginForm.jsp");
			break;
			
		case "/main":
			System.out.println("main page");
			response.sendRedirect(path+"/Main.jsp");
			break;
		case "/memberJoin":
			System.out.println("join request");
			
			//1. 파라메터 확인
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			String joinId = request.getParameter("joinId");
			String joinPw = request.getParameter("joinPw");
			String joinName = request.getParameter("joinName");
			String joinBirth = request.getParameter("joinBirth");
			
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
			String detailAddres= request.getParameter("detailAddress");
			String extraAddress = request.getParameter("extraAddress");
			
			String maddr = postcode+"_"+address+"_"+detailAddres+"_"+extraAddress;
			
			
		/*	System.out.println(joinId);
			System.out.println(joinPw);
			System.out.println(joinBirth);
			System.out.println(joinAddress);
			System.out.println(detailAddres);
			System.out.println(extraAddress);
		*/
			
			Member joinMember = new Member();
			joinMember.setMid(joinId);
			joinMember.setMpw(joinPw);
			joinMember.setMname(joinName);
			joinMember.setMbirth(joinBirth);
			joinMember.setMaddr(maddr);
			
			
			// 2. 회원가입 처리 서비스 호출
			int joinResult = msvc.memberJoin(joinMember);
			
			if(joinResult > 0) {
				System.out.println("회원가입 성공");
				
				String joinMsg = "회원가입성공";
				String joinUrl = path+"/main";
				
				response.sendRedirect(path+"/Success.jsp"+"?"
				+"msg"+"="+URLEncoder.encode(joinMsg,"UTF-8")+
				"&"+"url"+"="+URLEncoder.encode(joinUrl,"UTF-8"));
				
			}
			else{
				System.out.println("회원가입 실패");
			
				response.sendRedirect(path+"/Fail.jsp"+"?msg"+"="+URLEncoder.encode("회원가입실패","UTF-8"));
				
			}
			System.out.println(joinMember.toString());		
			
			
			
			// 3. 회원가입 결과 확인
			
			
			// 4. 회원 가입 성공 => 메인 페이지
			// 	  회원 가입 실패 => 회원 가입 페이지 
			
			
			break;
			
			
		case "/memberIdCheck":
			System.out.println("아이디 중복 확인 요청");
			String checkId = request.getParameter("inputId");
			System.out.println("확인할 아이디: "+checkId);
			
			// 아이디 중복확인 기능 호출
			
			String result = msvc.memberIdCheck(checkId); 
			
			response.getWriter().print(result);
			
			
			break;
			
			
		case "/memberLogin":
			System.out.println("로그인 요청");
			// 1. 아이디 비밀번호 파라메터 확인
			// 2. service - 회원 정보 조회
			// select * from members where mid = ? and mpw = ?
			
			
			String loginId = request.getParameter("loginId");
			String loginPw = request.getParameter("loginPw");
			
			System.out.println("입력한 아이디: "+loginId);
			System.out.println("입력한 비밀번호:"+loginPw);
			
			Member member = msvc.memberLogin(loginId,loginPw);
			
			if(member == null) {
				System.out.println("로그인 실패");
				response.sendRedirect(path+"/Fail.jsp"+"?msg"+"="+URLEncoder.encode("로그인 또는 비밀번호가 일치하지 않습니다","UTF-8"));
			}
			else {
				System.out.println("로그인 성공");
				System.out.println("조회한 아이디: "+member.getMid());
				System.out.println("조회한 비밀번호: "+member.getMpw());
				// 로그인 처리 -> 세션
				session.setAttribute("loginMemberId", member.getMid()); // 세션(브라우저에 속한 객체)공간에 로그인 정보 담기 
				System.out.println("로그인 성공");
				
				String joinMsg = "로그인 성공";
				String joinUrl = path+"/main";
				
				response.sendRedirect(path+"/Success.jsp"+"?"
				+"msg"+"="+URLEncoder.encode(joinMsg,"UTF-8")+
				"&"+"url"+"="+URLEncoder.encode(joinUrl,"UTF-8"));
			}
			break;
			
			
		case "/memberLogout":
			System.out.println("로그아웃");
			session.removeAttribute("loginMemberId");
	response.sendRedirect(path+"/Fail.jsp"+"?msg"+"="+URLEncoder.encode("로그아웃 되었습니다","UTF-8")+
			"&url"+"="+URLEncoder.encode(path+"/main","UTF-8"));
			break;
			
			
		case "/myInfo":
			System.out.println("내 정보 확인");
			
			String logId=(String)session.getAttribute("loginMemberId");
			//session.getAttribute("loginMemberId") -> Object 타입
			System.out.println(logId);
			//로그인 o => logId
			// 로그인 x => null
			
			if(logId == null) {
				response.sendRedirect(path+"/Fail.jsp"+"?msg"+"="+URLEncoder.encode("로그인 안됨","UTF-8")+
						"&url"+"="+URLEncoder.encode(path+"/memberLoginForm","UTF-8"));
			}
			else {
				// service - 회원 정보 조회 
				// select * from members where mid = 로그인한 아이디 
				Member memberInfo = msvc.memberInfo(logId);
				System.out.println("회원 정보 조회: "+memberInfo);
				request.setAttribute("mInfo", memberInfo); // 세션에 안 담은 이유는 정보가 브라우저에 계속 담겨져있기 떄문이다
				request.getRequestDispatcher("myInfo.jsp").forward(request, response);
			}
			
			
			//response.sendRedirect(path+"/myInfo.jsp");
			
			break;
			

			
		}
			}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
