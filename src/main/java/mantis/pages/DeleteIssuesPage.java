package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DeleteIssuesPage extends BasePage {

    @FindBy(xpath = "//input[@value='Delete Issues']")
    private WebElement DeleteIssuesClick;

    public DeleteIssuesPage(WebDriver driver) {
        super(driver);
        currentUrl += "bug_actiongroup_page.php";
    }

    public void clickDeleteIssues(){
        DeleteIssuesClick.click();
    }


}
