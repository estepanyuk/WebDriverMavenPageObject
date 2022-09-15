package mantis.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PasswordPage extends BasePage {

    @FindBy(id = "password")
    private WebElement passwordField;

    public PasswordPage(WebDriver driver) {
        super(driver);
        currentUrl += "login_password_page.php";
    }

    public void login(String password) {
        passwordField.sendKeys(password);
        passwordField.sendKeys(Keys.ENTER);
    }
}
