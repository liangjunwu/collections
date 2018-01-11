package com.ljw.ssme.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ljw.ssme.beans.common.Json;
import com.ljw.ssme.beans.pojo.User;
import com.ljw.ssme.service.UserService;
import com.ljw.ssme.utils.CaptchaUtil;

@Controller
@RequestMapping("user/")
public class LoginController {
	
	private Logger logger = Logger.getLogger(LoginController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	@ResponseBody
	public Object login(String username, String password, String verifyCode, HttpSession session){
		logger.info("username:" + username + ":: password:" + password + ":: verifyCode:" + verifyCode);
		Json json = new Json();
		if(CaptchaUtil.verify(verifyCode)){
			User user = userService.checkIn(username, DigestUtils.md5Hex(password)); //密文比对
			if(user == null){  //用户名或密码错误
				json.setStatus(-2); //y
				json.setSuccess(false); //失败
				json.setObj("用户或密码错误");
			}else{ //成功
				session.setAttribute("user_info", user);
				json.setStatus(1); //y
				json.setSuccess(true); //失败
				json.setObj("success");
			}
		}else{
			json.setStatus(-1); //验证码错误
			json.setSuccess(false); //失败
			json.setObj("验证码错误");
		}
		return json;
	}
	
	@RequestMapping("toIndex")
	public String toIndex(){
		return "layout/main";
	}
	
	@RequestMapping("verify")
	public void getVerifyCode(HttpServletResponse response) {
		try {
			CaptchaUtil.create(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
