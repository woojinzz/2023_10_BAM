package example.controller;

import java.util.Scanner;

import example.Container;
import example.dto.Member;
import example.service.ArticleService;
import example.service.MemberService;
import example.util.Util;

public class MemberController extends Controller {
	
	private ArticleService articleService;
	private MemberService memberService;

	
	public MemberController(Scanner sc){

		this.sc = sc;
		this.articleService = Container.articleService;
		this.memberService = Container.memberService;

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
		case "logout":
			doLogout();
			break;
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}

	private void doJoin() {

		int lastMemberId = memberService.getLastId();
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
			if(memberService.isloginIdDupChk(loginId)) {// 반복문 빠져나가기 위해 한번더 체크
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
		memberService.doJoin(member);
	
	System.out.printf("%s 회원님이 가입되셨습니다.\n", loginName);
	}
	
	private void doLogin() {
		
//		if (isLogined() == false) {
//			System.out.println("로그아웃 후 이용해주세요");
//			return;
//		}
		
		String loginId = null;
		while (true) {
			System.out.printf("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if(loginId.length() == 0) {
				System.out.println("아이디를 입력해주세요.");
				continue;
			}
			break;
		}
		
		String loginPw = null;
		while (true) {
			System.out.printf("비빌번호 : ");
			loginPw = sc.nextLine().trim();
			
			if(loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해주세요.");
				continue;
			}
			break;
		}
		
		Member member = memberService.getByMemberByLoginId(loginId);
		

		if(member == null) {
			System.out.printf("%s 는 존재하지 않는 아이디 입니다.\n", loginId);
			return;
		}
		if (member.loginPw.equals(loginPw) == false) {
			System.out.println("비밀번호를 확인해 주세요");
			return;
		}
		
		this.loginedMember = member; //현재 로그인 객체 연결

		
		
		System.out.printf("%s 님 환영합니다\n", member.loginName);
	}

	private void doLogout() {

		if (this.loginedMember == null) {
			System.out.println("로그인 후 이용해주세요");
			return;
		}
		this.loginedMember = null;
		System.out.println("로그아웃 되었습니다.");
		
	}

	@Override
	public void makeTestData() {//test 데이터 
		
		//0부터 시작하기 때문에 + 1 씩 각각 해줘야 함 / 전위연산은 이코드가 실행되기 전에 + 
		memberService.doJoin(new Member(memberService.getLastId(), Util.getDate(), "test1", "test1", "user1"));
		memberService.doJoin(new Member(memberService.getLastId(), Util.getDate(), "test2", "test2", "user2"));
		memberService.doJoin(new Member(memberService.getLastId(), Util.getDate(), "test3", "test3", "user3"));
		System.out.println("테스트용 회원데이터가 생성되었습니다.");
	}







}
