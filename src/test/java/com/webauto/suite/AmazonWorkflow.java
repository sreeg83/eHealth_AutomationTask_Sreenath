package com.webauto.suite;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.webauto.framework.Driver;
import com.webauto.framework.ui.PageFactory;
import com.webauto.tests.pageRepo.Loc_SearchHomePage;


//import utils.ExtentReports.ExtentTestManager;

public class AmazonWorkflow extends SuiteInitialization {
    private Loc_SearchHomePage loc_SearchHomePage;

    @Test()
    public void verifyAmazonSearch() throws Exception {
        logger = extent.createTest("Amazon SearchFlow",
                "Coding Test Amazon SearchFlow");
        logger.assignCategory("verifySonyTVSearch");
        verifyAmazonSearch_TC();
    }

    public void verifyAmazonSearch_TC() throws Exception {

        loc_SearchHomePage = PageFactory.init(Driver.current(), Loc_SearchHomePage.class);

        //Asserts Search query input field
        loc_SearchHomePage.searchQueryInput.assertExists();
        //Asserts Search Button  field
        loc_SearchHomePage.searchButton.assertExists();

        //Check for product
        loc_SearchHomePage.searchQueryInput.setText("sony Tv");

        //Press Search Button
        loc_SearchHomePage.searchButton.click();

        //Select Brand
        loc_SearchHomePage.featuredBrands.checkFuzzyLogic("Sony");

         //Select Resolution
        loc_SearchHomePage.Resolution.checkFuzzyLogic("4K");

        //Select Model Year
        loc_SearchHomePage.Model_Year.checkFuzzyLogic("2022");

        //sort by btn
        loc_SearchHomePage.sortByBtn.selectDropDown_text("Price: Low to High");

        //Click for Results label
        loc_SearchHomePage.resultlabel.click();

        //Assert for Results label
        Assert.assertTrue(loc_SearchHomePage.resultlabel.exists(),"true");

        Thread.sleep(10000);

    }

}
