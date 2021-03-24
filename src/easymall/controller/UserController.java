package easymall.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easymall.po.User;
import easymall.service.UserService;
import easymall.util.number;
import easymall.util.sendEmail;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public String Login(User user, HttpSession session, Model model){
		User muser = userService.login(user);
		if(muser != null){
			session.setAttribute("user", muser);
			return "redirect:/index.jsp";
		}else{
			model.addAttribute("messageError", "�û������������");
			return "login";
		}
	}
	
	@RequestMapping(value = "/checkUser", method = RequestMethod.POST)
	public void check(HttpServletRequest request,
				HttpServletResponse response) throws IOException{
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		if(userService.checkUsername(username)) {
			response.getWriter().print("�û���" + username + "�ѱ�ע�ᣡ");
		}else {
			response.getWriter().print("��ϲ����" + username + "����ʹ�ã�");
		}
	}
	
	@RequestMapping("/send")
	public String send(String email, Model model,HttpSession session,HttpServletRequest request){
		String r = new number().getCheckCode();
		System.out.println(email);
		sendEmail.sendemail(email, r);
		HttpSession se = request.getSession();
		se.setAttribute("evstr", r);
		return "regist";
	}
	
	@RequestMapping("/regist")
	public String regist(@Valid User user, BindingResult result,String valistr, String emailvalistr, HttpSession session, Model model) {
		if (result.hasErrors()){
			String msg = new String();
	        List<ObjectError> errorList = result.getAllErrors();
	        for(ObjectError error : errorList){
	            System.out.println(error.getDefaultMessage());
	            msg = error.getDefaultMessage();
	        }
	        model.addAttribute("msg", msg);
	        return "regist";
	    }
//		if(user.getUsername() == null || user.getUsername() == "") {
//			model.addAttribute("msg", "�û�������Ϊ�գ�");
//			return "regist";
//		}
//		if(user.getPassword() == null || user.getPassword() == "") {
//			model.addAttribute("msg", "���벻��Ϊ�գ�");
//			return "regist";
//		}
		
//		System.out.println(emailvalistr);
//		System.out.println(session.getAttribute("evstr"));
		if(!valistr.equalsIgnoreCase(session.getAttribute("code").toString())) {
			model.addAttribute("msg", "��֤�����");
			return "regist";
		}
		if(!emailvalistr.equalsIgnoreCase(session.getAttribute("evstr").toString())) {
			model.addAttribute("msg", "������֤�����");
			return "regist";
		}
		if(userService.regist(user) > 0) {
			model.addAttribute("msg", "ע��ɹ�");
			return "redirect:/index/index";
		}else {
			model.addAttribute("msg", "ע��ʧ��");
			return "regist";
		}
		
	}
}
