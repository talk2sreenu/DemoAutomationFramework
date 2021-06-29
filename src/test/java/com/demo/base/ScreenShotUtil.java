package com.demo.base;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Base64;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.demo.utilities.WebDriverFactory;

public class ScreenShotUtil extends TestBase{
	
	public static String captureScreenshot(){
		byte[] fileData = null;
		String imageFile = "Temp"+CommonUtils.dateTimeGenerate();
		File srcFile = ((TakesScreenshot) WebDriverFactory.getDriver()).getScreenshotAs(OutputType.FILE);
		File dir = new File(System.getProperty("user.dir")+"/test-results/screenshots/");
		if(!(dir.isDirectory()))
			dir.mkdir();
		String screenshotLoc = System.getProperty("user.dir")+"/test-results/screenshots/"+imageFile+".jpg";
		try {
			FileUtils.copyFile(srcFile, new File(screenshotLoc));
			fileData = FileUtils.readFileToByteArray(new File(screenshotLoc));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return Base64.getEncoder().encodeToString(fileData);
	}

	//Future enhancement. This function will be used when the screenshots are resized & rendered for size
	public String encodetoBase64(BufferedImage image) {
		ByteArrayOutputStream bs = new ByteArrayOutputStream();
		String imageString="";
		try {
			ImageIO.write(image, "jpg", bs);
			byte[] imageBytes = bs.toByteArray();
			Base64.Encoder encoder = Base64.getEncoder();
			imageString = encoder.encodeToString(imageBytes);
			bs.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		System.out.println("Expected Location : "+ imageString);
		return imageString;
	}
}
