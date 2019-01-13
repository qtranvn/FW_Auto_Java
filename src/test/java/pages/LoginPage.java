package pages;

import com.qtr.core.base.BasePage;
import com.qtr.core.config.driver.selenium.WebDriverFactory;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    public LoginPage() {
        PageFactory.initElements(WebDriverFactory.instance().getWebDriver(), this);
    }

    @FindBy(xpath = "//*[@class=\"form-title\" and text()=\"ĐĂNG NHẬP\"]")
    private WebElement lbl_Login;

    @FindBy(xpath = "//input[@type=\"tel\"]")
    private WebElement input_Phone;

    @FindBy(xpath = "//input[@type=\"password\"]")
    private WebElement input_Password;

    @FindBy(xpath = "//button[@type=\"submit\"]")
    private WebElement btn_Login;

    @FindBy(xpath = "//button[contains(.,\"Đăng nhập bằng\")]")
    private WebElement btn_LoginUsingFB;

    @FindBy(xpath = "//*[@class=\"errorMessage\"]")
    private WebElement lbl_ErrorMsg;

    public void verifyRequiredFields() {
        Boolean isRequiredPhone = Boolean.valueOf(input_Phone.getAttribute("required"));
        Boolean isRequiredPassword = Boolean.valueOf(input_Password.getAttribute("required"));
        String validationMsgPhone = input_Phone.getAttribute("validationMessage");
        String validationMsgPassword = input_Password.getAttribute("validationMessage");
        if(!isRequiredPhone || !isRequiredPassword || !validationMsgPhone.equals("Please fill out this field.") || !validationMsgPassword.equals("Please fill out this field."))
            throw new IllegalArgumentException("Phone and Password should be required fields");
    }

    public void enterPhonedAndPassword(String phone, String password) {
        setText(input_Phone, phone);
        setText(input_Password, password);
    }

    public void clickSubmit() {
        btn_Login.submit();
    }

    public void clickLoginByFB() {
        btn_LoginUsingFB.click();
    }

    public void checkInvalidData() {
        String errMsg= "Phone: Số điện thoại không hợp lệ. Password: Mật khẩu phải có ít nhất 5 kí tự.";
        verifyTextPresent(errMsg,false);
    }

    public void checkWrongData() {
        String errMsg= "Số điện thoại hoặc mật khẩu không đúng, vui lòng đăng nhập lại.";
        verifyTextPresent(errMsg, false);
    }
}

