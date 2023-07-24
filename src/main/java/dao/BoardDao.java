package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Board;

public class BoardDao {

	Connection getConnection() {
		Connection con  = null;

		String url="jdbc:oracle:thin:@//localhost:1521/xe"; 
	    String userid="jsp_board"; 
	    String userpw="1111";
	    
		try {
				Class.forName("oracle.jdbc.OracleDriver");
				 con = DriverManager.getConnection(url, userid, userpw);
		}
		catch (ClassNotFoundException e) {
				e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return con;	
		
	}
	
	public int selectMaxBno() {
		System.out.println("dao - selectMaxBno(호출)");
		
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return -1;	
			} 
		 
		 String sql = "select nvl(max(bno),0) from boards";
		 
		 int result =0;
		 
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while(rs.next()){
				result = rs.getInt(1); 		
				}	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 		return result;
	}

	public int insertBoard(Board board) {
	System.out.println("dao - insertBoard(호출)");
		
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return 0;	
			} 
		 
		 int result=0;
		 String sql = "insert into boards(bno,bwriter,btitle,bcontents,bhits,bstate) values(?,?,?,?,0,'1')";
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, board.getBno());
			pst.setString(2, board.getBwriter());
			pst.setString(3, board.getBtitle());
			pst.setString(4, board.getBcontent());
			result=pst.executeUpdate();
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return result;
	}

	public ArrayList<Board> selectBoards() {
		
		System.out.println("dao - selectBoards(호출)");
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return null;	
			} 
		 
		 ArrayList<Board> boards =  new ArrayList<Board>();
		 
		 String sql = "select * from boards "+ "where bstate = '1'"+" order by bno";
		  
			try {
				PreparedStatement pst = con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				
				while (rs.next()) {
					Board bList = new Board();
					
					bList.setBno(rs.getInt("bno"));
					bList.setBwriter(rs.getString("bwriter"));
					bList.setBtitle(rs.getString("btitle"));
					bList.setBcontent(rs.getString("bcontents"));
					bList.setBhits(rs.getInt("bhits"));
					bList.setBstate(rs.getString("bstate"));
					boards.add(bList);
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 
		 return boards;
	}

	public Board selectBoardView(int viewBno) {

		System.out.println("dao - selectBoardView(호출)");
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return null;	
			} 
		 
		 Board board = null;
		 
		 String sql = "select * from boards"+" where bno = ?";
		 
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, viewBno);
			ResultSet rs = pst.executeQuery();
			
			while (rs.next()) {
				board = new Board();
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontents"));
				board.setBhits(rs.getInt("bhits"));
				board.setBno(rs.getInt("bno"));
				board.setBstate(rs.getString("bstate"));
				board.setBwriter(rs.getString("bwriter"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return board;
	}

	public void updateBoardHits(int viewBno) {
		System.out.println("dao - updateBoardHits(호출)");
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return;	
			}
		 
		 String sql = "update boards set bhits = bhits+1 "+"where bno = ?";
		 
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, viewBno);
			pst.executeUpdate();
		 }
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}


	public int updateBstate(Integer bDel) {
		System.out.println("dao - updateBstate(호출)");
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return 0;	
			}
		 int result =0;
		 String sql = "update boards set bstate = 0 " + "where bno = ?";
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, bDel);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Board> selectBoardSearch(String searchTitle) {
		System.out.println("dao - selectBoardSearch(호출)");
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return null;	
			}
		 
		// String sql = "select * from boards where bstate = 1 and btitle like '%'||?||'%'";
		 
		 String sql = "select * from boards where bstate = 1 and btitle like '%"+searchTitle+"%' "
				 +"order by bno desc";
		
		 ArrayList<Board> boardList = new ArrayList<Board>();
		 try {
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
			Board board = new Board();
				board.setBtitle(rs.getString("btitle"));
				board.setBcontent(rs.getString("bcontents"));
				board.setBhits(rs.getInt("bhits"));
				board.setBno(rs.getInt("bno"));
				board.setBstate(rs.getString("bstate"));
				board.setBwriter(rs.getString("bwriter"));
				boardList.add(board);
			}
		 }
		 catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return boardList;
	}

	

}
