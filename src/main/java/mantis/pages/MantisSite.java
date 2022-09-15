package mantis.pages;

import org.openqa.selenium.WebDriver;

public class MantisSite {
    private final WebDriver driver;
    private LoginPage loginPage;
    private PasswordPage passwordPage;
    private MainPage mainPage;
    private ViewIssuesPage viewIssuesPage;
    private BugReportPage bugReportPage;
    private ViewIssueDetailsPage viewIssueDetailsPage;
    private DeleteIssuesPage deleteIssuesPage;



    public MantisSite(WebDriver driver) {
        this.driver = driver;

        loginPage = new LoginPage(driver);
        passwordPage = new PasswordPage(driver);
        mainPage = new MainPage(driver);
        viewIssuesPage = new ViewIssuesPage(driver);
        bugReportPage = new BugReportPage(driver);
        viewIssueDetailsPage = new ViewIssueDetailsPage(driver);
        deleteIssuesPage = new DeleteIssuesPage(driver);
    }

    public void login(String login, String password) {
        loginPage.login(login);
        passwordPage.login(password);
    }

    public LoginPage getLoginPage() {
        return loginPage;
    }

    public PasswordPage getPasswordPage() {
        return passwordPage;
    }

    public MainPage getMainPage() {
        return mainPage;
    }

    public ViewIssuesPage getViewIssuesPage() {
        return viewIssuesPage;
    }

    public BugReportPage getBugReportPage(){
        return bugReportPage;
    }

    public ViewIssueDetailsPage getViewIssueDetailsPage(){
        return viewIssueDetailsPage;
    }

    public DeleteIssuesPage getDeleteIssuesPage(){
        return deleteIssuesPage;
    }

}
