package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BugReportPage extends BasePage {

    @FindBy(xpath = "//div[@id='sidebar']//span[text()=' Report Issue ']")
    private WebElement reportIssueButton;

    @FindBy(id = "summary")
    private WebElement summaryField;

    @FindBy(id = "description")
    private WebElement descriptionField;

    @FindBy(xpath = "//input[@value='Submit Issue']")
    private WebElement submitIssueButton;

    @FindBy(xpath = "//div[contains(text(), 'Recently Visited: ')]/a[1]")
    private WebElement numberIssueId;

    public BugReportPage(WebDriver driver) {
        super(driver);
        currentUrl += "bug_report_page.php";
    }

    public void clickReportIssue() {
        reportIssueButton.click();
    }

    public void pasteSummary(String text) {
        summaryField.clear();
        summaryField.sendKeys(text);
    }

    public String getSummary() {
        return summaryField.getAttribute("value");
    }

    public void pasteDescription(String text) {
        descriptionField.sendKeys(text);
    }

    public String getDescription() {
        return descriptionField.getAttribute("value");
    }

    public void clickSubmitIssue() {
        submitIssueButton.click();
    }

    public String getNumberIssueId() {
        return numberIssueId.getText();
    }

}
