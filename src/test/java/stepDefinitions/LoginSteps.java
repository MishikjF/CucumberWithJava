package stepDefinitions;

import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {
	
	public WebDriver driver = new ChromeDriver();
	
	@Given("I have opened the browser and navigated to login page")
	public void openBrowser()  {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		driver.get("https://qacourse.churlinoski.mk/");
		driver.findElement(By.xpath("//a[@class=\"tools-icon my-account-icon \"]")).click();
	}
	
	//TC0001 - With valid user credentials
	@When("I login with valid credentials")
	public void insertValidUsernameAndPassword()  {
		driver.findElement(By.id("username")).sendKeys("user123455");
		driver.findElement(By.id("password")).sendKeys("AExgte6r6fjvv");
		driver.findElement(By.xpath("//button[@name='login']")).click();
	}
	
	@Then("I should be redirected to account page")
	public void validateAccountPage()  {
		Boolean isDisplayed = driver.findElement(By.xpath("//nav[@class='woocommerce-MyAccount-navigation']//ul")).isDisplayed();
		assertTrue(isDisplayed);
	}
	
	@And("I have closed the browser")
	public void closeBrowser()  {
		driver.quit();
	}
	
	//TC0002 - With invalid user credentials
	@When("I login with invalid credentials")
	public void insertInvalidUsernameAndPassword()  {
		driver.findElement(By.id("username")).sendKeys("user123456");
		driver.findElement(By.id("password")).sendKeys("AExgte6r6fjw");
		driver.findElement(By.xpath("//button[@name='login']")).click();
	}
	
	@Then("I should get an error message")
	public void validatewrongUsernameError()  {
		Boolean isDisplayed = driver.findElement(By.xpath("//div[@id='content']//li[1]")).isDisplayed();
		assertTrue(isDisplayed);
	}
	
	//TC0003 - With no user credentials
	@When("I login with no credentials")
	public void insertEmptyUsernameAndPassword()  {
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		driver.findElement(By.xpath("//button[@name='login']")).click();
	}
	
	@Then("I should get an error message for missing username")
	public void validateMissingUsernameError()  {
		Boolean isDisplayed = driver.findElement(By.xpath("//strong[contains(text(), 'Error')]")).isDisplayed();
		assertTrue(isDisplayed);
	}
	
	//TC0004 - With lost password link
	@When("I login with valid username")
	public void insertValidUsername()  {
		driver.findElement(By.id("username")).sendKeys("user123455");
	}
	
	@And("I click on lost password link")
	public void clickOnLostPassword() {
		driver.findElement(By.xpath("//a[normalize-space()='Lost your password?']")).click();
		driver.findElement(By.id("user_login")).sendKeys("user123455");
		driver.findElement(By.xpath("//button[@value=\'Reset password\']")).click();
	}
	
	@Then("I should get an reset email alert")
	public void validateResetEmailAlert()  {
		Boolean isDisplayed = driver.findElement(By.xpath("//div[@role='alert']")).isDisplayed();
		assertTrue(isDisplayed);
	}
}
