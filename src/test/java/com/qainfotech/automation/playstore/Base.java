package com.qainfotech.automation.playstore;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;

public class Base {
	
	static WebDriver driver;
	static String ExpectedPlayStoreTitle = "Google Play";
	static String PlayStoreURL = "https://play.google.com/store?hl=en";
	static WebDriverWait wait;
	static Actions actions;
	static int MovieCount;
	static JavascriptExecutor js;
	static List<WebElement> Movie25Elements;
	static WebElement element;
	static String ExpectedCCError= "Invalid or unsupported form of payment.";
	
	public void launchwebbrowser() {
		System.setProperty("ChromeDriver", "C:\\Users\\aakashchoudhary\\eclipse-workspace\\playstore\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 10);
		actions = new Actions(driver);
		js = (JavascriptExecutor) driver;

	}
	
	/*Scrolling Method to till the end of screen when multiple Elements ₹25.00 movies are adding dynamically at run time*/
	public List<WebElement> scroll(List<WebElement> element) throws InterruptedException {
		int Flag=0;
		while(Flag<1) {
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			Thread.sleep(5000);
			List<WebElement> ScrollMovie25Elements = driver.findElements(By.xpath("//div[@class='ZmHEEd ']//span[text()='₹25.00']/ancestor::div[@class='vU6FJ p63iDd']//div[@class='WsMG1c nnK0zc']"));
			int ScrolledMovieCount = ScrollMovie25Elements.size();
			System.out.println("Scrolled Movie Count:"+ ScrolledMovieCount);
			if(MovieCount==ScrolledMovieCount){
				MovieCount=ScrolledMovieCount;
				element = ScrollMovie25Elements;
				System.out.println("Scrolled Count is equal to previous movie count");
				Flag++;
			}
			else
			{
				System.out.println("Scrolled Count is not equal to previous movie count");
				js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
				Thread.sleep(5000);
				//List<WebElement> ElseScrollMovie25Elements = wait.until(ExpectedConditions.visibilityOfAllElements(element));
				List<WebElement> ElseScrollMovie25Elements = driver.findElements(By.xpath("//div[@class='ZmHEEd ']//span[text()='₹25.00']/ancestor::div[@class='vU6FJ p63iDd']//div[@class='WsMG1c nnK0zc']"));
				int NewScrollMovieCount = ElseScrollMovie25Elements.size();
				MovieCount = NewScrollMovieCount;
				element = ElseScrollMovie25Elements;
				System.out.println("Updated Movie Count:"+ MovieCount);
			}
		}
		System.out.println("return element" + element);
		return element;
	}

	public void failed() {
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			Timestamp time = currentTimeStamp();
			System.out.println("C:\\Users\\aakashchoudhary\\eclipse-workspace\\playstore\\Screenshot\\testfailure"+time+".jpg");
			FileUtils.copyFile(srcFile, new File("C:\\Users\\aakashchoudhary\\eclipse-workspace\\playstore\\Screenshot\\testfailure"+time+".jpg"));
		}catch(IOException e){
			e.printStackTrace();
		}
	}

	public void onTestFailure(ITestResult result) {
		System.out.println("FAILED Test");
		failed();

	}

	public Timestamp currentTimeStamp() {
		Date date = new Date();
		long time = date.getTime();
		Timestamp ts = new Timestamp(time);	
		System.out.println("Current Time Stamp: "+ts);
		return ts;
	}

}
