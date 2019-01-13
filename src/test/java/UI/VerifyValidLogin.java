package UI;

import com.qtr.core.config.driver.selenium.WebDriverFactory;
import com.qtr.core.dataprovider.ExcelDataProvider;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;

public class VerifyValidLogin {
    private HomePage homePage;
    private LoginPage loginPage;

    @BeforeClass
    @Parameters({"browserName"})
    public void setUp(String browserName) {
        //Setup test data
        ExcelDataProvider.getExcelFileSheet("TestData.xlsx", "Login");

        //Setup web-driver
        WebDriverFactory.instance().createWebDriver(browserName);

        //Initiate page class
        homePage = new HomePage();
        loginPage = new LoginPage();

        //Go to Login page
        homePage.openHomePage();
        homePage.clickLoginNow();
    }

    @Test
    public void loginWithValidData() {
        //Get data in excel
        String validPhone = ExcelDataProvider.getCellData(3, 0);
        String validPassword = ExcelDataProvider.getCellData(3, 1);

        //Enter valid data
        loginPage.enterPhonedAndPassword(validPhone,validPassword);
        loginPage.clickSubmit();

        //Verify Profile displays
        homePage.verifyProfileLink();

        //Logout
        homePage.clickLogout();
    }

    @AfterClass
    public void tearDown() {
        WebDriverFactory.instance().disposeWebDriver();
    }
}
