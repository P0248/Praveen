package excel_utility;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;

public class Excel 
{

	public static XSSFWorkbook wb;
	public static XSSFSheet sheet;
	public static XSSFRow row;
	public static XSSFCell cellvalue;
	public static String NewExcel;
	public static XSSFSheet ExcelWSheet;
	
	public static String readExcel(int rownum,int cellnum,String sheetname) throws FileNotFoundException     //For Suite execution
																											//added sheetname parameter
	{
		try{
		
			
			FileInputStream excel=new FileInputStream(Constants.Excel_path);
			wb=new XSSFWorkbook(excel);
			sheet=wb.getSheet(sheetname);
			row=sheet.getRow(rownum);
			cellvalue=row.getCell(cellnum);
						
			if(cellvalue==null)
					{
				return "";
					}
			if(cellvalue.getCellType()==cellvalue.CELL_TYPE_BLANK)
			{
				return "";
			}
			
			if(cellvalue.getCellType()==cellvalue.CELL_TYPE_NUMERIC)
			{
				return String.valueOf(cellvalue);			}
			else
				{
				return cellvalue.getStringCellValue();
				}
			
			
		}
		catch(Exception e)
		
		{
			System.out.println("unable to read Excel");
		}
		return null;
		
		
	}
	
	public static void copyExcel() throws IOException
	{
		
		try{
			FileSystem system=FileSystems.getDefault();
		
		Path original=system.getPath(Constants.original_file);
		Path target=system.getPath(Constants.target_file);
		Files.copy(original,target,StandardCopyOption.REPLACE_EXISTING);
		NewExcel=target.toString();
		}
		catch(Exception e)
		{
			System.out.println("Unable to copy Excel");
		}
		
	}
	
	public static int lastRow(String sheetname) throws IOException              //added parameter
	{
		try{
			FileInputStream excel=new FileInputStream(Constants.Excel_path);
		
		wb=new XSSFWorkbook(excel);
		sheet=wb.getSheet(sheetname);
		int lastrow=sheet.getLastRowNum();
		return lastrow;
	}
	catch(Exception e)
		{
		System.out.println("unable to find last row");
		}
		return 0;
	
	}
	
	public static void setExcel(int rownum,int cellnum, String result) throws IOException  //added parameter
	{
		try{
			FileInputStream excel=new FileInputStream(NewExcel);
		
		wb=new XSSFWorkbook(excel);
		sheet=wb.getSheet(Constants.Sheet_name);
		row=sheet.getRow(rownum);
		cellvalue=row.getCell(cellnum);
		if(cellvalue==null)
		{
			cellvalue=row.createCell(cellnum);
			cellvalue.setCellValue(result);
		}
		else
			cellvalue.setCellValue(result);
		
		
		FileOutputStream excelnew=new FileOutputStream(NewExcel);
		wb.write(excelnew);
		excelnew.flush();
		excelnew.close();
		}
		catch(Exception e)
		{
			System.out.println("Unable to write into excel");
		}
		
	}
	
	
	public static int rowContains(String testcaseID,int cellnum,String sheetname) throws IOException       //Created the method
	{
		FileInputStream excel=new FileInputStream(Constants.Excel_path);
		wb=new XSSFWorkbook(excel);
		sheet=wb.getSheet(sheetname);
		//int lastrow=excel_utility.Excel.
		
		int lastrow=excel_utility.Excel.lastRow(sheetname);
		int i;
		for(i=0;i<lastrow;i++)
		{
			if(excel_utility.Excel.readExcel(i, cellnum, sheetname).equalsIgnoreCase(testcaseID))
			break;
		}
		return i;
	}
	
	
	public static int stepsCount(String testcaseID,String sheetname,int TestcaseStart) throws IOException
	{
		for(int i=TestcaseStart;i<excel_utility.Excel.lastRow(sheetname);i++)
		{
			if(!testcaseID.equals(excel_utility.Excel.readExcel(i, 1, sheetname))){
				int number=i;
				return number;
			}
		}
		int number=excel_utility.Excel.lastRow(sheetname);
				
		return number;
	}
	
	
	
}

