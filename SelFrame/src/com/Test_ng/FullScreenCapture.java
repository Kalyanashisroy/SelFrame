package com.Test_ng;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;
public class FullScreenCapture {

	public static void main(String[] args) {
				new FullScreenCapture().robotScreenShot();
	}

	public  void robotScreenShot() {
		//String screenshotPath=System.getProperty("user.dir")+"/Screencapture/"+getCurrentDateTime()+".jpg";
		String screenshotPath=System.getProperty("user.dir")+"/src/com/Test_ng/ScreenS/"+getCurrentDateTime();
		try {
			Robot robot = new Robot();
			//String format = "jpg";
			String format = "png";
			//String fileName = "D:/FullScreenshot." +format;
			String fileName =screenshotPath+"." + format;
			Rectangle screenRect = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage screenFullImage = robot.createScreenCapture(screenRect);
			ImageIO.write(screenFullImage, format, new File(fileName));
			System.out.println("a full screenshot saved to ::"+fileName);
		}catch(Exception e) {
			System.out.println(e);
		}
	}

	public static String getCurrentDateTime() {
		DateFormat customFormat=new SimpleDateFormat("MM_dd_yyyy_HH_hh_SS");
		Date currentDate=new Date();
		return customFormat.format(currentDate);
	}
	

	
	
}
