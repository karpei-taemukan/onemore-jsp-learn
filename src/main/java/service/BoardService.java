package service;

import java.util.ArrayList;

import dao.BoardDao;
import dto.Board;

public class BoardService {

	BoardDao bdao = new BoardDao();
	// 글 등록 기능
	public int boardWrite(Board board) {
		System.out.println("service-boardWrite() 호출");
		// board -> 글 제목, 글 내용, 작설자
		// 1. 새 글번호 생성 
		 // 현재 등록된 글번호의 최댓값 + 1
		// 등록된 글번호 최댓값 -> select max(bno) from boards;
			int bnoMax = bdao.selectMaxBno()+1;
			System.out.println("MAX BNO: "+bnoMax);
			board.setBno(bnoMax);
			// 2. dao - insert into boards values(?,?,?,?,0,'1');
		int result = bdao.insertBoard(board);
		
		return result;
	}
	public ArrayList<Board> getBoardList() {
		System.out.println("service-getBoardList() 호출");
		ArrayList<Board> bList = bdao.selectBoards();
		
		if(bList == null) {
			System.out.println("게시물 조회 실패");
		}
		else {
		System.out.println("게시물 조회 성공");
		}
			
		
			return bList;
	}
	public Board getBoardView(int viewBno) {
		System.out.println("service-getBoardView() 호출");
		
		// dao - update boards set bhits = bhits + 1 where bno = ?;
		
		bdao.updateBoardHits(viewBno);
		
		
		// dao - select 
		
		Board board = bdao.selectBoardView(viewBno);
		
		/*
		 * String bcontent  = board.getBcontents();
		 * bcontents = bcontents.replace("\r\n","<br>").repalce(" ","&nbsp;");
		 * board.setBcontents(bcontents);
		 * 
		 * */
		
		
		
		return board;
	}

	public int BoardDelete(Integer bDel) {
		System.out.println("BoardDelete() 호출");
		
		int result = bdao.updateBstate(bDel);
		 
		
		return result;
	}
	public ArrayList<Board> getSearchBoard(String searchTitle) {
		System.out.println("service-getSearchBoard() 호출");
		ArrayList<Board> boardList = bdao.selectBoardSearch(searchTitle);
		return boardList;
	}



}
