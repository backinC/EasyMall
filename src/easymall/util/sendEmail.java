package easymall.util;

import org.apache.commons.mail.HtmlEmail;

public class sendEmail {
	//邮箱验证码
		public static boolean sendemail(String emailaddress,String code){
			try {
				HtmlEmail email = new HtmlEmail();
				email.setHostName("smtp.qq.com");
				email.setCharset("UTF-8");
				email.addTo(emailaddress);// 收件地址
	 
				email.setFrom("2670220145@qq.com", "EasyMall官网");
	 
				email.setAuthentication("2670220145@qq.com","igtjbbcpzqeidjgf");
	 
				email.setSubject("注册验证码");
				email.setMsg("尊敬的用户您好,您本次注册的验证码是:" + code);
	 
				email.send();
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
}
