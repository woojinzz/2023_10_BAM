import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	static List<Article> articles;// 게시글 저장
	static int lastArticleId; //글 순서
	
	static {
		articles =  new ArrayList<>(); // 아티클스 초기화
		lastArticleId = 0;
	}
	
	public static void main(String[] args) {
		
		System.out.println("== 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		//int lastArticleId = 0;//글 넘버
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
				articles.add(article);
				
				System.out.printf("%d 번 글 생성\n" , lastArticleId);
		
			}
			//게시글 목록
			else if(cmd.equals("article list")) {
				
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue; //가장 가까운 반복문으로 이동
				}
				System.out.println("== 게시글 목록 ==");
				System.out.println("번호	:	 제목	 :   작성일");
				
				for(int i = articles.size() - 1; i >= 0; i--) {//역순회 해야 해서 --
					Article article = articles.get(i);
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
				   
				Article foundArticle = null;// 불린으로 가능
				
				for(Article article : articles) {
					if(article.id == id) {
						foundArticle = article;
						break;
					} 
				}
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
				Article foundArticle = null;// 불린으로 가능
				
				for(Article article : articles) {
					if(article.id == id) {
						foundArticle = article;
						break;
					} 
				}
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
				
				for(Article article : articles) { //백업
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
				articles.remove(foundArticle);
				System.out.printf("%d 번 게시물을 삭제 했습니다.\n",id);
			}
			else {
				System.out.println("명령어가 존재하지 않습니다.");
			}
		}
		sc.close();//스캐너 종료
		System.out.println("== 끝 ==");
	}

	private static void makeTestData() {
		
		//전위연산은 이코드가 실행되기 전에 +
		articles.add(new Article(++lastArticleId, Util.getDate(), "제목1", "내용1"));
	    articles.add(new Article(++lastArticleId, Util.getDate(), "제목2", "내용2"));
		articles.add(new Article(++lastArticleId, Util.getDate(), "제목3", "내용3"));
		System.out.println("테스트용 게시물이 생성되었습니다.");
		
		
		
	}
}

class Article{
	
	int id;
	String regDate;
	String title;
	String body;
	
	//생성자 생성해서 바로 넘김
	public Article(int id, String regDate, String title, String body) {
		
		this.id = id;
		this.regDate = regDate;
		this.title = title;
		this.body =body;
	}
	
}
