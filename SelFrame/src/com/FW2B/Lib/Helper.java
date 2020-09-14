package com.FW2B.Lib;


import java.io.File;



import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
// robot screenshot
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Helper {
	static String pathofPropertyfile1 = "./src/com/FW2B/Lib/keyValue_2B.properties";
	//Screenshot,alerts,frames,windows,Sync issue,javascript exucutor
	//a global variable for webdriver;
	public WebDriver driver;
	public Helper() {
	
	}
	public static WebDriver captureScreenshot(WebDriver driver) {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath=System.getProperty("user.dir")+"/Screencapture/"+getCurrentDateTime()+".png";
		try {
			FileHandler.copy(src, new File(screenshotPath));
			System.out.println("Screenshot captured");
		} catch (IOException e) {
			System.out.println("Unable to captured screenshot:" +e.getMessage());
		}		
		return driver;
	}
	public static String getCurrentDateTime() {
		DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_hh_SS");
		Date currentDate=new Date();
		return customFormat.format(currentDate);
	}
	/*public static void main(String[] args) {
		//String screenshotPath=System.getProperty("user.dir")+"\\Screencapture\\"+getCurrentDateTime()+".png";
		//System.out.println(screenshotPath);
		//new Helper().robotScreenShot();
		//System.out.println("kk"+new Helper().robotScreenShot("FAIL"));
	}*/

	public WebDriver captureScreenshot(WebDriver driver,String methodName) {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String screenshotPath=System.getProperty("user.dir")+"/Screencapture/"+methodName+"_"+getCurrentDateTime()+".png";
		try {
			FileHandler.copy(src, new File(screenshotPath));
			System.out.println("Screenshot captured");
		} catch (IOException e) {
			System.out.println("Unable to captured screenshot:" +e.getMessage());
		}		
		return driver;
	}

	//robot screenshot
	public  String robotScreenShot(String passfail) {
		String screenshotPath=System.getProperty("user.dir")+"/Screencapture/"+passfail+getCurrentDateTime();
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
			System.out.println("a full screenshot saved");
		}catch(Exception e) {
			System.out.println(e);
		}
		return fileName;
	}



	//if duplicate key is present then it always read the last one
	public String PropertyFileRead(String keyRead1) {
		Properties prop=new Properties();	
		try {
			File src=new File(pathofPropertyfile1);
			FileInputStream fis=new FileInputStream(src);
			prop.load(fis);

		}catch(Exception e) {
			e.printStackTrace();
		}
		return prop.getProperty(keyRead1);
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
				System.out.println("Element was not displayed to drag");
			}
		} catch (StaleElementReferenceException e) {
			System.out.println("Element with " + elementToResize + "is not attached to the page document "	+ e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + elementToResize + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Unable to resize" + elementToResize + " - "	+ e.getStackTrace());
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
