package cn.tedu.note.web;

import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.note.entity.User;
import cn.tedu.note.service.PasswordException;
import cn.tedu.note.service.UserNameException;
import cn.tedu.note.service.UserService;
import cn.tedu.note.util.JsonResult;
import cn.tedu.note.util.Login;

@Controller
@RequestMapping("/user")
public class UserController extends Login{
	
	@Resource
	private UserService userService;
	//注册
	@RequestMapping("/save.do")
	@ResponseBody
	public Integer save(String name,String nickname,
			String password){
		Integer result = userService.save(name, nickname, password);
		
		return result;
	}
	
	//登录
	@RequestMapping("/login.do")
	@ResponseBody
	public JsonResult<User> login(String name,
			String password,HttpServletRequest request,HttpSession session){
		try{
			User user = 
				userService.login(name, password);
			String talkId = UUID.randomUUID().toString();
			session.setAttribute(talkId, name);
			user.setToken(talkId);
			return new JsonResult<User>(user);
		}catch(PasswordException e){
			e.printStackTrace();
			return new JsonResult<User>(3,e);
		}catch(UserNameException e){
			e.printStackTrace();
			return new JsonResult<User>(2,e);
		}catch(Exception e){
			e.printStackTrace();
			return new JsonResult<User>(e);
		}
	}
	@RequestMapping("/checkUserName.do")
	@ResponseBody
	boolean checkUserName(String name) throws Exception{
		User user = userService.findUserByName(name);
		if(user==null)
			return true;
		else
			return false;
	}
	//JSON: {state:0, data:{id:...}, message:null}
	//JSON: {state:1, data:null, message:名字空}
	 
}







