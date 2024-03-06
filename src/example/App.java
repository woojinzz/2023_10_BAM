package example;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.controller.ArticleController;
import example.controller.MemberController;
import example.dto.Article;
import example.dto.Member;
import example.util.Util;

public class App {	
	public void run() {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		
		//String num ;// 사용자 입력
		
		
		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				break;
			}
			//회원가입
			if(cmd.equals("member join")) {
				memberController.odJoin();
			}
			//게시글 작성
			else if(cmd.equals("article write")) {
				articleController.doWrite();
			}
			//게시글 목록
			else if(cmd.startsWith("article list")) {
				articleController.showList(cmd);
			}
			//게시글 번호 검색
			else if(cmd.startsWith("article detail ")) {//문자열로 시작하면
				articleController.showDetail(cmd);
			}
			//게시글 수정
			else if(cmd.startsWith("article modify ")) {//문자열로 시작하면
				articleController.doModify(cmd);
			}
			//게시글 삭제
			else if(cmd.startsWith("article delete ")) {//문자열로 시작하면
				articleController.doDelete(cmd);
			}
			else {
				System.out.println("명령어가 존재하지 않습니다.");
			}
		}
		sc.close();//스캐너 종료
		System.out.println("== 끝 ==");
	}

	

}
