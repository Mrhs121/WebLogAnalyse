import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.directory.api.util.Strings;

import com.hs.webloganalyse.bean.WebLogBean;
import com.hs.webloganalyse.utils.WebLogParser;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;


public class WebLogParserTest {
	
	public static void main(String[] args) throws IOException {
		
		
		BufferedReader reader = new BufferedReader(new FileReader(new File("I:\\weblog.txt")));
		String line = reader.readLine();
		String[] strings = line.split(" ");
		StringBuilder stringBuilder = new StringBuilder();
		System.out.println(strings.length);
		for(int i=0;i<strings.length;i++){
			System.out.println(i+" "+strings[i]);
		}
		for(int j=8;j<strings.length;j++){
			stringBuilder.append(strings[j]+" ");
		}
		System.out.println(stringBuilder);
//		System.out.println(line);
//		WebLogParser.parserBrowser(line);
		//WebLogBean logBean = WebLogParser.parser(line);
//		String ua = WebLogParser.parserBrowser(line);
//		System.out.println("ua -- "+ua);
//		if (ua.equals("")) {
//			System.out.println("eee");
//			return;
//		}
//		System.out.println("ua : "+ua);
//		
		UserAgent userAgent = UserAgent.parseUserAgentString(stringBuilder.toString());  
	       Browser browser = userAgent.getBrowser();  
	       
	       OperatingSystem os = userAgent.getOperatingSystem();
	       
	       System.out.println(browser.getName()+" "+os.getName());
	       
//	       
//		Pattern pattern = Pattern.compile(";\\s?(\\S*?\\s?\\S*?)\\s?(Build)?/");  
//        
//		
//		
//		Matcher matcher = pattern.matcher(line);  
//        String model = null;  
//        if (matcher.find()) {  
//            model = matcher.group(1).trim();  
//            //log.debug("通过userAgent解析出机型：" + model);  
//            System.out.println(model);
//        }  else {
//        	System.out.println("no");
//        }
        	
//		String[] data = line.split(" ");
//		for(int i=0;i<line.split(" ").length;i++){
//			System.out.println(data[i]);
//		}
//		
//		System.out.println(line);
//		WebLogBean reString = WebLogParser.parser(line);
//		System.out.println("-----------------------------------------");
//		if (reString == null) {
//			return;
//		}
//		System.out.println(reString.toString());
//		System.out.println(reString.getBrowser());
		
//		
//		System.out.println("-----------------------------------------\n\n");
//		
//		String line2 = reader.readLine();
//		System.out.println(line2);
//		System.out.println("---> "+line.split(" ").length);
//		String reString2 = WebLogParser.parser(line2);
//		System.out.println("-----------------------------------------");
//		System.out.println(reString2);
	}
}
