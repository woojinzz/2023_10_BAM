import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		
		System.out.println("==시작==");
		
		Scanner sc = new Scanner(System.in);
		int a = 0;
		List<Article> articles = new ArrayList<>();
		
		while(true) {
			
			System.out.printf("명령어) ");
			String cmd = sc.nextLine();
			
			if(cmd.equals("exit")) {
				break;
			}
			
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
			else if(cmd.equals("article list")) {
				
				if(articles.size() == 0) {
					System.out.println("게시글이 없습니다.");
					continue;// 가장 가까운 반복문으로 이동
				}
				
				System.out.println("번호	:	 제목	");
				
				for(int i = articles.size() - 1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d	:	%s	\n",article.id, article.title);
				}
				
			}
			
		}
		sc.close();
	
		System.out.println("==끝==");
		
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
