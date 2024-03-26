package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.Container;
import example.dto.Article;
import example.dto.Member;

public class MemberDao extends Dao{
	private List<Member> members; //회원 저장

	public MemberDao() {
		this.members = new ArrayList<>();; // 회원 초기화
		
	}
	
	public int getListMemberId() {
		return this.lastId + 1;
	}
	
	public List<Member> getMembers() {
		return this.members;
	}
	
	public void doJoin(Member member) {
		this.members.add(member);
		this.lastId++;
	}
	
	public Member getMemberByLoginId(String loginId) {
		for (Member member : members){
			if(member.loginId.equals(loginId)) {
			    return member;
			}
		}
		return null;
	}
	
	public boolean isloginIdDupChk(String loginId) {
		Member member = getMemberByLoginId(loginId);
		
		if (member != null) {
			return true;
		}
		return false;
	}

	public String getWriteName(int memberId) {
		
		for (Member member : Container.memberDao.getMembers()) {
			if (memberId == member.id) {
				return member.loginName;
			}
		}

		return null;
	}
	

} 
