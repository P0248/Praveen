package config;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Constants {

	public static String Excel_path=System.getProperty("user.dir")+"/src/InputFiles/SSS-2221.xlsx";
	public static String Sheet_name="Test Steps";
	public static String TCSheet_name="Test Cases";
	public static String original_file=System.getProperty("user.dir")+"/src/InputFiles/SSS-2221.xlsx";
	
/*	public long startTime = System.currentTimeMillis();
    static SimpleDateFormat dateFormat1 = new SimpleDateFormat("HH:mm");
    static Calendar cal1 = Calendar.getInstance();
   public static String StartTimeHH = dateFormat1.format(cal1.getTime());*/
   
	public static String target_file=System.getProperty("user.dir")+"/src/OutputFiles/SSS-2221- "+System.currentTimeMillis()+".xlsx";
	public static String Configfile_path=System.getProperty("user.dir")+"/src/config/Config.properties";
	public static String chromedriver=System.getProperty("user.dir")+"/src/Resources/GoogleChromeDriver/chromedriver_win32/chromedriver.exe";
	public static String gecko=System.getProperty("user.dir")+"/src/Resources/Gecko/geckodriver.exe";
	public static String ie=System.getProperty("user.dir")+"/src/Resources/IE_Driver/IEDriverServer.exe";

	
}
