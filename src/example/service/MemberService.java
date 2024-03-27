package example.service;

import example.Container;
import example.dao.MemberDao;
import example.dto.Member;

public class MemberService {
	

	private MemberDao memberDao;
	
	public MemberService() {

		this.memberDao = Container.memberDao;
	}

	public int getLastId() {
		// TODO Auto-generated method stub
		return memberDao.getLastId();
	}

	public boolean isloginIdDupChk(String loginId) {
		// TODO Auto-generated method stub
		return memberDao.isloginIdDupChk(loginId);
	}

	public void doJoin(Member member) {
		memberDao.doJoin(member);
		
	}
	
	public String getWriteName(int memberId) {
		// TODO Auto-generated method stub
		return memberDao.getWriteName(memberId);
	}

	public Member getByMemberByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return memberDao.getMemberByLoginId(loginId);
	}
}
