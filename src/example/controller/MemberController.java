package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Member;
import example.util.Util;

public class MemberController extends Controller {
	
	private List<Member> members; //회원 저장
	private int lastMemberId; //회원 순서
	
	
	public MemberController(Scanner sc){
		this.members = new ArrayList<>();; // 회원 초기화
		this.lastMemberId = 0;
		this.sc = sc;
	}
	
	@Override
	public void doAction(String cmd, String methodName) {
		switch (methodName) {
		case "join":
			doJoin();
			break;
		case "login":
			doLogin();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	
	}


	private void doJoin() {
		
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
			

			if(isloginIdDupChk(loginId)) {// 반복문 빠져나가기 위해 한번더 체크
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
	
	private void doLogin() {
		System.out.printf("아이디 : ");
		String loginId = sc.nextLine().trim();
		System.out.printf("비밀번호 : ");
		String loginPw = sc.nextLine().trim();
		
		Member member = null;
		
		for (Member m : members){
			if(m.loginId.equals(loginId)) {
				member = m;
				break;
			}
			
		}
		if(member == null) {
			System.out.printf("%s 는 존재하지 않는 아이디 입니다.\n", loginId);
			return;
		}
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해 주세요");
			return;
		}
		System.out.printf("%s 님 환영합니다\n", member.loginName);
		
		
	}
	
	private boolean isloginIdDupChk(String loginId) {
		
		for(Member member : members) {
			if(member.loginId.equals(loginId)) {
				return true; 
			
			}
		}
		return false;
	}






}
