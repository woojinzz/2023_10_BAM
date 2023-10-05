import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("== 시작 ==");
		
		Scanner sc = new Scanner(System.in);
		int a = 0;//글 넘버
		String num ;// 사용자 입력
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				break;
			}
			//게시글 작성
			if(cmd.equals("article write")) {
				
				int id = a + 1;
				a = id;
				System.out.printf("제목 : ");
				String title = sc.nextLine();
				System.out.printf("내용 : ");
				String body = sc.nextLine();
				
				Article article = new Article(id, title, body);
				articles.add(article);
				
				System.out.printf("%d 번 글 생성\n" , id);
		
			}
			//게시글 목록
			else if(cmd.equals("article list")) {
				
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue; //가장 가까운 반복문으로 이동
				}
				System.out.println("== 게시글 목록 ==");
				System.out.println("번호	:	 제목	");
				
				for(int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	:	%s	\n",article.id, article.title);
				}
				
			}
			//게시글 번호 검색
			else if(cmd.startsWith("article detail ")) {//문자열로 시작하면
				
				String[] cmdBits = cmd.split(" ");
				int id = Integer.parseInt(cmdBits[2]);//형 변환
				
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
					System.out.printf("2번 게시물은 존재하지 않습니다.\n", id);
					continue;
				}
				
				System.out.println("== 게시글 상세 ==");
				System.out.printf("번호 : %d\n", foundArticle.id);
				System.out.printf("제목 : %s\n", foundArticle.title);
				System.out.printf("내용 : %s\n", foundArticle.body);
		
			}else {
				
				System.out.println("명령어가 존재하지 않습니다.");
				
			}
			
			
		}

		sc.close();
	
		System.out.println("== 끝 ==");
		
	}
}

class Article{
	
	int id;
	String title;
	String body;
	
	public Article(int id, String title, String body) {
		
		this.id = id;
		this.title = title;
		this.body =body;
	}
	
}
