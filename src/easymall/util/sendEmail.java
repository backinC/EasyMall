package easymall.util;

import org.apache.commons.mail.HtmlEmail;

public class sendEmail {
	//������֤��
		public static boolean sendemail(String emailaddress,String code){
			try {
				HtmlEmail email = new HtmlEmail();
				email.setHostName("smtp.qq.com");
				email.setCharset("UTF-8");
				email.addTo(emailaddress);// �ռ���ַ
	 
				email.setFrom("2670220145@qq.com", "EasyMall����");
	 
				email.setAuthentication("2670220145@qq.com","igtjbbcpzqeidjgf");
	 
				email.setSubject("ע����֤��");
				email.setMsg("�𾴵��û�����,������ע�����֤����:" + code);
	 
				email.send();
				return true;
			}
			catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
}
