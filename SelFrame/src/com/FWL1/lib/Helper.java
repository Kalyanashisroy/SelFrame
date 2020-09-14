package com.FWL1.lib;

import java.awt.AWTException;

import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;


public class Helper {
static Logger log = Logger.getLogger(Helper.class);
String propertyfilepath=System.getProperty("user.dir")+"/src/com/FWL1/lib/propertyfile.properties";
public Helper() {
	String projectPath = System.getProperty("user.dir");
	DOMConfigurator.configure(projectPath+getValueFrom("log4jxmlpath"));
	
}	

public static void main(String[] args) {
		// TODO Auto-generated method stub
		test();
	}

public static  void test() {
	//getValueFrom("chromepath");
	System.out.println(new Helper().getValueFrom("log4jxmlpath"));
	new Helper().robotScreenShot("ebaba");
}
	
public String getValueFrom(String key) {
	Properties prop = new Properties();
	File file = new File(propertyfilepath);
	try {
		FileInputStream fis = new FileInputStream(file);
		try {
			prop.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	return prop.getProperty(key);
}

public WebDriver captureScreenshot(WebDriver driver,String methodName) {
	File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	//String screenshotPath=System.getProperty("user.dir")+"/Screencapture/"+methodName+"_"+getCurrentDateTime()+".png";
	String screenshotPath = System.getProperty("user.dir")+getValueFrom("screenshotpath") + methodName + "_"+getCurrentDateTime()+".png";
	try {
		FileHandler.copy(src, new File(screenshotPath));
		//System.out.println("Screenshot captured");
		//log.info("Screenshot captured");
	} catch (IOException e) {
		//System.out.println("Unable to captured screenshot:" +e.getMessage());
		//log.info("Unable to captured screenshot:" +e.getMessage());
	}		
	return driver;
}

public  String getCurrentDateTime() {
	DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_hh_SS");
	Date currentDate=new Date();
	return customFormat.format(currentDate);
}

//robot screenshot
	public  String robotScreenShot(String passfail) {
		//String screenshotPath=System.getProperty("user.dir")+"/Screencapture/"+passfail+getCurrentDateTime();
		String screenshotPath = System.getProperty("user.dir")+getValueFrom("screenshotpath") + passfail + getCurrentDateTime();
		String fileName="";
		try {
			Robot robot = new Robot();
			//String format = "jpg";
			String format = "png";
			//String fileName = "D:/FullScreenshot." +format;
			fileName =screenshotPath+"." + format;
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(fileName));
			//System.out.println("a full screenshot saved");
			log.info("a full screenshot saved");
		}catch(Exception ex) {
			//System.err.println(ex);
			log.error(ex);
		}
		return fileName;
	}


	public WebDriver JSExecutor(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		//js.executeAsyncScript("window.setTimeout(arguments[arguments.length - 1],5000)");

		js.executeScript("alert('Welcome to automation testing')", "no comments");
		return driver;
	}

	public WebDriver resizeX_Yoffset(WebDriver driver,WebElement elementToResize, int xOffset, int yOffset) {
		try {
			if (elementToResize.isDisplayed()) {
				Actions action = new Actions(driver);
				action.clickAndHold(elementToResize).moveByOffset(xOffset, yOffset).release().build().perform();
			} else {
				log.info("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			//System.out.println("Element with " + elementToResize + "is not attached to the page document "	+ e.getStackTrace());
			log.error("Element with " + elementToResize + "is not attached to the page document "	+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			//System.out.println("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
			log.error("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			//System.out.println("Unable to resize" + elementToResize + " - "	+ e.getStackTrace());
			log.error("Unable to resize" + elementToResize + " - "	+ e.getStackTrace());
		}
		return driver;
	}

	public WebDriver hover(WebDriver driver, WebElement element) {
		Actions hover = new Actions(driver);
		Actions item = hover.moveToElement(element);
		item.perform();
		return driver;

	}
	public WebDriver dragAndDrop(WebDriver driver,WebElement source, WebElement target) {
		Actions dd = new Actions(driver);
		Actions fromTo = dd.dragAndDrop(source, target);
		fromTo.perform();
		return driver;
	}



	public WebDriver highlightElement(WebDriver driver,WebElement element ,String colour) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				element, "color: "+colour+"; border: 5px solid "+colour+";");
		js.executeScript("arguments[0].setAttribute('style', arguments[1]);",
				element, "");

		return driver;	
	}

	
	
}


