package example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.Container;
import example.dto.Article;
import example.service.ArticleService;
import example.service.MemberService;
import example.util.Util;

public class ArticleController extends Controller {
	
	
	//private Scanner sc; // 효율성에 의해 넘겨 받음
	private String cmd;
	private ArticleService articleService;
	private MemberService memberService;
	
	public ArticleController(Scanner sc) {
	
		this.sc = sc;
		this.cmd = null;
		this.articleService = Container.articleService;
		this.memberService = Container.memberService;
	}
	@Override
	public void doAction(String cmd, String methodName){
		this.cmd = cmd;
		switch (methodName) {
		case "write":
			doWrite();
			break;
		case "list":
			showList();
			break;
		case "detail":
			showDetail();
			break;
		case "modify":
			doModify();
			break;
		case "delete":
			doDelete();
			break;			
		default:
			System.out.println("존재하지 않는 명령어 입니다.");
			break;
		}
	}
	
	private void doWrite() {
		
		int lastArticleId = articleService.getLastId();
		
		System.out.printf("제목 : ");
		String title = sc.nextLine();
		System.out.printf("내용 : ");
		String body = sc.nextLine();

		Article article = new Article(lastArticleId,  Util.getDate(), loginedMember.id, title, body);//값 받아올 사용자 객체가 필요함
		Container.articleService.doWrite(article);
		
		System.out.printf("%d 번 글 생성\n" , lastArticleId);
	}
	private void showList() {
		
		List<Article> articles = articleService.getArticles();
		
		if (articles.size() == 0) {
			System.out.println("게시글이 없습니다.");
			return; //메서드 종료 void 라서 리턴값 없이 해당 메서드 종료
		}
		
		//article list 를 카운팅 하지 않고 length()사용
		String searchKeywoed = cmd.substring("article list".length()).trim();
		List<Article> printArticles = articles;// 아티클스에 있던거 넣어줌/원본 데이터를 지키기 위해

		if (searchKeywoed.length() > 0) {//검색어가 존재한다면
			System.out.println("검색어 : "+ searchKeywoed);
			 
			printArticles = new ArrayList<>();//빈 객체 연결
			 
			for (Article article : articles) {//검색어가 포함된 제목이 있는지 찾음
				if (article.title.contains(searchKeywoed)) {//포함관계 메서드 contains()
					printArticles.add(article);//일치한걸 찾으면 넣기
				}
			}
			if (printArticles.size() == 0) {
				System.out.println("검색결과가 없습니다.");
				return; //메서드 종료 void 라서 리턴값 없이 해당 메서드 종료
			}
		}
		System.out.println("== 게시글 목록 ==");
		System.out.println("번호	:	 작성일	 :             제목   :   작성자");
		
		for (int i = printArticles.size() - 1; i >= 0; i--) {//역순회 해야 해서 --
			Article article = printArticles.get(i);
			
			String writerName = memberService.getWriteName(article.memberId);
			System.out.printf("%d	:	 %s	 :   %s  :  %s\n", article.id, article.regDate, article.title, writerName);
		}
	}
	private void showDetail() {
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
	
		// 불린으로 가능 null 아니면 article / 백업을 위해 만듦 
		Article foundArticle = articleService.getArticleById(id);//여기 존재하는 아이디를 넘겨줘야 메소드에서 사용 가능

		if (foundArticle == null) {
			System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		
		String writerName = memberService.getWriteName(foundArticle.memberId);
		
		System.out.println("== 게시글 상세 ==");
		System.out.printf("번호 : %d\n", foundArticle.id);
		System.out.printf("작성일 : %s\n", foundArticle.regDate);
		System.out.printf("작성자 : %s\n", writerName);
		System.out.printf("제목 : %s\n", foundArticle.title);
		System.out.printf("내용 : %s\n", foundArticle.body);
	}	
	private void doModify() {
		
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("%d 번 게시글의 권한이 없습니다.\n", id);
			return;
		}
		System.out.println("== 게시글 수정 ==");
		System.out.printf("수정할 제목 : ");
		String title = sc.nextLine();
		System.out.printf("수정할 내용 : ");
		String body = sc.nextLine();
		
		articleService.doModify(foundArticle, title, body);
		System.out.printf("%s 게시글 수정이 완료되었습니다.",id);
	}
	private void doDelete() { 
		
		String[] cmdBits = cmd.split(" ");
		
		if (cmdBits.length == 2) {
			System.out.println("명령어를 확인해주세요");
			return;
		}
		
		int id = Integer.parseInt(cmdBits[2]);//String 를 int로 형 변환
		   
		Article foundArticle = articleService.getArticleById(id);
		
		if (foundArticle == null) {
			System.out.printf("%d 번 게시물은 존재하지 않습니다.\n", id);
			return;
		}
		if (foundArticle.memberId != loginedMember.id) {
			System.out.printf("%d 번 게시글의 권한이 없습니다.\n", id);
			return;
		}
		articleService.doDelete(foundArticle);
		System.out.printf("%d 번 게시물을 삭제 했습니다.\n",id);
	}

	@Override
	public void makeTestData() {//test 데이터 
		
		//0부터 시작하기 때문에 + 1 씩 각각 해줘야 함 / 전위연산은 이코드가 실행되기 전에 + 
		articleService.doWrite(new Article(articleService.getLastId(), Util.getDate(), 2, "제목1", "내용1"));
		articleService.doWrite(new Article(articleService.getLastId(), Util.getDate(), 3, "제목2", "내용2"));
		articleService.doWrite(new Article(articleService.getLastId(), Util.getDate(), 2, "제목3", "내용3"));
		System.out.println("테스트용 게시물이 생성되었습니다.");
	}



}
