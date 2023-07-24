package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Board;
import service.BoardService;

/**
 * Servlet implementation class BoardController
 */
@WebServlet({"/boardList","/boardWriteForm","/boardWrite","/boardView", "/boardDelete","/boardSearch"})
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		 String url = request.getServletPath();
		 String path = request.getContextPath();
		 
		 BoardService bmvc = new BoardService();
		 
		 switch (url) {
		case "/boardList":
			System.out.println("게시판 이동 요청");
			// 1. Board 게시글 목록 조회 select * from boards where bstate = '1';
			 ArrayList<Board> bList = bmvc.getBoardList();
				request.setAttribute("bList", bList);
			// 2. 게시판 페이지로 응답
			
			request.getRequestDispatcher("BoardList.jsp").forward(request, response);
		break;
		
		case "/boardWriteForm":
			response.sendRedirect(path+"/BoardWriteForm.jsp");
			break;
			
		case "/boardWrite":
			System.out.println("글등록 요청");
		
			String bwriter = (String)request.getSession().getAttribute("loginMemberId");
		
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
			if(bwriter == null) { // session은 오랫동안 아무동작 안하면 세션 내용 삭제됨
				response.sendRedirect(path+"/Fail.jsp"+"?msg"+"="+URLEncoder.encode("로그인 안됌","UTF-8")+
						"&url"+"="+URLEncoder.encode(path+"/memberLoginForm","UTF-8"));
			}
			else {				
				// 1. 파라메터 - 글제목 글내용 확인 
				String btitle = request.getParameter("boardTitle");
				String bcontent = request.getParameter("boardContent");
				
				System.out.println("입력한 게시판 제목: "+btitle);
				System.out.println("입력한 게시판 내용: "+bcontent);
				
				Board board = new Board();
				
				board.setBtitle(btitle);
				board.setBcontent(bcontent);
				board.setBwriter(bwriter);
				
				 
				// 2. service 글 등록 기능 호출 
				
				int writeResult = bmvc.boardWrite(board);
				
				// 3. 등록결과 확인 및 응답 
				if(writeResult > 0) {
					System.out.println("글 등록 성공");
					// BoardList.jsp로 이동
				
					response.sendRedirect(path+"/Success.jsp"+"?"
					+"msg"+"="+URLEncoder.encode("글등록 성공","UTF-8")+
					"&"+"url"+"="+URLEncoder.encode(path+"/boradList","UTF-8"));
				}
				else {
					System.out.println("글 등록 실패");
					// BoardWriteForm.jsp로 뒤로가기
					response.sendRedirect(path+"/Fail.jsp"+"?msg"+"="+URLEncoder.encode("글등록 실패","UTF-8")+
							"&url"+"="+URLEncoder.encode(path+"/boardWriteForm","UTF-8"));
				}
				
				
			}
			break;
			
			
		case "/boardView":
			System.out.println("글 상세보기 페이지 이동 요청");
			int viewBno = Integer.parseInt(request.getParameter("viewBno"));
			System.out.println("상세보기 글번호: "+ viewBno);
			// 1. 상세보기 페이지에 출력할 글 조회
			// select * from boards where bno = ?;
			Board bView = bmvc.getBoardView(viewBno);
			request.setAttribute("board", bView);
			request.getRequestDispatcher("BoardView.jsp").forward(request, response); // Dispatcher는 데이터를 가지고 요청
			
		break;	
			
		
		case "/boardDelete":
			System.out.println("글 삭제 요청");
			Integer bDel = Integer.parseInt(request.getParameter("delBno"));
			System.out.println("삭제할 글번호: "+bDel);
			/*
			 *update boards set bstate = 0 where bno = ?;
			 * */
			int bdelResult = bmvc.BoardDelete(bDel);
			
			if(bdelResult > 0 ) {
				System.out.println("삭제처리 성공");
				response.sendRedirect(path+"/Success.jsp"+"?"
						+"msg"+"="+URLEncoder.encode(bDel+"번 글 삭제","UTF-8")+
						"&"+"url"+"="+URLEncoder.encode(path+"/boradList","UTF-8"));
			}
			else {
			System.out.println("삭제처리 실패");
			}
		
			break;
			
			
		case "/boardSearch":
			System.out.println("글 검색 요청");
			String searchTitle = request.getParameter("searchTitle");
			System.out.println("검색어: "+ searchTitle);

			/*select * from boards where bstate = 1 and btitle like '%~~%'*/
			ArrayList<Board> searchBList = bmvc.getSearchBoard(searchTitle);
			request.setAttribute("bList", searchBList);
			request.getRequestDispatcher("BoardList.jsp").forward(request, response);
			
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
