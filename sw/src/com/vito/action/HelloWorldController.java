package com.vito.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcome")
public class HelloWorldController {
	@RequestMapping(value="/hello",method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hello";
	}
	
	@RequestMapping(value="/hellow",method = RequestMethod.GET)
	public String printWelcomew(ModelMap model) {
		model.addAttribute("message", "Spring 3 MVC Hello World");
		return "hellow";
	}
}