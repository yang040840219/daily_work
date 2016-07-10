package daily.machine;

import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test1 {
  public static void main(String []args) throws Exception{
	  String url = "jdbc:mysql://10.46.71.146:3306/db_edw_report"
	  		+ "?user=sp_edwrpt_rw&password=be%25VzR3f(o2Q&characterEncoding=utf-8" ;
	  DriverManager.getConnection(url);
  }
}
