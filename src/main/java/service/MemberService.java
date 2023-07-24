package service;

import dao.MemberDao;
import dto.Member;

public class MemberService {
MemberDao mdao = new MemberDao();

// 아이디 중복 확인 기능
public String memberIdCheck(String checkId) {

	System.out.println("service - memberIdCheck() 호출");
	
	String checkResult = "Y";
	
	// select * from members where mid=?
	Member mem = mdao.selectMemberInfo(checkId);
	
	if(mem != null) {
		checkResult = "N";
	}

	return checkResult;
}

public int memberJoin(Member joinMember) {
	System.out.println("service - memberJoin() 호출");
	int inserResult = mdao.insertMemberInfo(joinMember);
	
	return inserResult;
}



// 로그인 -회원정보 조회
public Member memberLogin(String loginId, String loginPw) {
System.out.println("service - memberLogin() 호출");

Member mem  = mdao.selectMemberLoginInfo(loginId, loginPw);
	return mem;
}

public Member memberInfo(String logId) {
Member member  = mdao.selectMemberInfo(logId);
member.setMbirth(member.getMbirth().split(" ")[0]);
// select mid, mpw, mname, to_char(mbirth, 'yyyy-mm-dd'), maddr from members where mid = ?;
	return member;
}




}
