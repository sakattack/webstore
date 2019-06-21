package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping
	public String welcome(Model model) {
		model.addAttribute("greeting", "Welcome to Web Store!");
		model.addAttribute("tagline", "The one and only amazing web store");
		return "welcome";
	}

//  SAME AS ABOVE (not good cause of tight coupling with InternalResourceView)
//	@RequestMapping
//	public ModelAndView welcome(Map<String, Object> model) {
//		model.put("greeting", "Welcome to Web Store!");
//		model.put("tagline", "The one and only amazing web store");
//		View view = new InternalResourceView("/WEB-INF/views/welcome.jsp");
//		return new ModelAndView(view, model);
//	}

}