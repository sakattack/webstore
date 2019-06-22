package com.packt.webstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class HomeController {

	// RedirectAttributes are needed in order to pass attributes to the redirect
	// view. not needed when forward is used instead
	@RequestMapping
	public String welcome(Model model, RedirectAttributes redirectAttributes) {
		model.addAttribute("greeting", "Welcome to Web Store!");
		model.addAttribute("tagline", "The one and only amazing web store");
		redirectAttributes.addFlashAttribute("greeting", "Welcome to Web Store!"); // flash attributes are used when we
																					// want to pass attributes from this
																					// request to a redirect
		redirectAttributes.addFlashAttribute("tagline", "The one and only amazing web store");
		return "redirect:/welcome/greeting";
	}

//  SAME AS ABOVE (not good cause of tight coupling with InternalResourceView)
//	@RequestMapping
//	public ModelAndView welcome(Map<String, Object> model) {
//		model.put("greeting", "Welcome to Web Store!");
//		model.put("tagline", "The one and only amazing web store");
//		View view = new InternalResourceView("/WEB-INF/views/welcome.jsp");
//		return new ModelAndView(view, model);
//	}

	@RequestMapping("/welcome/greeting")
	public String greeting() {
		return "welcome";
	}

}