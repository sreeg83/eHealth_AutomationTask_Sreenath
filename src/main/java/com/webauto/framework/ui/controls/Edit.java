package com.webauto.framework.ui.controls;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.webauto.framework.ui.Page;

public class Edit extends Control {

	public boolean scrollIntoView(long timeout) {
		WebElement scroll_element = this.element();
		JavascriptExecutor jse2 = (JavascriptExecutor) this.getDriver();
		jse2.executeScript("arguments[0].scrollIntoView()", scroll_element);
		return true;
	}

	public Edit(Page parentValue, By locatorValue) {
		super(parentValue, locatorValue);
	}

	public void setText(String value) throws InterruptedException {
		this.click();
		this.element().clear();
		Thread.sleep(1000);
		this.element().sendKeys(value);
	}

	public void setIsland(String value) throws InterruptedException {
		this.click();
		this.element().clear();
		Thread.sleep(2000);
		this.element().sendKeys(value);
	}

	public boolean checkFuzzyLogic(String bAddress) {
		this.exists();
		List<WebElement> listAddress = this.elements();
				for (WebElement address : listAddress) {
			System.out.println(address.getText().trim().toUpperCase());

			if ((address.getText().trim().toUpperCase()).contains(bAddress.toUpperCase())) {
				address.click();
				return true;
			}
		}
		return false;
	}

	public void selectDropDown_value(String value) {
		this.clickable();
		Select dropdown = new Select(this.element());
		dropdown.selectByValue(value);

	}

	public void selectDropDown_text(String value) {
		this.clickable();
		Select dropdown = new Select(this.element());
		dropdown.selectByVisibleText(value);
	}

	public void pressTAB() {
		this.element().sendKeys(Keys.TAB);
	}

}
