package UI;

import com.qtr.core.config.driver.selenium.WebDriverFactory;
import com.qtr.core.dataprovider.ExcelDataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class VerifyInvalidLogin {

    private LoginPage loginPage;

    @BeforeClass
    @Parameters({"browserName"})
    public void setUp(String browserName) {
        //Setup test data
        ExcelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Setup web-driver
        WebDriverFactory.instance().createWebDriver(browserName);

        //Initiate page class
        HomePage homePage = new HomePage();
        loginPage = new LoginPage();

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test
    public void loginWithInvalidData() {
        //Get data in excel
        String invalidPhone = ExcelDataProvider.getCellData(1, 0);
        String invalidPassword = ExcelDataProvider.getCellData(1, 1);
        String wrongPhone = ExcelDataProvider.getCellData(2, 0);
        String wrongPassword = ExcelDataProvider.getCellData(2, 1);

        // Login with blank data
        loginPage.clickSubmit();
        loginPage.verifyRequiredFields();

        // Login with invalid data
        loginPage.enterPhonedAndPassword(invalidPhone,invalidPassword);
        loginPage.clickSubmit();
        loginPage.checkInvalidData();

        // Login with wrong data
        loginPage.enterPhonedAndPassword(wrongPhone, wrongPassword);
        loginPage.clickSubmit();
        loginPage.checkWrongData();
    }

    @AfterClass
    public void tearDown() {
        WebDriverFactory.instance().disposeWebDriver();
    }
}
