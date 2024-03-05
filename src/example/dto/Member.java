package example.dto;

public class Member {
	public int id;
	public String regDate;
	public String loginId;
	public String loginPw;
	public String loginName;
	
	public  Member(int id, String regDate, String loginId,  String loginPw, String loginName) {
		this.id = id;
		this.regDate = regDate;
		this.loginId = loginId;
		this.loginPw = loginPw;
		this.loginName = loginName;
		
	}

}
