package example.dao;

import java.util.ArrayList;
import java.util.List;

import example.dto.Member;

public class Dao {
	public int lastId;
	private List<Member> members; //회원 저장
	
	public Dao() {
		this.members = new ArrayList<>();; // 회원 초기화
	}
	
	
	public int getLastId() {
		return this.lastId + 1;
	}
	
	public Member getMemberByLoginId(String loginId) {
		for (Member member : members){
			if(member.loginId.equals(loginId)) {
			    return member;
			}
		}
		return null;
	}

}
