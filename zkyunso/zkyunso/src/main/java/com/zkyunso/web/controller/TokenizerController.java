package com.zkyunso.web.controller;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/tokenizer")
public class TokenizerController {
	@ResponseBody
	@RequestMapping(value = "/seg", produces = "text/plain;charset=UTF-8")
	public String seg(String text) {// 2
		return "result";// 返回首页
	}
}
