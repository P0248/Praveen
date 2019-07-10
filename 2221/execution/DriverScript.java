package execution;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

//import org.apache.log4j.xml.DOMConfigurator;

import config.ActionKeys;
import config.Constants;
import excel_utility.Excel;


public class DriverScript 
{

	public static String Keyword;
	public static String Object;
	public static String Data;
	
	public static Method[] method;
	static ActionKeys obj=new ActionKeys();
	public static Properties Config_properties;
	public static String testcaseID;
	public static String runmode;
	public static int teststep;
	public static int testlaststep;

	
	
	public static void main(String args[]) throws Exception
	{
				
		DOMConfigurator.configure("log4j.xml");
		FileInputStream config_file=new FileInputStream(Constants.Configfile_path);
		Config_properties=new Properties();
		Config_properties.load(config_file);   //loading config elements
		
		method = obj.getClass().getMethods();  // loading methods
		
		Excel.copyExcel();
		executeTestcases();
	
	}
		
	public static void executeTestcases() throws Exception
		{
		
		
		
		
		int totalcases=excel_utility.Excel.lastRow(Constants.TCSheet_name);
		
		for(int testcase=1;testcase<=totalcases;testcase++)
		{
			testcaseID=excel_utility.Excel.readExcel(testcase, 0, Constants.TCSheet_name);
			runmode=excel_utility.Excel.readExcel(testcase, 2, Constants.TCSheet_name);
			//System.out.println(testcaseID);
			//System.out.println(runmode);
			
			if(runmode.equals("Yes"))
			{
				teststep=excel_utility.Excel.rowContains(testcaseID, 1, Constants.Sheet_name);
				testlaststep=excel_utility.Excel.stepsCount(testcaseID, Constants.Sheet_name, teststep);
				for(;teststep<=testlaststep;teststep++)
				{
					Keyword=Excel.readExcel(teststep, 3,Constants.Sheet_name);
					Object=Excel.readExcel(teststep, 4,Constants.Sheet_name);
					Data=Excel.readExcel(teststep, 5,Constants.Sheet_name);
					//System.out.println("teststep"+teststep);
					//System.out.println("testlaststep"+testlaststep);
					//System.out.println(Keyword);
					//System.out.println(Object);
					//System.out.println(Data);
					executeActions(teststep,Keyword,Object,Data);
				}
				
				
			}
		
			
		}
		
		
		//for(int i=1;i<Excel.lastRow();i++)
		
			
		
		
	
		//executeActions(i,Keyword,Object,Data);
		
		}
	

	private static void executeActions(int i,String Keyword,String Object,String Data) throws Exception
	{
	
		for(int j=0; j<=method.length;j++)
		{
		
			if(method[j].getName().equals(Keyword))
				{
						try		{
							//System.out.println(Keyword+Object+Data);
							method[j].invoke(obj,i,Object,Data);
								}
						catch(Exception e)
								{
									System.out.println("no method");
								}
						break;
				}
		
		}
	}
	
	
}
