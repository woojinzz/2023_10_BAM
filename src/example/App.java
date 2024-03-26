package example;


import java.util.Scanner;

import example.controller.ArticleController;
import example.controller.Controller;
import example.controller.MemberController;

public class App {	
	public void run() {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		
		MemberController memberController = new MemberController(sc);
		ArticleController articleController = new ArticleController(sc);
		
		articleController.makeTestData();
		memberController.makeTestData();
		//memberController.isLogined();
		
		while(true) {
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			String[] cmdBits = cmd.split(" ");
			
			if (cmdBits.length == 1) {
				System.out.println("명령어를 입력해주세요");
				continue;
			}
			String controllerName = cmdBits[0];
			String methodName = cmdBits[1];
			
			String actionName = controllerName + "/" + methodName;
			
			switch (actionName) {
			case "article/weite" : 
			case "article/modify" : 
			case "article/delete" : 
			case "member/logout" : 
				if (Controller.isLogined() == false) {
					System.out.println("로그인을 먼저 해주세요.");
					continue;
				}
				break;
			case "member/join" :
			case "member/login" : 
				if (Controller.isLogined()) {
					System.out.println("로그아웃 후 이용해주세요.");
					continue;
				}
				break;
			}
			
			Controller controller = null;
			
			if (cmd.equals("exit")) {
				break;
			}
			if (controllerName.equals("member")) {
				controller = memberController;
			} else if (controllerName.equals("article")) {
				controller = articleController;
			} else {
				System.out.println("명령어가 존재하지 않습니다.");
				continue;
			}
			
			controller.doAction(cmd, methodName);
		}
	}

	

}
