package mantis.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(css = "#username")
    private WebElement loginField;

    public LoginPage(WebDriver driver) {
        super(driver);
        currentUrl += "login_page.php";
    }

    public void login(String login) {
        driver.get(currentUrl);

        loginField.sendKeys(login);
        loginField.sendKeys(Keys.ENTER);
    }
}
