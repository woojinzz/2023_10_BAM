package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Member;
import example.util.Util;

public class MemberController {
	
	private List<Member> members; //회원 저장
	private int lastMemberId; //회원 순서
	private Scanner sc; // 효율성에 의해 넘겨 받음
	
	public MemberController(Scanner sc){
		this.members = new ArrayList<>();; // 회원 초기화
		this.lastMemberId = 0;
		this.sc = sc;
	}
	public void odJoin() {
		
		lastMemberId++;
		String loginId = null;
		String loginPw = null; 
		String loginPwChk = null; 
		String loginName = null; 
		
		while(true) {
			System.out.printf("아이디: ");
			loginId = sc.nextLine().trim();
			
			if(loginId.length() == 0) {
				System.out.println("아이디는 필수입력 정보입니다.");
				continue;
			}
			
			boolean loginIdDupChk = false;// 중복 체크
			
			for(Member member : members) {
				if(loginId.equals(member.loginId)) {
					loginIdDupChk = true; // 중복이면
				}
			}
			if(loginIdDupChk) {// 반복문 빠져나가기 위해 한번더 체크
				System.out.printf("%s 는 이미 사용중인 아이디 입니다.\n", loginId);
				continue;
			}
			System.out.printf("%s 는 사용 가능한 아이디 입니다.\n", loginId);
			break;
		}
		
		while(true) {
			System.out.printf("비밀번호: ");
			loginPw = sc.nextLine().trim();
			if(loginPw.length() == 0) {
				System.out.println("비빌번호는 필수 입력 정보입니다.");
				continue;
			}
			
			System.out.printf("비밀번호 확인: ");
			loginPwChk = sc.nextLine();
			
			if(!loginPw.equals(loginPwChk)) {
				
				System.out.println("비밀번호가 일치하지 않습니다.");
				continue;
			}
			System.out.println("비밀번호가 일치합니다.");
			break;
		}
		while(true) {
			
			System.out.printf("이름: ");
			loginName = sc.nextLine().trim();
			
			if(loginName.length() == 0) {
				System.out.println("이름은 필수 입력정보 입니다.");
				continue;
			}
			break;
		}
	
		
		Member member = new Member(lastMemberId, Util.getDate(), loginId, loginPw, loginName);
		this.members.add(member);
		
		for(Member mem : members) {
			System.out.printf("%s	:	 %s	 :   %s :     %s\n", Util.getDate(), member.loginId, member.loginPw, member.loginName);
		}
	System.out.printf("%s 회원님이 가입되셨습니다.\n", loginName);
		
	}

}
