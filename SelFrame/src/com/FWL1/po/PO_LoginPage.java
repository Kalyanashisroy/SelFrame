package com.FWL1.po;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import com.FWL1.lib.Helper;

public class PO_LoginPage {
	static Logger log = Logger.getLogger(PO_LoginPage.class);
	@FindBy(how = How.ID,using="j_username")
	public WebElement userName;

	@FindBy(how = How.NAME,using="j_password")
	public WebElement passWord;

	@FindBy(how = How.NAME,using="Submit")
	public WebElement submit;

	
	// Locate the element by CSS -name,id ,class and enter text
	//@FindBy(how = How.XPATH, using ="/html/body/h4[6]")
	//public WebElement Element1;





	public PO_LoginPage(WebDriver driver){
		Helper help = new Helper();
		String projectPath = System.getProperty("user.dir");
		DOMConfigurator.configure(projectPath+help.getValueFrom("log4jxmlpath"));
		
		PageFactory.initElements(driver, this);
		log.info("Page object login page is called. ");
	}
	public void pageobject1() {
		System.out.println("inside PO-->staticPage-->ParentPage");
	}
}
