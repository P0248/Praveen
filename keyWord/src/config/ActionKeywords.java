package config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ActionKeywords {
	
	public static WebDriver driver;
	
	public static void launchBrowser() {
		driver = new ChromeDriver();
		
	}

}
