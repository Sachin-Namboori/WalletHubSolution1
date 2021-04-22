package Assignment2;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.awt.AWTException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import org.testng.Assert;

public class ReviewAndTest {

	String emailId = "sachin.namboori@gmail.com";
	String passWord = "Penholder@123";
	String actualTextArea;
	String expectedTextArea = "@sachin_namboori";
	WebDriver driver;

	@Test
	public void ReviewAndPost() throws InterruptedException, AWTException {

		// setting up the system path and for chrome driver exe
		String projectPath = System.getProperty("user.dir");
		System.setProperty("webdriver.chrome.driver", projectPath + "\\src\\test\\resources\\chromedriver.exe");

		// creating object of chrome driver, maximize it and managing timeouts
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// navigating to the site and finding and clicking the Reviews section
		driver.get("http://wallethub.com/profile/test_insurance_company/");
		driver.findElement(By.xpath("//span[text()='Reviews']")).click();

		// locating review stars and clicking on it
		WebElement revStar = driver.findElement(By
				.xpath("//review-star[@class='rvs-svg']//div[@class='rating-box-wrapper']//*[local-name()='svg'][4]"));
		Actions act = new Actions(driver);
		act.moveToElement(revStar).perform();
		revStar.click();

		// creating explicit webdriverwait
		WebDriverWait wait = new WebDriverWait(driver, 30);

		// Click on dropdown
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Select...']"))).click();

		// select Health Insurance from dropdown and enter some random values
		driver.findElement(By.xpath("//li[normalize-space()='Health Insurance']")).click();
		driver.findElement(By.xpath("//textarea[@placeholder='Write your review...']")).sendKeys(
				"I am happy to work with Wallethub I am happy to work with Wallethub I am happy to work with WallethubI am happy to work with Wallethub I am happy to work with Wallethub I am happy to work with Wallethub I am happy to work with Wallethub");
		driver.findElement(By.xpath("//*[text()='Submit']")).click();

		wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Login"))).click();

		driver.findElement(By.name("em")).sendKeys(emailId);
		driver.findElement(By.name("pw1")).sendKeys(passWord);
		driver.findElement(By.xpath("//span[@ng-if='!pending']")).click();

		// Loop to poll the element to become available and then getting its text
		for (int i = 0; i <= 2; i++) {
			try {
				actualTextArea = driver.findElement(By.xpath("//span[normalize-space()='@sachin_namboori']")).getText();
				break;
			} catch (Exception e) {
				System.out.println("This is the exception occured" + e);
			}

		}

		// Asserting the expected vs actual author name
		AssertJUnit.assertEquals(expectedTextArea, actualTextArea);
		driver.quit();
	}

}
