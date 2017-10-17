package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class FileUtil {
	
	
	public static String fileReader(File file){
		try {
			BufferedReader br = new BufferedReader(new FileReader(file)) ;
			StringBuffer sb = new StringBuffer();
			String tmp = null ;
			while((tmp = br.readLine()) != null){
				sb.append(tmp+ "\n");
			}
			return sb.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	   return "";
	}
	
	public static Map<String, String> readFile(String path, Map<String, String> contentMap) {
		File file = new File(path);
		if (file.isFile()) {
			contentMap.put(file.getAbsolutePath(),fileReader(file));
			return contentMap;
		} else {
			File[] files = file.listFiles();
			if (files == null || files.length == 0) {
				return contentMap;
			} else {
				for (File tmpFile : files) {
					if (file.isDirectory()) {
						readFile(tmpFile.getAbsolutePath(), contentMap);
					} else {
						contentMap.put(file.getAbsolutePath(), fileReader(file));
					}
				}
				return contentMap;
			}
		}
	}

}
