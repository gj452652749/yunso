package com.tool.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVReader;

public class CSVHandler {

	public String[] getHead(String src) throws IOException {
		FileReader fReader = new FileReader(new File(src));
		CSVReader csvReader = new CSVReader(fReader);
		String[] strs = csvReader.readNext();
		if (strs != null && strs.length > 0) {
			for (String str : strs)
				if (null != str && !str.equals(""))
					System.out.print(str + " , ");
			System.out.println("\n---------------");
		}
		return strs;
	}

	public void readCsv(String src) throws Exception {
		FileReader fReader = new FileReader(new File(src));
		CSVReader csvReader = new CSVReader(fReader);
		List<String[]> list = csvReader.readAll();
		for (String[] ss : list) {
			for (String s : ss)
				if (null != s && !s.equals(""))
					System.out.print(s + " , ");
			System.out.println();
		}
		csvReader.close();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CSVHandler csvHandler = new CSVHandler();
		try {
			csvHandler
					.readCsv("E:\\workplace\\server\\tomcat7\\wtpwebapps\\SpringMVC_01\\upload\\ch.xls");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
