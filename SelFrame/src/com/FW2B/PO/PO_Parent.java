package com.FW2B.PO;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class PO_Parent {

	//Q1.2 
	@FindBy(how = How.ID,using="firstname")
	public WebElement inputtext;

	//Q 1.2 Locate the element by CSS -name,id ,class and enter text
	//@FindBy(how = How.XPATH, using ="/html/body/h4[6]")
	//public WebElement headerElement1;


	//Q4.2  Polling_by_Explicit_Wait
	@FindBy(how = How.ID,using="butt0012")
	public WebElement button;

	@FindBy(how = How.ID,using="timRand001")
	public WebElement timerand;



	public PO_Parent(WebDriver driver){
		//System.out.println("called in PO");
		PageFactory.initElements(driver, this);
	}
	public void pageobject1() {
		System.out.println("inside PO-->staticPage-->ParentPage");
	}
}
