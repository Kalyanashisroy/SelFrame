package demo2b.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PageObject_Lesson_1 {
	public WebDriver driver;
	public PageObject_Lesson_1(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(how = How.ID,using="firstname")
	public WebElement inputtext;
	public void enterFirstName() {
		inputtext.sendKeys("abcd");
		
	}
	
	/*@FindBy(how = How.ID,using="firstname")
	public WebElement inputtext;
	public PageObject_Lesson_1(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}*/
	
	
	
	
	
}
