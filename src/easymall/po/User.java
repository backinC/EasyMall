package easymall.po;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class User {
	private int id;
	@NotBlank(message="请输入用户名")
	private String username;
	
	@Length(min=6,max=16,message="密码格式不正确,应为6-16位")
	private String password;
	
	@NotBlank(message="请输入昵称")
	private String nickname;
	
	@NotBlank(message="请输入邮箱")
	@Email(message="邮箱格式不正确")
	private String email;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
