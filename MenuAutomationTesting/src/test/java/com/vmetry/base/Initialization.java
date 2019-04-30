package com.vmetry.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.j2objc.annotations.Property;

public class Initialization {
	public static WebDriver wd=null;
	public static Properties p=null;
	
	
	public static void initialize() throws IOException {
		
		System.getProperty("user.dir");
		String path=System.getProperty("user.dir")+"\\src\\test\\resources\\Config\\config.properties";
		File f =new File(path);
		//below constructor is argumented constructor(f)
		FileInputStream fis=new FileInputStream(f); // advance level of file handler to handle all types of files(to read)
		p=new Properties(); // to read key values in properties file
		p.load(fis);
		
		PropertyConfigurator.configure(path);
		if(p.getProperty("browser").equals("chrome")) {
		System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\lib\\chromedriver.exe");
		wd=new ChromeDriver();
		}else if(p.getProperty("browser").equals("firefox"))
		{
		wd=new FirefoxDriver();
		}else {
		
		wd=new InternetExplorerDriver();
		}
		
		
		wd.get(p.getProperty("testingURL"));
		wd.manage().window().maximize();
		wd.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		WebDriverWait wait=new WebDriverWait(wd,30);
		
		
		
	}

}



