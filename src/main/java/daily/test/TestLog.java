package daily.test;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
	
	
	public static void main(String args[]){
		
		String rawMsg = "api12 10.158.15.7 | - | [04/Aug/2016:11:47:11 +0800] | POST /rest?method=c.config.get HTTP/1.0 | 200 | 525 |- | advertising_identifier=770073d4-0ff3-45cc-98f9-fc37b3751ec4&app_ver=7.5.0&appkey=10000001&config_name_ary=client.menulist%2Chome.banner&from=appstore&from_type=app&gps_type=baidu&method=c.config.get&model=iPhone8%2C1&nonce=027feefe-d753-42b6-90c3-f93653a12140&os=iPhone%20OS9.3.3&sig=3f518e7ed748d00212de08e4bdc58173&talkingdata_id=770073D4-0FF3-45CC-98F9-FC37B3751EC4&td_appcpa_channel=appstore&timestamp=2016-08-04%2011%3A47%3A11%20GMT%2B8&token=faf075fbdbaf67efbf5ec96a710c2432&udid=71a43a1ecfe8e3252be4d35c737484a7374eb151&ver=3 | 0.093 | edaijia/750.1 (iPhone; iOS 9.3.3; Scale/2.00) | 117.136.81.120" ;
		
		String paraValue = "";
		String idfaValue = "";
        String channelValue = "";
        String osValue = "ios";
        String datetimeValue = String.valueOf(System.currentTimeMillis()/1000);
		
        String splits[] = rawMsg.split("\\|");
        if(splits.length < 7)
                System.out.println("1231");
        String url = splits[3].trim();
        String [] urlArray = url.split(" ");
        String urlMethod = urlArray[0];
        System.out.println("method:" + urlMethod);
        if(splits[3].contains("get")) {
                System.out.println("get msg:"+ rawMsg) ;
                String paraValueFake = splits[3];
                String[] restSplits = paraValueFake.split("\\?");
                if(restSplits.length != 2)
                	 	System.out.println("1232");
                paraValue = restSplits[1];
        } else if(splits[3].contains("POST")) {
                paraValue = splits[7];
        } else {
        	 	System.out.println("1233");
        }

        String[] paraPairs = paraValue.trim().split("&");
        for( String paraPair : paraPairs)
        {
                String[] keyValuePair = paraPair.split("=");
                if(keyValuePair.length != 2)
                		System.out.println("1234");
                String key = keyValuePair[0];
                if (key.equals("advertising_identifier")) {
                        idfaValue = keyValuePair[1].toUpperCase();
                } else if (key.equals("from")) {
                        channelValue = keyValuePair[1];
                }
        }
        //@SuppressWarnings("deprecation")
        //String decodeMsg = URLDecoder.decode(paraValue);
        if(!idfaValue.equals(""))
        {
           	System.out.println("1235");
        }
        System.out.println("idfaValue:"+idfaValue + "  channelValue:"+channelValue + " osValue:"+ osValue);
		
	}
}
