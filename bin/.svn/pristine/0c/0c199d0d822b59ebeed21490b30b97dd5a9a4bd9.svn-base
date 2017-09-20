package com.dayuanit.shop.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dayuanit.shop.domain.User;
import com.dayuanit.shop.dto.AjaxResultDTO;
import com.dayuanit.shop.service.UserService;
import com.dayuanit.shop.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/toRegist")
	public String toRegist() {
		return "regist";
	}
	
	
	@RequestMapping("/toLogin")
	public String toLogin(HttpServletRequest req) {
		return "login";
	}
	
	@RequestMapping("/toUserCenter")
	public String toUserCenter() {
		return "usercenter";
	}
	
	
	@RequestMapping(value="/logout",method = { RequestMethod.POST, RequestMethod.GET })  
	public String logout(HttpServletRequest req) {
		
		HttpSession sssion = req.getSession(false);
		if (null != sssion) {
			sssion.invalidate();
		}
			
		return "redirect:/index.jsp";
	}
	
	
	@RequestMapping("/login")
	@ResponseBody
	public AjaxResultDTO login(HttpServletRequest req, HttpServletResponse resp,String userName, String password, HttpSession session, String code) {
		
		try {
			
			String realCode = (String)session.getAttribute("code");
			if (!code.equals(realCode)) {
				session.removeAttribute("code");
				return AjaxResultDTO.failed("验证码错误");
			}
			User loginUser = userService.login(userName, password);
			req.getSession().setAttribute(LOGIN_FLAG, loginUser);
			
			Cookie cookie = new Cookie("userFlag", "xxxxxxxxx");
			cookie.setHttpOnly(true);
			resp.addCookie(cookie);
//			cookie.setMaxAge(60 * 60 * 24);
//			cookie.setPath("/");
//			resp.addCookie(cookie);
			
		} catch(Exception e) {
			return AjaxResultDTO.failed(e.getMessage());
		}
		return AjaxResultDTO.success();
	}
	
	
	@RequestMapping("/regist")
	@ResponseBody
	public AjaxResultDTO regist(@Validated UserVo userVo, BindingResult br, HttpSession session, String code) {
		
		String realCode = (String)session.getAttribute("code");
		if (!code.equals(realCode)) {
			session.removeAttribute("code");
			return AjaxResultDTO.failed("验证码错误");
		}
		
		
		if (br.hasErrors()) {
			String msg = "";
			List<FieldError> lfe = br.getFieldErrors();
			for (FieldError fe : lfe) {
				msg += fe.getDefaultMessage() + ",";
			}
			return AjaxResultDTO.failed(msg);
		}
		
		try {
			
			userService.regist(userVo.getUserName(), userVo.getPassword(), userVo.getCopyPassword(),userVo.getEmail());
		} catch(Exception e) {
			return AjaxResultDTO.failed(e.getMessage());
		}
		return AjaxResultDTO.success();
	}

}
