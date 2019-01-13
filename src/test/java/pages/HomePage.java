package pages;

import com.qtr.core.base.BasePage;
import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(WebDriverFactory.instance().getWebDriver(), this);
    }

    @FindBy(id = "btnMenuMore")
    private WebElement btn_MenuMore;

    @FindBy(xpath = "//*[text()=\"Đăng xuất\"]")
    private WebElement lnk_Logout;

    @FindBy(xpath = "//a[text()=\"Xem trang cá nhân của bạn\"]")
    private WebElement lnk_GoToProfile;

    @FindBy(xpath = "//*[text()=\"Đăng nhập ngay\"]")
    private WebElement btn_LoginNow;

    public void openHomePage() {
        String url = "https://www.chotot.com";
        this.openURL(url);
    }

    public void clickLoginNow() {
        btn_LoginNow.click();
    }

    public void clickLogout() {
        if(waitForElementVisible(lnk_Logout, 5))
            clickJS(lnk_Logout);
        else {
            btn_MenuMore.click();
            clickJS(lnk_Logout);
        }
    }

    public void verifyProfileLink() {
        btn_MenuMore.click();
        verifyElementPresent(lnk_GoToProfile);
    }
}
