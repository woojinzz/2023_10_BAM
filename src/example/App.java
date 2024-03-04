package example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Article;
import example.util.Util;

public class App {
	
	private List<Article> articles;// 게시글 저장
	private int lastArticleId; //글 순서
	
	public App() {
		this.articles =  new ArrayList<>(); // 아티클스 초기화
		this.lastArticleId = 0;
	}
	
	
	public void run() {
		
		System.out.println("== 프로그램 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		String num ;// 사용자 입력
		makeTestData();
		
		while(true) {
			
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				break;
			}
			//게시글 작성
			if(cmd.equals("article write")) {
				
				lastArticleId++;
				
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
	
				Article article = new Article(lastArticleId, Util.getDate(), title, body);//값 받아올 사용자 객체가 필요함
				this.articles.add(article);
				
				System.out.printf("%d 번 글 생성\n" , lastArticleId);
		
			}
			//게시글 목록
			else if(cmd.equals("article list")) {
				
				if(this.articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue; //가장 가까운 반복문으로 이동
				}
				System.out.println("== 게시글 목록 ==");
				System.out.println("번호	:	 제목	 :   작성일");
				
				for(int i = this.articles.size() - 1; i >= 0; i--) {//역순회 해야 해서 --
					Article article = this.articles.get(i);
					System.out.printf("%d	:	 %s	 :   %s\n", article.id, article.title, article.regDate);
				}
				
			}
			//게시글 번호 검색
			else if(cmd.startsWith("article detail ")) {//문자열로 시작하면
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
				
//				for(int i = 0; i<cmdBits.length; i++) {//배열값 찍어보기
//					
//					System.out.println(cmdBits[i]);
//				}
				// 불린으로 가능 null 아니면 article / 백업을 위해 만듦 
				Article foundArticle = getArticleById(id);//여기 존재하는 아이디를 넘겨줘야 메소드에서 사용 가능
				

				if(foundArticle == null) {
					System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.println("== 게시글 상세 ==");
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("작성일 : %s\n", foundArticle.regDate);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
		
			}
			//게시글 수정
			else if(cmd.startsWith("article modify ")) {//문자열로 시작하면
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
				Article foundArticle = getArticleById(id);
				
				if(foundArticle == null) {
					System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.println("== 게시글 수정 ==");
				System.out.printf("수정할 제목 : ");
				String title = sc.nextLine();
				System.out.printf("수정할 내용 : ");
				String body = sc.nextLine();
				
				foundArticle.title = title;
				foundArticle.title = body;
			}
			//게시글 삭제
			else if(cmd.startsWith("article delete ")) {//문자열로 시작하면
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
				   
				Article foundArticle = null;// 불린으로 가능
				int foundIndex = -1;
				int i = -1;
				
				for(Article article : this.articles) { //백업
					if(article.id == id) {
//						foundArticle = article;
						foundIndex = i;
						break;
					} 
				}
				if(foundArticle == null) {
					System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				this.articles.remove(foundArticle);
				System.out.printf("%d 번 게시물을 삭제 했습니다.\n",id);
			}
			else {
				System.out.println("명령어가 존재하지 않습니다.");
			}
		}
		sc.close();//스캐너 종료
		System.out.println("== 끝 ==");
		
	}
	private Article getArticleById(int id) {// id 값 넘겨 받음
		
		for(Article article : this.articles) { // 아디클을 넘겨주기 위해 존재
			if(article.id == id) {
				return article;
			} 
		}
		return null;
	}


	private void makeTestData() {//test 데이터 
		
		//0부터 시작하기 때문에 + 1 씩 각각 해줘야 함 / 전위연산은 이코드가 실행되기 전에 + 
		this.articles.add(new Article(++lastArticleId, Util.getDate(), "제목1", "내용1"));
	    this.articles.add(new Article(++lastArticleId, Util.getDate(), "제목2", "내용2"));
		this.articles.add(new Article(++lastArticleId, Util.getDate(), "제목3", "내용3"));
		System.out.println("테스트용 게시물이 생성되었습니다.");
	}

}
