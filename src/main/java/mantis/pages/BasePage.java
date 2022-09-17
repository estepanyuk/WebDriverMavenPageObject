package mantis.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected String currentUrl = "https://academ-it.ru/mantisbt/";

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 60, 500);
        PageFactory.initElements(driver, this);
    }

    public String getCurrentUrl() {
        return currentUrl;
    }

}
