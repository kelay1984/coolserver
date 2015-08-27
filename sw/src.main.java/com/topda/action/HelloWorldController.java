package com.topda.action;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.topda.common.Utils;
import com.topda.coolserver.CoolServer;

@Controller
@RequestMapping("/welcome")
public class HelloWorldController {
	@Resource
	private CoolServer coolServer;

	@RequestMapping(value = "/startServer", method = RequestMethod.POST)
	public void printWelcome(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String rtnString=coolServer.registerTemperDataListenr();
			String issuccString = "false";
			response.setCharacterEncoding("utf-8");
			if(rtnString.contains("成功")){
				issuccString = "true";
			}
			JSONObject obj = new JSONObject();
			obj.put("success", issuccString);
			obj.put("msg", rtnString);
			
			response.getWriter().write(obj.toJSONString());
			response.getWriter().flush();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/stopServer", method = RequestMethod.POST)
	public void stopServer(HttpServletRequest request,
			HttpServletResponse response) {

		System.out.println("coolServer:" + coolServer);
		coolServer.removeTemperDataListenr();
		response.setCharacterEncoding("utf-8");
		try {
			response.getWriter().write("{\"success\":true }");
			response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@RequestMapping(value = "/access", method = RequestMethod.POST)
	public void access(HttpServletRequest request,
			HttpServletResponse response) {

		try {
			String issuccString = "false";
			String accessString = request.getParameter("access");
			if(coolServer.access(accessString))
				issuccString= "true";
			response.setCharacterEncoding("utf-8");
			response.getWriter().write("{\"success\":"+issuccString+" }");
			response.getWriter().flush();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/accesspage", method = RequestMethod.GET)
	public String printWelcomeaccess(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "access";
	}
	
	@RequestMapping(value = "/config", method = RequestMethod.GET)
	public String printWelcomew(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hellow";
	}

	@RequestMapping(value = "/setting", method = RequestMethod.GET)
	public String setting(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "setting";
	}
}