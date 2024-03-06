package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.dto.Article;
import example.util.Util;

public class ArticleController {
	private List<Article> articles;// 게시글 저장
	private int lastArticleId; //글 순서
	private Scanner sc; // 효율성에 의해 넘겨 받음
	
	public ArticleController(Scanner sc) {
		this.articles =  new ArrayList<>(); // 아티클스 초기화
		this.lastArticleId = 0;
		this.sc = sc;
	}
	
	public void doWrite() {
		
		lastArticleId++;
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(lastArticleId, Util.getDate(), title, body);//값 받아올 사용자 객체가 필요함
		this.articles.add(article);
		
		System.out.printf("%d 번 글 생성\n" , lastArticleId);
	}
	public void showList(String cmd) {
		//article list 를 카운팅 하지 않고 length()사용
		String searchKeywoed = cmd.substring("article list".length()).trim();
		List<Article> printArticles = this.articles;// 아티클스에 있던거 넣어줌/원본 데이터를 지키기 위해

		if(this.articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return; //메서드 종료 void 라서 리턴값 없이 해당 메서드 종료
		}
		if(searchKeywoed.length() > 0) {//검색어가 존재한다면
			System.out.println("검색어 : "+ searchKeywoed);
			
			printArticles = new ArrayList<>();//빈 객체 연결
			
			for(Article article : articles) {//검색어가 포함된 제목이 있는지 찾음
				if(article.title.contains(searchKeywoed)) {//포함관계 메서드 contains()
					printArticles.add(article);//일치한걸 찾으면 넣기
				}
			}
			if(printArticles.size() == 0) {
				System.out.println("검색결과가 없습니다.");
				return; //메서드 종료 void 라서 리턴값 없이 해당 메서드 종료
			}
		}
		System.out.println("== 게시글 목록 ==");
		System.out.println("번호	:	 제목	 :   작성일");
		
		for(int i = printArticles.size() - 1; i >= 0; i--) {//역순회 해야 해서 --
			Article article = printArticles.get(i);
			System.out.printf("%d	:	 %s	 :   %s\n", article.id, article.title, article.regDate);
		}
	}
	public void showDetail(String cmd) {
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
		
//		for(int i = 0; i<cmdBits.length; i++) {//배열값 찍어보기
//			
//			System.out.println(cmdBits[i]);
//		}
		// 불린으로 가능 null 아니면 article / 백업을 위해 만듦 
		Article foundArticle = getArticleById(id);//여기 존재하는 아이디를 넘겨줘야 메소드에서 사용 가능

		if(foundArticle == null) {
			System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		System.out.println("== 게시글 상세 ==");
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성일 : %s\n", foundArticle.regDate);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
	}	
	public void doModify(String cmd) {
		
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		System.out.println("== 게시글 수정 ==");
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();
		
		foundArticle.title = title;
		foundArticle.title = body;
		System.out.printf("%s 게시글 수정이 완료되었습니다.",id);
	}
	public void doDelete(String cmd) {
		
		String[] cmdBits = cmd.split(" ");
		int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
		   
		Article foundArticle = getArticleById(id);
		
		if(foundArticle == null) {
			System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		this.articles.remove(foundArticle);
		System.out.printf("%d 번 게시물을 삭제 했습니다.\n",id);
	}
	private Article getArticleById(int id) {// id 값 넘겨 받음
		
		for(Article article : this.articles) { // 아디클을 넘겨주기 위해 존재
			if(article.id == id) {
				return article;
			} 
		}
		return null;
	}
	public void makeTestData() {//test 데이터 
		
		//0부터 시작하기 때문에 + 1 씩 각각 해줘야 함 / 전위연산은 이코드가 실행되기 전에 + 
		this.articles.add(new Article(++lastArticleId, Util.getDate(), "제목1", "내용1"));
	    this.articles.add(new Article(++lastArticleId, Util.getDate(), "제목2", "내용2"));
		this.articles.add(new Article(++lastArticleId, Util.getDate(), "제목3", "내용3"));
		System.out.println("테스트용 게시물이 생성되었습니다.");
	}

}
