package com.topda.action;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.topda.coolserver.CoolServer;

@Controller
@RequestMapping("/welcome")
public class HelloWorldController {
	@Resource
	private CoolServer coolServer;
	
	@RequestMapping(value="/startServer",method = RequestMethod.POST)
	public void printWelcome(HttpServletRequest request, HttpServletResponse response) {
		String roomid= request.getParameter("roomid");
		  String room= request.getParameter("room");
		  System.out.println("coolServer:"+coolServer);
		  coolServer.registerTemperDataListenr();
		  
		  System.out.println("session set:"+room+"=="+roomid);
		  response.setCharacterEncoding("utf-8");
		  try {
			response.getWriter().write("{\"success\":true }");
			 response.getWriter().flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	}
	
	@RequestMapping(value="/stopServer",method = RequestMethod.POST)
	public void stopServer(HttpServletRequest request, HttpServletResponse response) {

		  System.out.println("coolServer:"+coolServer);
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
	
	@RequestMapping(value="/hellow",method = RequestMethod.GET)
	public String printWelcomew(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hellow";
	}
	
	@RequestMapping(value="/setting",method = RequestMethod.GET)
	public String setting(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "setting";
	}
}