package com.zkyunso.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.zkyunso.db.utils.DBProperty;
import com.zkyunso.sys.SysUtil;

@Controller
@RequestMapping("/fileimport")
public class FileimportController {
	@RequestMapping(value = "/index")
	public String index() {// 2
		return "filesUpload";// 返回首页
	}

	@ResponseBody
	@RequestMapping(value = "/upload")
	public String upload(@RequestParam("files") MultipartFile[] files) throws IOException {// 2
		// 判断file数组不能为空并且长度大于0
		if (files != null && files.length > 0) {
			// 循环获取file数组中得文件
			for (int i = 0; i < files.length; i++) {
				MultipartFile file = files[i];
				// 保存文件
				saveFile(file);
			}
		}
		return "true";// 返回首页
	}

	/***
	 * 保存文件
	 * 
	 * @param file
	 * @return
	 */
	private boolean saveFile(MultipartFile file) {
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				String filePath = SysUtil.uploadRootDir
						+ file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/name/{id}", produces = "text/plain;charset=UTF-8")
	public String name(@PathVariable("id") Integer id) {// 2
		String src = SysUtil.confRootDir + id + "\\name.json";
		String jsonStr = SysUtil.readFromFile(src);
		return jsonStr;// 返回首页
	}

	@ResponseBody
	@RequestMapping(value = "/tokenizer/{id}", produces = "text/plain;charset=UTF-8")
	public String tokenizer(@PathVariable("id") Integer id) {// 2
		String src = SysUtil.confRootDir + id + "\\tokenizer.json";
		String jsonStr = SysUtil.readFromFile(src);
		System.out.println(jsonStr);
		return jsonStr;// 返回首页
	}
}
