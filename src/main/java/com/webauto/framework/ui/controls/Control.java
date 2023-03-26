package com.webauto.framework.ui.controls;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Assert;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.webauto.framework.Configuration;
import com.webauto.framework.ui.Page;
import com.webauto.framework.ui.PageFactory;

public class Control {
	protected static final long TIMEOUT = Configuration.timeout();
	private Page parent;
	private By locator;
	public Control(Page parentValue, By locatorValue) {
		this.parent = parentValue;
		this.locator = locatorValue;
	}
	public WebDriver getDriver() {
		return parent.getDriver();
	}
	public Page getParent() {
		return parent;
	}
	public By getLocator() {
		return locator;
	}
	public WebElement element() {
		return getDriver().findElement(locator);
	}
	
	
	public WebElement element(int index) {
		return getDriver().findElements(locator).get(index);
	}

	public List<WebElement> elements() {
		return getDriver().findElements(locator);
	}

	
	// basic state indicator of locators
	//	
	public boolean isSelected(long timeout) {
		this.clickable(timeout);
		if (this.element().isSelected()) {
			return true;
		} else {
			return false;
		}
	}	
	
	public boolean exists(long timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	public boolean visible(long timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}
	
	public boolean clickable(long timeout) {
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(locator));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}
  
	public boolean exists() {
		return exists(TIMEOUT);
	}
	public boolean visible() {
		return visible(TIMEOUT);
	}
	
	public boolean clickable() {
		return clickable(TIMEOUT);
	}
	
	public boolean notClickable() {
		return !clickable(TIMEOUT);
	}	

	public boolean isSelected() {
		return isSelected(TIMEOUT);
	}		
	
	//  operations with assertion
	//
	public void assertExists(){
		Assert.assertTrue("Unable to find element: " + this.locator.toString(), exists());
	}

	public void assertVisible(){
		Assert.assertTrue("Element isn't visible: " + this.locator.toString(), visible());
	}		
	public void assertClickable(){
		Assert.assertTrue("Element isn't clickable: " + this.locator.toString(), clickable());
	}	

	public void assertNotClickable(){
		Assert.assertTrue("Element is clickable: " + this.locator.toString(), !clickable());
	}	
	
	public void assertSelected(){
		Assert.assertTrue("Element isn't selected: " + this.locator.toString(), isSelected());
	}	
	
	public void click() {
		Assert.assertTrue(
				"Unable to find element: " + this.locator.toString(),
				exists());
		Assert.assertTrue(
				"Element isn't clickable: " + this.locator.toString(),
				clickable());
		this.element().click();
	}
	
	public <T extends Page> T click(Class<T> pageClass) throws Exception {
		this.click();
		return PageFactory.init(this.getDriver(), pageClass);
	}
	
	public String getText() {
		Assert.assertTrue("Unable to find element with locator: " + this.getLocator(), this.exists());
		return this.element().getText();
	}
	
	public String getAttribute(String attr) {
		Assert.assertTrue("Unable to find element with locator: " + this.getLocator(), this.exists());
		return this.element().getAttribute(attr);
	}	
	
	public boolean hover_exists(long timeout) {
		WebElement hover_element= this.element();
		Actions builder = new Actions(this.getDriver());
		builder.moveToElement(hover_element).build().perform(); 		
		WebDriverWait wait = new WebDriverWait(getDriver(), timeout);
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		} catch (TimeoutException e) {
			return false;
		}
		return true;
	}

	
	public boolean scrollIntoView(long timeout) {
		WebElement scroll_element= this.element();
		JavascriptExecutor jse2 = (JavascriptExecutor)this.getDriver();
		jse2.executeScript("arguments[0].scrollIntoView()", scroll_element);		
		return true;		
	}
	
	static String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	
	
	public boolean hover_exists() {
		return hover_exists(TIMEOUT);
	}
	
	public boolean scrollIntoView() {
		return scrollIntoView(TIMEOUT);
	}
	
	public void hover_click() {
		Assert.assertTrue(
				"Unable to find element: " + this.locator.toString(),
				hover_exists());
		//hover_exists();
		Assert.assertTrue(
				"Element isn't clickable: " + this.locator.toString(),
				clickable());
		this.element().click();
	}
	
	public void scroll_click() {
		scrollIntoView();
		Assert.assertTrue(
				"Element isn't clickable: " + this.locator.toString(),
				clickable());
		this.element().click();
	}
		
}
