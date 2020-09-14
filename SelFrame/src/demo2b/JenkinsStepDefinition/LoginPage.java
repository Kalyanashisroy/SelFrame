package demo2b.JenkinsStepDefinition;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class LoginPage {
	WebDriver driver;
	@Given("^user is already on login page$")
	public void user_is_already_on_login_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "D:\\Browser\\chromedriver_win32_83\\chromedriver.exe" );
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/login?from=%2F");
		
	   
	}

	@When("^title of login page is Jenkins$")
	public void title_of_login_page_is_Jenkins() throws Throwable {
	    String title=driver.getTitle();
	    System.out.println(title);
	}

	@Then("^user enter username and password$")
	public void user_enter_username_and_password() throws Throwable {
		WebElement eleuser=driver.findElement(By.id("j_username"));
		eleuser.sendKeys("Kalyan");
		if(driver.findElement(By.id("j_username"))!=null){
			System.out.println("Element is present");
		}else {
			System.out.println("Element is not present");
		}
		WebElement elepass=driver.findElement(By.name("j_password"));
		elepass.sendKeys("pwd@12345");
		if(driver.getPageSource().contains("pwd@12345")) {
			System.out.println("Text is present");
		}else {
			System.out.println("Text is not present");
		}
	}

	@Then("^user click on login button$")
	public void user_click_on_login_button() throws Throwable {
		WebElement elesign=driver.findElement(By.name("Submit"));
		elesign.click();
	}

	@Then("^user is on home page$")
	public void user_is_on_home_page() throws Throwable {
	    
	}

	@Then("^close the browser$")
	public void close_the_browser() throws Throwable {
	    driver.quit();
	}

	
	

}
