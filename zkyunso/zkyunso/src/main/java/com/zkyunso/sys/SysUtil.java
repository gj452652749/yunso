package com.zkyunso.sys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/*
 * 主要是初始化系统参数
 */
public class SysUtil {
	static public String confRootDir;// 配置文件路径
	static public String uploadRootDir;// 配置文件路径
	static {
		Properties prop = new Properties();
		try {
			prop.load(SysUtil.class.getClassLoader().getResourceAsStream(
					"sys.properties"));
			confRootDir = prop.getProperty("conf.root.dir");
			uploadRootDir = prop.getProperty("upload.root.dir");
			System.out.println(confRootDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 输入文件名，返回文件内容
	public static String readFromFile(String src) {
		String str = "";
		try {
			// BufferedReader bufferedReader = new BufferedReader(new
			// FileReader(new File(src)));
			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(new FileInputStream(src), "utf-8"));
			String content;
			while ((content = bufferedReader.readLine()) != null) {
				str = str + content + "\r\n";

			}
			bufferedReader.close();
			return str;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	// 输入字符串，文件名，将字符串转换成文件
	public static String stringToTxt(String s, String path) {

		try {
			File f = new File(path);
			if (f.exists()) {
				System.out.print("文件存在");
			} else {
				System.out.print("文件不存");
				f.createNewFile();// 不存在则创建
			}
			BufferedWriter output = new BufferedWriter(new FileWriter(f));
			output.write(s);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return path;

	}
}
