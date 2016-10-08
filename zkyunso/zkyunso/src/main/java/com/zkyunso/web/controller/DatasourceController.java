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
@RequestMapping("/datasource")
public class DatasourceController {
	/**
	 * 初始化
	 * @param User
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/init", produces = "text/plain;charset=UTF-8")
	public String init() {// 2
		return "result";// 返回首页
	}
	/**
	 * 新增数据源
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/add", produces = "text/plain;charset=UTF-8")
	public String add() {// 2
		return "result";// 返回首页
	}
	@ResponseBody
	@RequestMapping(value = "/update/dsInfo", produces = "text/plain;charset=UTF-8")
	public String updateInfo() {// 2
		return "result";// 返回首页
	}
	@ResponseBody
	@RequestMapping(value = "/update/dsDetails", produces = "text/plain;charset=UTF-8")
	public String updateDetails() {// 2
		return "result";// 返回首页
	}
}
