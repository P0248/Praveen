package config;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import excel_utility.Excel;
import excel_utility.Log;
import execution.DriverScript;

public class ActionKeys 

{
	public static WebDriver driver;
	static WebDriverWait wait;
	private String parent;
	//public String activity_select_text_verify;
	
	public static int product_select;
	public static int course_link_portal;
	public static int category_link_portal;
	
	public By setLocator(String testobj)
	{
		String type=testobj.split("\\|")[0];
		String value=testobj.split("\\|")[1];
		By resultvalue=null;
		
			switch(type)
			{
			case "id":
				resultvalue=By.id(value);
				break;
			case "name":
				resultvalue=By.name(value);
				break;
			case "xpath":
				resultvalue=By.xpath(value);
				break;
			case "linkText":
				resultvalue=By.linkText(value);
			case "prtLinktext":
				
				resultvalue=By.partialLinkText(value);
				break;
			}
			return resultvalue;
	}
	
	
	@Test
	public static void openURLChrome(int i,String testobj,String testdata) throws Exception
	 {  
		try {
			
			Log.info("Opening Browser");
			System.setProperty("webdriver.chrome.driver", Constants.chromedriver); 
		driver=new ChromeDriver();
		wait=new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(testdata);  
		Excel.setExcel(i, 6, "PASS");
	 }
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter correct details");
			Excel.setExcel(i, 6, "FAIL");
		}
	}
	public void openURLFF(int i,String testobj,String testdata) throws Exception
	 {  
		try {
			
		System.setProperty("webdriver.gecko.driver", Constants.gecko); 
		DesiredCapabilities capabilities=DesiredCapabilities.firefox();
    	capabilities.setCapability("marionette", true);
    	driver =new FirefoxDriver(capabilities);
		wait=new WebDriverWait(driver,10);
	
		//driver.manage().window().maximize();
		driver.get(testdata);
		driver.manage().timeouts().implicitlyWait(40,TimeUnit.SECONDS);
		Excel.setExcel(i, 6, "PASS");
	 }
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter correct details");
			Excel.setExcel(i, 6, "FAIL");
		}
	}
	public void openURLIE(int i,String testobj,String testdata) throws Exception
	 {  
		try {
		
		System.setProperty("webdriver.ie.driver", Constants.ie); 
		driver=new InternetExplorerDriver();
		wait=new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(testdata);  
		Excel.setExcel(i, 6, "PASS");
	 }
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter correct details");
			Excel.setExcel(i, 6, "FAIL");
		}
	}
	
	public void input(int i,String Object,String Data) throws Exception
	{
		try {
			Log.info("Entering the text " + Data);
			
			Thread.sleep(2000);
			//System.out.println(Object+Data);
			//System.out.println(setLocator(DriverScript.Config_properties.getProperty(Object)));
			driver.findElement(setLocator(DriverScript.Config_properties.getProperty(Object))).sendKeys(Data);
			Excel.setExcel(i, 6, "PASS");
		} catch (Exception e) {
			Thread.sleep(4000);
			e.printStackTrace();
			Log.warn("unable to click"+ Data);
			Log.error("exception is "+e);
			System.out.println("Unable to enter details--"+ Data);
			Excel.setExcel(i, 6, "FAIL");
			Excel.setExcel(i, 7, "Unable to enter details--"+ Data);
		}
		
	}
	

	public void inputuser(int i,String testobj,String testdata) throws Exception {
		driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))).sendKeys("05050339");
		Excel.setExcel(i, 6, "PASS");
		
	}

	public void click(int i,String testobj,String testdata) throws Exception {
	try {
		
		Log.info((Excel.readExcel(i, 2, "Test Steps"))+"clicked");
		driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))).click(); 
		Excel.setExcel(i, 6, "PASS");
		
		Thread.sleep(2000);
	} catch (Exception e) {
		e.printStackTrace();
		Log.warn("unable to click");
		System.out.println("Unable to click the element");
		Excel.setExcel(i, 6, "FAIL");
		Excel.setExcel(i, 7, "Unable to click the element");
	}
	
	}
	
	public void submit(int i,String testobj,String testdata) throws Exception {
		try {
			driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))).submit();
			Excel.setExcel(i, 6, "PASS");
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to submit the element");
			Excel.setExcel(i, 6, "FAIL");
			Excel.setExcel(i, 7, "Unable to submit the element");
		}
		
		}
	
	

	public void select(int i,String testobj,String testdata) throws Exception {
		try {new Select(driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)))).selectByVisibleText(testdata);
		Excel.setExcel(i, 6, "PASS");
		Thread.sleep(2000);
		}
		catch (Exception e) {
			Thread.sleep(4000);
			e.printStackTrace();
			System.out.println("Unable to select");
			Excel.setExcel(i, 6, "FAIL");
		}
	}
	
	public void PageloadTimeout(int i,String testobj,String testdata) throws Exception
	{
		try
		{
			driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
			Excel.setExcel(i, 6, "PASS");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter correct details");
			Excel.setExcel(i, 6, "FAIL");
		}
	}
		
	public void waitText(int i,String testobj,String testdata) throws Exception
	{
		try {
			wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))), testdata));
			Excel.setExcel(i, 6, "PASS");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter correct details");
			Excel.setExcel(i, 6, "FAIL");
		}
	}

	public void waitURL(int i,String testobj,String testdata) throws Exception
	{
		try{
			wait.until(ExpectedConditions.urlContains(testdata));
			Excel.setExcel(i, 6, "PASS");
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("enter correct details");
			Excel.setExcel(i, 6, "FAIL");
		}
	}
	
	
	public void clear(int i,String testobj,String testdata) throws Exception
	{
		try {
			Thread.sleep(2000);
			driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))).clear();
			Excel.setExcel(i, 6, "PASS");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to clear the data under the field field");
			Excel.setExcel(i, 6, "FAIL");
			Excel.setExcel(i, 7, "Unable to clear the data under the field field");
		}
		
	}

	public void verifyText(int i,String testobj, String testdata) throws Exception {
	
		try {
			//wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))), testdata));
		
		String element=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))).getText();
		
		Excel.setExcel(i, 6, "PASS");
		Excel.setExcel(i, 7, element);}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to verify "+testdata);
			Excel.setExcel(i, 6, "FAIL");
			Excel.setExcel(i, 7, "Unable to verify "+testdata);
		}
		
		
		
	}
	
	public void switchToWindow(int i,String testobj, String testdata) throws Exception {
		
		try{
			this.parent=driver.getWindowHandle();
			System.out.println("parent id in child"+ this.parent);
		Set <String> windows= driver.getWindowHandles();
		for(String windowhandle:windows)
		{
			//Excel.setExcel(i, 6, "Parentwindow");
			if(!windowhandle.equals(this.parent))
		{
			driver.switchTo().window(windowhandle);
			
			
			
			
			
			//driver.close();
		//	driver.switchTo().window(parent);
			Excel.setExcel(i, 6, "PASS");
		}
		}}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable to switch window");
			Excel.setExcel(i, 6, "FAIL");
			Excel.setExcel(i, 6, "Unable to switch window");
		}
		
		
		
		
	}
	
	public void driverClose(int i,String testobj, String testdata) throws Exception 
	{
			
		try{
			driver.close();
			driver.quit();
			Excel.setExcel(i, 6, "PASS");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			Thread.sleep(3000);
			//driver.close();
			Excel.setExcel(i, 6, "FAIL");
		}
	}
	
public void switchToParent(int i,String testobj, String testdata) throws Exception 
{
		
	try{
		System.out.println("parent id in parent"+ this.parent);
		driver.switchTo().window(this.parent);
		Excel.setExcel(i, 6, "PASS");
	}
	catch(Exception e)
	{
		e.printStackTrace();
		Thread.sleep(3000);
		String parent2=driver.getWindowHandle();
		driver.switchTo().window(parent2);
		Excel.setExcel(i, 6, "FAIL");
	}
}
	
	
	
	public void imageUpload(int i,String testobj,String testdata)
	{
		
				driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))).sendKeys(System.getProperty("user.dir")+"/Resource/"+testdata);
	}
	public void imageUpload2(int i,String testobj,String testdata) throws Exception
	{
		try{
		Runtime.getRuntime().exec(testdata);
		Thread.sleep(3000);
	}
	catch (Exception e) {
		e.printStackTrace();
		System.out.println("incorrect value of "+ testdata+ "  for the field");
		Excel.setExcel(i, 6, "FAIL");
		
	}}
		
	public void linkText(int i,String testobj,String testdata) throws Exception
	{
		try {
			
			driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)+testdata)).click();
			Thread.sleep(2000);
			Excel.setExcel(i, 6, "PASS");
		} catch (IOException e) {
			e.printStackTrace();
			Thread.sleep(2000);
			driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)+testdata)).click();
			System.out.println("Unable to click the link");
			Excel.setExcel(i, 6, "FAIL from catch IO");
			Excel.setExcel(i, 7, "Unable to click the link");
		}
		catch (Exception e1) {
			e1.printStackTrace();
			driver.navigate().refresh();
			driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)+testdata)).click();
			System.out.println("Unable to click the link");
			Excel.setExcel(i, 6, "FAIL from exception");
			Excel.setExcel(i, 7, "Unable to click the link");
		}
		
		
		
	
	
	
	}
	
		public void waitClickable(int i,String testobj,String testdata) throws Exception
		{
			try {
				
			
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.elementToBeClickable(setLocator(DriverScript.Config_properties.getProperty(testobj))));
				
				
				Excel.setExcel(i, 6, "PASS");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Unable to find the element");
				Excel.setExcel(i, 6, "FAIL");
				
			}
	
	
	}
		
		public void waitNotClickable(int i,String testobj,String testdata) throws Exception
		{
			try {
				
			
				WebDriverWait wait = new WebDriverWait(driver, 10);
				wait.until(ExpectedConditions.invisibilityOfElementLocated(setLocator(DriverScript.Config_properties.getProperty(testobj))));
				
				
				Excel.setExcel(i, 6, "PASS");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Element is present,need more time to load page");
				Excel.setExcel(i, 6, "FAIL");
				Excel.setExcel(i, 7, "Element is present,need more time to load page");
			}
	
	
	}
		
		public void switchToFrame(int i,String testobj,String testdata) throws Exception
		{
			try {
				
			
				driver.switchTo().frame(driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))));
				//driver.switchTo().frame(testdata);
				Excel.setExcel(i, 6, "PASS");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Unable to switch to the frame");
				Excel.setExcel(i, 6, "FAIL");
				
			}
	
	
	}
		
		public void switchToParentFrame(int i,String testobj,String testdata) throws Exception
		{
			try {
				
				
				driver.switchTo().parentFrame();
				Excel.setExcel(i, 6, "PASS");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("unable to click on the frAME");
				Excel.setExcel(i, 6, "FAIL");
				
			}
	
	
	}
		

		public void switchToDefault(int i,String testobj,String testdata) throws Exception
		{
			try {
				
				
				driver.switchTo().defaultContent();
				Excel.setExcel(i, 6, "PASS");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("unable to click on the frAME");
				Excel.setExcel(i, 6, "FAIL");
				
			}
	
	
	}
		
		
		
		
		//}
			public void threadSleep(int i,String testobj,String testdata) throws Exception
			{
				Thread.sleep(3000);
				Excel.setExcel(i, 6, "PASS");
			}
			public void threadLongSleep(int i,String testobj,String testdata) throws Exception
			{
				Thread.sleep(10000);
				Excel.setExcel(i, 6, "PASS");
			}
			
			public void dragAnddrop(int i,String testobj,String testdata) throws Exception
			{
			try{
				System.out.println("before drag");
				WebElement source=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
					//Actions drag=new Actions(driver);
					
					  WebElement rect=driver.findElement(By.id("iconRectangle"));
		             //   WebElement imgarea=driver.findElement(By.xpath(MainThread.CONFIG.getProperty("Detection_Image_Area")));
		                //Actions action=new Actions(driver);
		               // action.clickAndHold(rect).build().perform();
		                //action.moveToElement(imgarea);
		               // action.dragAndDrop(rect, source).build().perform();
		              
		                switchToParentFrame(i, testobj, testdata);
		                JavascriptExecutor jse = (JavascriptExecutor)driver;
		                jse.executeScript("window.scrollBy(0,250)", "");
		                switchToFrame(i, "xpath|//div[@id='imgDiv']/iframe", testdata);
		                Actions actionBuilder=new Actions(driver);          
		                actionBuilder.click(rect).
		                moveToElement(source).
		                clickAndHold().
		                moveByOffset(230,40).
		                release().perform();
		                Thread.sleep(3000);
		                actionBuilder.click(rect).
		                moveToElement(source).
		                clickAndHold().
		                moveByOffset(50,100).
		                release().perform();
		                Thread.sleep(3000);
				
					
				//drag.dragAndDrop(source, source);
					
				//drag.dragAndDropBy(driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))), 230, 40).perform();
				//drag.contextClick().perform();
				System.out.println("after drag");
				Thread.sleep(3000);
				//(new Actions(driver)).dragAndDrop(source, source).perform();
				Thread.sleep(3000);
				Excel.setExcel(i, 6, "PASS");
			}
			
			catch(Exception e){
				e.printStackTrace();
				System.out.println("under drag catch");
				WebElement source=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
				Thread.sleep(3000);
				(new Actions(driver)).dragAndDrop(source, source).perform();
				Excel.setExcel(i, 6, "FAIL");
			}
			}
			
			public void alertDismiss(int i,String testobj,String testdata) throws Exception
			{
				
				try{
					driver.switchTo().alert().dismiss();
					
					
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("No alert present");
					Excel.setExcel(i, 6, "FAIL");
				}
				
				
			}
			
			public void alertAccept(int i,String testobj,String testdata) throws Exception
			{
				
				try{
					//driver.switchTo().alert().accept();
					Alert alert=driver.switchTo().alert();
                    Thread.sleep(2000);
                    alert.accept();
                    Excel.setExcel(i, 6, "PASS");
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("No alert present");
					Excel.setExcel(i, 6, "FAIL");
				}
				
				
			}
			public void alertText(int i,String testobj,String testdata) throws Exception
			{
				
				try{
					//driver.switchTo().alert().accept();
					Alert alert=driver.switchTo().alert();
                 
                    String str=alert.getText();
                    Excel.setExcel(i, 7, str);
                   alert.accept();
                 
                    Excel.setExcel(i, 6, "PASS");
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
					System.out.println("No alert present");
					Excel.setExcel(i, 6, "FAIL");
				}
				
				
			}
			
				public void scrollRight(int i,String testobj,String testdata) throws Exception
				{
					
					try{
						((JavascriptExecutor)driver).executeScript("window.scrollBy(2000,0)");
						Excel.setExcel(i, 6, "PASS");
						
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("Unable to scroll");
						Excel.setExcel(i, 6, "FAIL");
					}
					
					
				}
				public void scrollDown(int i,String testobj,String testdata) throws Exception
				{
					
					try{
						((JavascriptExecutor)driver).executeScript("window.scrollBy(0,250)", "");
						
						Excel.setExcel(i, 6, "PASS");
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("Unable to scroll");
						Excel.setExcel(i, 6, "FAIL");
					}
					
					
				}
				public void scrollLeft(int i,String testobj,String testdata) throws Exception
				{
					
					try{
						((JavascriptExecutor)driver).executeScript("window.scrollBy(-2000,0)");
						
						Excel.setExcel(i, 6, "PASS");
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("No alert present");
						Excel.setExcel(i, 6, "FAIL");
					}
					
					
				}

				

	
				
				public void newTab(int i,String testobj,String testdata) throws Exception
				{
					
					try{
				
						//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"\t");
						Thread.sleep(3000);
						ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					    driver.switchTo().window(tabs2.get(1));
					   // driver.close();
					   // driver.switchTo().window(tabs2.get(0));
					    Excel.setExcel(i, 6, "PASS");
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("Unable to open new tab");
						Excel.setExcel(i, 6, "FAIL");
					}
				
				}
				public void newTab2(int i,String testobj,String testdata) throws Exception
				{
					
					try{
				
						
						
						//driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
						Thread.sleep(3000);
						//ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
					 //  driver.switchTo().window(tabs2.get(1));
					   // driver.close();
					   // driver.switchTo().window(tabs2.get(0));
					    Excel.setExcel(i, 6, "PASS");
					    driver.navigate().to("https://10.200.0.20/radexam/");
					    
					    
					    System.out.println("under new tab");
					    Thread.sleep(20000);
					}
					
					catch(Exception e)
					{
						e.printStackTrace();
						System.out.println("Unable to open new tab");
						Excel.setExcel(i, 6, "FAIL");
					}
				
				}
				
				




public void dragScroll(int i,String testobj,String testdata) throws Exception
{
	try {
		
		//driver.findElement(By.linkText("Update RadExam Calculations")).click();
		WebElement target=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
		//WebElement source=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
		Actions actionBuilder=new Actions(driver);          
		System.out.println("starting scroll");
		actionBuilder.clickAndHold(target).moveByOffset(0,-5000).    
		//click(target).
		//moveToElement(source
		//clickAndHold().
		//moveByOffset(5000,0).
		release().perform();
		actionBuilder.clickAndHold(target).moveByOffset(0,5000).release().perform();             //execution correctly
		actionBuilder.clickAndHold(target).moveByOffset(5000,5000).release().perform();
		Thread.sleep(3000);
		
		
		System.out.println("during scroll");
		Excel.setExcel(i, 6, "PASS");
		//driver.findElement(By.linkText("Update RadExam Calculations")).click();
		
		
		//int numberOfPixelsToDragTheScrollbarDown = 5000;
		//actionBuilder.moveToElement(target, 0, numberOfPixelsToDragTheScrollbarDown).release().perform();
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
		System.out.println("Unable to scroll");
		Excel.setExcel(i, 6, "FAIL");
	}
}

public void mouseMove(int i,String testobj,String testdata) throws Exception
{
	try {
		
		//driver.findElement(By.linkText("Update RadExam Calculations")).click();
		//WebElement target=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
		//WebElement source=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
		Actions actionBuilder=new Actions(driver);          
		System.out.println("starting mouse");
		actionBuilder.moveToElement(driver.findElement(By.xpath(".//*[@id='BtnTop']/i"))).build().perform();
		//click(target).
		//moveToElement(source
		//clickAndHold().
		//moveByOffset(5000,0).
		//release().perform();
		Thread.sleep(3000);
		
		
		System.out.println("during scroll");
		
		//driver.findElement(By.linkText("Update RadExam Calculations")).click();
		
		
		//int numberOfPixelsToDragTheScrollbarDown = 5000;
		//actionBuilder.moveToElement(target, 0, numberOfPixelsToDragTheScrollbarDown).release().perform();
	} catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
		System.out.println("Unable to scroll");
	}
}

public void mousehover(int i,String testobj,String testdata) throws Exception
{

try{
	WebElement element=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
	Actions builder = new Actions(driver);
	builder.clickAndHold(element).perform();
	String tooltip=element.getAttribute("title");
	if(tooltip.contains(testdata))
	{
		Excel.setExcel(i, 6, "Pass");
		Excel.setExcel(i, 7, "Tooltip Displayed");
	}
	else
	{
		Excel.setExcel(i, 7, "Tooltip not Displayed");
	Excel.setExcel(i, 6, "Pass");
	}
	
}
catch(Exception e)
{
	e.printStackTrace();
	System.out.println("Unable to select");
	Excel.setExcel(i, 6, "FAIL");
}
}


//NCW Move scenario methods ---SSS-1418




public void verifyCheckout(int i,String testobj,String testdata) throws Exception
{

try{
	
	

	

		WebElement element=driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj)));
													  
		String Text=element.getText();
		//System.out.println("checkoutname" +expected);
	
		
		if(Text.equalsIgnoreCase("Edit Case"))
			{ 
			String lockedBy=driver.findElement(By.id("lockedByText")).getText();
			System.out.println(lockedBy);
			driver.findElement(setLocator(DriverScript.Config_properties.getProperty(testobj))).click();
			Thread.sleep(2000);
			Excel.setExcel(i, 7, lockedBy);
			}
		else
		{
			String lockedBy1=driver.findElement(By.id("lockedByText")).getText();
		Excel.setExcel(i, 7, lockedBy1);
		}
		Excel.setExcel(i, 6, "Pass");
	
	
}

catch(Exception e)
{
	e.printStackTrace();
	System.out.println("Unable to select");
	Excel.setExcel(i, 6, "FAIL");
}
}

}
