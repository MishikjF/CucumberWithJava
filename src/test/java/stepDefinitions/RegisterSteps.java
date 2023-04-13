package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.time.Duration;
import java.util.Random;

import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterSteps {
public WebDriver driver = new ChromeDriver();
	
	@Before
	public int generateRndForReg() {
		Random randomGenerator = new Random();
        return randomGenerator.nextInt(100000);
	}
	int rnd = generateRndForReg();
	
	@Given("I have opened the browser and navigated to register page")
	public void openBrowser()  {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver.get("https://qacourse.churlinoski.mk/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.findElement(By.xpath("//a[@class=\"tools-icon my-account-icon \"]")).click();
		
	}
	
	//TC0005 - With valid user credentials
		@When("I register with valid credentials")
		public void insertValidCredentials()  {
			driver.findElement(By.id("reg_username")).sendKeys("user"+rnd);
			driver.findElement(By.id("reg_email")).sendKeys("user"+rnd+"@localhost.com");
			driver.findElement(By.id("reg_password")).sendKeys("AExgte6r6fjvv");
			driver.findElement(By.xpath("//button[@name='register']")).click();
		}
		
		@Then("I should be redirected to account page after register")
		public void validateAccountPageAfterRegister()  {
			Boolean isDisplayed = driver.findElement(By.xpath("//nav[@class='woocommerce-MyAccount-navigation']//ul")).isDisplayed();
			assertTrue(isDisplayed);
		}
		
		@And("I have closed the browser after register")
		public void closeBrowser()  {
			driver.quit();
		}
		
		//TC0006 - With existing email
		@When("I register with existing email")
		public void insertExistingEmail()  {
			driver.findElement(By.id("reg_username")).sendKeys("user"+rnd);
			driver.findElement(By.id("reg_email")).sendKeys("user@localhost.com");
			driver.findElement(By.id("reg_password")).sendKeys("AExgte6r6fjw");
			driver.findElement(By.xpath("//button[@name='register']")).click();
		}
		
		@Then("I should get an error message about existing email")
		public void validateExistingEmailError()  {
			Boolean isDisplayed = driver.findElement(By.xpath("//*[@class='woocommerce-error']/li")).isDisplayed();
			assertTrue(isDisplayed);
		}
		
		//TC0007 - With no user credentials
		@When("I register with no user credentials")
		public void insertNoCredentials()  {
			driver.findElement(By.id("reg_username")).sendKeys("");
			driver.findElement(By.id("reg_email")).sendKeys("");
			driver.findElement(By.id("reg_password")).sendKeys("");
			driver.findElement(By.xpath("//button[@name='register']")).click();
		}
			
		@Then("I should get an error message about missing email")
		public void validateMissingEmailError()  {
			Boolean isDisplayed = driver.findElement(By.xpath("//ul[@role='alert']")).isDisplayed();
			assertTrue(isDisplayed);
		}
}
