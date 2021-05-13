package opinion;

public class Member {
	public String login;
	private String password;
	public String description;
	
	public Member(String login, String password, String description) {
		this.login = login;
		this.password = password;
		this.description = description;
	}
	
	public String getLogin() { return this.login;}
	
	public String getPassword() { return this.password;}
}
