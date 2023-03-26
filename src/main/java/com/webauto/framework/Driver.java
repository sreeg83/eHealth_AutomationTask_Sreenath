package com.webauto.framework;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class Driver {
    private Driver() {
    }

    private static ConcurrentHashMap<String, WebDriver> driverThreadMap
        = new ConcurrentHashMap<String, WebDriver>();
  
    public static void add(String browser, Capabilities capabilities) throws Exception {
     
        WebDriver driver = null;
        if (browser.trim().equalsIgnoreCase("remote")) {
            driver = new RemoteWebDriver(new URL(Configuration.get("remote_url")), capabilities);
        } else if (browser.trim().equalsIgnoreCase("chrome")){
                  	
        	System.setProperty("webdriver.chrome.driver", "seleniumDrivers/chromedriver.exe");
        	driver = new ChromeDriver();
        }
        driverThreadMap.put(getThreadName(), driver);
    }
    public static WebDriver current() {
        return driverThreadMap.get(getThreadName());
    }
    public static String getThreadName() {
        return Thread.currentThread().getName() + "-" + Thread.currentThread().getId();
    }
}
