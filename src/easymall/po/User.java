package easymall.po;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class User {
	private int id;
	@NotBlank(message="�������û���")
	private String username;
	
	@Length(min=6,max=16,message="�����ʽ����ȷ,ӦΪ6-16λ")
	private String password;
	
	@NotBlank(message="�������ǳ�")
	private String nickname;
	
	@NotBlank(message="����������")
	@Email(message="�����ʽ����ȷ")
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
