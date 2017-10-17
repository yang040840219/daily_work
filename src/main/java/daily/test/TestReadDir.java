package daily.test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import com.util.FileUtil;

public class TestReadDir {



	public static void main(String[] args) {

		String path = "/Users/yxl/yunniao/source/beeper_data_warehouse/job/sql";
		File file = new File(path);
		Map<String, String> map = new LinkedHashMap<String, String>();
		FileUtil.readFile(path, map);

		System.out.println(map.size());
		for (String key : map.keySet()) {
			System.out.println(map.get(key));
			System.out.println("-----------------");
		}

	}

}
