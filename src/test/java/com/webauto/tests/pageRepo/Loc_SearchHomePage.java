package com.webauto.tests.pageRepo;

import org.openqa.selenium.WebDriver;

import com.webauto.framework.ui.FindBy;
import com.webauto.framework.ui.Page;
import com.webauto.framework.ui.controls.Control;
import com.webauto.framework.ui.controls.Edit;

public class Loc_SearchHomePage extends Page {
	@FindBy(locator = "xpath=//input[@id='twotabsearchtextbox']")
	public Edit searchQueryInput;
	
	@FindBy(locator = "xpath=//header/div[@id='navbar']/div[@id='nav-belt']/div[2]/div[1]/form[1]/div[3]/div[1]")
	public Control searchButton;

	@FindBy(locator = "//div[@id='brandsRefinements']/ul/li/span")
    public Edit featuredBrands;

	@FindBy(locator = "//div[@id='filters']/ul[2]/li/span")
	public Edit Resolution;

	@FindBy(locator = "//div[@id='filters']/ul[3]/li/span")
	public Edit Model_Year;

	@FindBy(locator = "xpath=//select[@id='s-result-sort-select']")
	public Edit sortByBtn;

	@FindBy(locator = "xpath=//body/div[@id='a-popover-5']/div[1]/div[1]/ul[1]/li/a")
	public Edit sortByValue;

	@FindBy(locator = "xpath=//span[contains(text(),'resul')]")
	public Edit resultlabel;



	public Loc_SearchHomePage(WebDriver driverValue) {
		super(driverValue);
		// TODO Auto-generated constructor stub
	}

}
