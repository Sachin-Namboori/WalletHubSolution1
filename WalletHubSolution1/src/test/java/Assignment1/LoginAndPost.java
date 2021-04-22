package Assignment1;

import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class LoginAndPost {

	String emailId = "sachin.namboori@gmail.com";
	String passWord = "Penholder@123";

	@Test
	public void loginAndPost() throws InterruptedException {

		// get root directory
		String projectPath = System.getProperty("user.dir");

		// chrome path to run the chrome driver
		System.setProperty("webdriver.chrome.driver", projectPath + "\\src\\test\\resources\\chromedriver.exe");

		// instantiating the chrome driver
		WebDriver driver = new ChromeDriver();

		// windows will be maximised
		driver.manage().window().maximize();

		// Setting up the implicity wait
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

		// navigate to facebook
		driver.get("https://www.facebook.com/");

		// Click on accept cookie button
		driver.findElement(By.xpath("//button[normalize-space()='Accept All']")).click();

		// provide the email id
		driver.findElement(By.id("email")).sendKeys(emailId);

		// provide the password
		driver.findElement(By.name("pass")).sendKeys(passWord);

		// Click on login
		driver.findElement(By.name("login")).click();

		// wait for and click on Name i.e Sachin Namboori
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='Sachin Namboori']"))).click();

		// wait for the webelement
		wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(
				"div[class='m9osqain a5q79mjw jm1wdb64 k4urcfbm'] span[class='a8c37x1j ni8dbmo4 stjgntxs l9j0dhe7']")))
				.click();
		// wait untill textarea becomes enable
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@class='_1mf _1mj'])[2]")))
				.sendKeys("Hello World");

		// click on post to create it
		driver.findElement(By.xpath("//span[text()='Post']")).click();

		// quit the driver
		driver.quit();
	}

}
