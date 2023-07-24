package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;

public class MemberDao {
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
	
	public Member selectMemberInfo(String checkId) {
		System.out.println("dao - selectMemberInfo(호출)");
		
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return new Member(); // memberService에선 null 값을 받으면 중복체크에서 통과됨	
			} 
		    
		String sql = "select * from members "+"where mid=?";
		
		Member mem = null;
		
		try {
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, checkId);
			ResultSet rs  = pst.executeQuery();
			
			while(rs.next()) {
			mem = new Member();
			mem.setMid(rs.getString("mid"));
			mem.setMpw(rs.getString("mpw"));
			mem.setMname(rs.getString("mname"));
			mem.setMbirth(rs.getString("mbirth"));
			mem.setMaddr(rs.getString("maddr"));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 
		return mem;
	}

	public int insertMemberInfo(Member joinMember) {
		
System.out.println("dao - insertMemberInfo(호출)");
		
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return 0;
		 } 
		    int result = 0;
		 
		String sql = "insert into members(mid, mpw, mname, mbirth, maddr) "+"values(?,?,?,?,?)";
		 
				try {
					PreparedStatement pst = con.prepareStatement(sql);
					pst.setString(1, joinMember.getMid());
					pst.setString(2, joinMember.getMpw());
					pst.setString(3, joinMember.getMname());
					pst.setString(4, joinMember.getMbirth());
					pst.setString(5, joinMember.getMaddr());
					result=pst.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
		return result;
	}



	public Member selectMemberLoginInfo(String loginId, String loginPw) {
System.out.println("dao - selectMemberLoginInfo(호출)");
		
		Connection con = getConnection();
		
		 if(con == null) {
				System.out.println("DB 접속 실패");
				return null;
		 } 
		 Member mem = null;
		 
		 String sql = "select * from members "+ "where mid = ? and mpw = ?";
				 try {
					PreparedStatement pst  = con.prepareStatement(sql);
					pst.setString(1, loginId);
					pst.setString(2, loginPw);
					
					ResultSet rs = pst.executeQuery();
					
					while (rs.next()) {
						mem = new Member();
						mem.setMid(rs.getString("mid"));
						mem.setMpw(rs.getString("mpw"));
					}
					
				 } catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		 
		return mem;
	}

}
