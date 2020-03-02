package com.qainfotech.automation.playstore;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(CustomListener.class)
public class PlayStore_Test extends Base{

	@BeforeClass
	public void intializeAndlaunchWebBrowser() {
		launchwebbrowser();

	}

	@AfterClass
	public void end() {
		driver.close();
	}

	@Test (priority=1)
	public void launchPlayStoreWeb() {
		driver.get(PlayStoreURL);
		Reporter.log("Launch Play Store URL:" + PlayStoreURL, true);
		driver.manage().window().maximize();
		String ActualPlayStore = driver.getTitle();
		Reporter.log("Actual PlayStore Title:" + ActualPlayStore, true);
		Reporter.log("Expected PlayStore Title:" + ExpectedPlayStoreTitle, true);
		Assert.assertEquals(ActualPlayStore, ExpectedPlayStoreTitle);

	}


	@Test(priority=2) public void playStoreSignIn() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Sign in']")).click();
		driver.findElement(By.cssSelector("#identifierId")).sendKeys("samsungs5qait@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.cssSelector("input[name=password]")).sendKeys("Qait@123"); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Next']")).click();

	}


	@Test (priority=3)
	public void movieDetails() throws InterruptedException {
		driver.get(PlayStoreURL);
		driver.findElement(By.xpath("//span[text()='Movies']")).click();
		Reporter.log("click on Movies tab",true);
		driver.findElement(By.xpath("//a[text()='New releases']")).click();
		Reporter.log("Clicked on New Release Movie", true);
		Movie25Elements = driver.findElements(By.xpath("//div[@class='ZmHEEd ']//span[text()='₹25.00']/ancestor::div[@class='vU6FJ p63iDd']//div[@class='WsMG1c nnK0zc']"));
		MovieCount = Movie25Elements.size();	
		System.out.println("Movie Count: " + MovieCount);
		Movie25Elements = scroll(Movie25Elements);
		Reporter.log(" Final MovieCount@@@ " + MovieCount,true);
		Reporter.log(" Movie25Elements@@@ " + Movie25Elements,true);
		Reporter.log("@For Loop Size:"+ Movie25Elements.size(),true);
		for(int i=0;i<Movie25Elements.size();i++){
			element = Movie25Elements.get(i);
			String MovieName;
			try {
				MovieName = element.getText();
				System.out.println("try element: "+ element);
				System.out.println("@@@@@ "+MovieName);
				Thread.sleep(2000);
				actions.moveToElement(element).click().build().perform();
				//element.click();
			} catch (StaleElementReferenceException e) {
				System.out.println("try block Exception: "+ e);
				/*
				 * driver.navigate().refresh(); System.out.println("Refresh browser");
				 */
				driver.findElement(By.xpath("//a[text()='New releases']")).click();
				Reporter.log("Clicked on New Release Movie", true);
				Movie25Elements = scroll(Movie25Elements);
				System.out.println("Catch Elements:"+ Movie25Elements);
				System.out.println("@@@Size: "+Movie25Elements.size());
				System.out.println("Scroll After Again Clicking on New Release movie");
				element = Movie25Elements.get(i);
				Thread.sleep(5000);
				System.out.println(" Catch Failed StaleElement: "+ element);
				//				MovieName = wait.until(ExpectedConditions.elementToBeClickable(element)).getText();
				//js.executeScript("arguments[0].scrollIntoView();", element);
				actions.moveToElement(element).perform();
				//System.out.println("@@MoveTOElementAction");
				MovieName = element.getText();
				System.out.println("@@@@@ "+MovieName);
				actions.moveToElement(element).click().build().perform();
				//element.click();
			}
			Reporter.log("Click on Movie Name: ",true);
			Thread.sleep(5000);
			//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
			WebElement DirectorNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='Director']//..//span[@itemprop='name']")));
			actions.moveToElement(driver.findElement(By.xpath("//h2[text()='Reviews']"))).build().perform();
			//js.executeScript("arguments[0].scrollIntoView();", DirectorNameElement);
			Thread.sleep(2000);
			String DirectorName = DirectorNameElement.getText();
			Reporter.log(MovieName +" Movie DirectorName: " + DirectorName,true);
			driver.navigate().back();
			Reporter.log("Navigated to Previous page",true);	
		}
		int a = Movie25Elements.size()-2;
		WebElement secondlastMovie = Movie25Elements.get(a);
		System.out.println(secondlastMovie.getText());
		secondlastMovie.click();
		driver.findElement(By.xpath("//button[text()='₹25.00 Rent']")).click();
		driver.findElement(By.xpath("//div[@class='XfpsVe J9fJmf']//button[text()='Sign in']")).click();
		driver.findElement(By.cssSelector("#identifierId")).sendKeys("samsungs5qait@gmail.com");
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.cssSelector("input[name=password]")).sendKeys("Qait@123"); 
		Thread.sleep(2000);
		driver.findElement(By.xpath("//span[text()='Next']")).click();
		driver.findElement(By.xpath("//span[text()='Add credit or debit card']")).click();
		/*
		 * driver.findElement(By.xpath("//button[text()='₹25.00 SD']")).click();
		 * driver.findElement(By.xpath("//span[text()='Add credit or debit card']")).
		 * click();
		 * driver.findElement(By.xpath("//input[@name='cardnumber']")).sendKeys(
		 * "1111111111111112");
		 * driver.findElement(By.xpath("//input[@name='ccmonth']")).sendKeys("11");
		 * driver.findElement(By.xpath("//input[@name='ccyear']")).sendKeys("12");
		 * driver.findElement(By.xpath("//input[@name='cvc']")).sendKeys("111");
		 * driver.findElement(By.xpath("//input[@name='ADDRESS_LINE_1']")).
		 * sendKeys("Vijay Nagar");
		 * driver.findElement(By.xpath("//input[@name='LOCALITY']")).sendKeys(
		 * "Ghaziabad");
		 * driver.findElement(By.xpath("//input[@name='POSTAL_CODE']")).sendKeys(
		 * "201009"); Select stateDropDown= new
		 * Select(driver.findElement(By.xpath("//input[@name='LOCALITY']")));
		 * stateDropDown.selectByVisibleText("Uttar Pradesh");
		 * driver.findElement(By.xpath("//div[text()='Save']")).click(); String
		 * ActualCCError = driver.findElement(By.cssSelector("#ariaId_9")).getText();
		 */
	}


}
