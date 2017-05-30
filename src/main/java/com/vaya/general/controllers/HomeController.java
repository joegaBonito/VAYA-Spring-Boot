package com.vaya.general.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
	@RequestMapping("/")
	public String home(Model model){
		return "index";
	}
	@RequestMapping("/error/error401")
    public String error401() {
        return "/error/error401";
    }
	@RequestMapping("/error/error403")
    public String error403() {
        return "/error/error403";
    }
	@RequestMapping("/error/error404")
    public String error404() {
        return "/error/error404";
    }
	@RequestMapping("/error/error500")
    public String error500() {
        return "/error/error500";
    }
}
