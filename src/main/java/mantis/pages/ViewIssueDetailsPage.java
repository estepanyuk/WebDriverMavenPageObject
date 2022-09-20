package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ViewIssueDetailsPage extends BasePage {

    @FindBy(xpath = "//input[@type='submit'][@value='Delete']")
    private WebElement submitDeleteClick;
    @FindBy(xpath = "//div[@class='alert alert-danger']/p[2]")
    private WebElement IssueNotFound;

    @FindBy(xpath = "//th[text()='Summary']/../td")
    private WebElement bugSummary;

    @FindBy(xpath = "//th[text()='Description']/../td")
    private WebElement bugDescription;

    public ViewIssueDetailsPage(WebDriver driver) {
        super(driver);
        currentUrl += "view.php?id=";
    }

    public String getBugSummary() {
        return bugSummary.getText();
    }

    public String getBugDescription() {
        return bugDescription.getText();
    }

    public void clickSubmitDelete(){
        submitDeleteClick.click();
    }

    public String getIssueNotFound() {
        return IssueNotFound.getText();
    }


}
