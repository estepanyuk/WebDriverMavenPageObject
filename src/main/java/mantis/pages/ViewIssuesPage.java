package mantis.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewIssuesPage extends BasePage {

    //поиск
    @FindBy(name = "bug_id")
    private WebElement bugIdField;

    //logout
    @FindBy(css = ".user-info")
    private WebElement userInfoClick;

    @FindBy(xpath = "//a[contains(text(),'Logout')]")
    private WebElement logoutClick;


    public ViewIssuesPage(WebDriver driver) {
        super(driver);
        currentUrl += "view_all_bug_page.php";
    }

    public void searchByBugId(String text) {
        bugIdField.click();
        bugIdField.clear();
        bugIdField.sendKeys(text);
        bugIdField.sendKeys(Keys.ENTER);
    }

    public String getNameBugIdField() {
        return bugIdField.getAttribute("value");
    }

    public void clickUserInfo() {
        userInfoClick.click();
    }

    public void clickLogout() {
        logoutClick.click();
    }
}
