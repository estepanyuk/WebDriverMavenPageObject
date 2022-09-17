package mantis.tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IssuesTest extends BaseTest{

    @Test
    public void addDelIssues(){
        String summary = "Summary1";
        String description = "Bug1";

        //шаг 1 авторизация
        mantisSite.login("admin", "admin20");
        Assertions.assertEquals(mantisSite.getMainPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't MainPage");

        //шаг 2 создание бага
        mantisSite.getBugReportPage().clickReportIssue();
        Assertions.assertEquals(mantisSite.getBugReportPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't BugReportPage");


        mantisSite.getBugReportPage().pasteSummary(summary);
        softAssertions.assertThat(mantisSite.getBugReportPage().getSummary()).as("Summary").isEqualTo(summary);

        mantisSite.getBugReportPage().pasteDescription(description);
        softAssertions.assertThat(mantisSite.getBugReportPage().getDescription()).as("Description").isEqualTo(description);

        mantisSite.getBugReportPage().clickSubmitIssue();

        String issueId = mantisSite.getBugReportPage().getNumberIssueId();
        String issueIdShort = String.valueOf(Integer.parseInt(issueId));

        sleep(5000);
        Assertions.assertEquals(mantisSite.getViewIssuesPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't ViewIssuesPage");

        //Шаг 3 Ищем наш созданный баг в поиске
        mantisSite.getViewIssuesPage().searchByBugId(issueId);
        Assertions.assertEquals(mantisSite.getViewIssueDetailsPage().getCurrentUrl() + issueIdShort, driver.getCurrentUrl(), "Current url isn't ViewIssueDetailsPage");

        //Шаг 4 Удаление
        mantisSite.getViewIssueDetailsPage().clickSubmitDelete();
        Assertions.assertEquals(mantisSite.getDeleteIssuesPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't DeleteIssuesPage");

        mantisSite.getDeleteIssuesPage().clickDeleteIssues();
        Assertions.assertEquals(mantisSite.getViewIssuesPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't ViewIssuesPage");

        //Шаг 5 Проверяем удалился ли наш баг репорт
        mantisSite.getViewIssuesPage().searchByBugId(issueId);
        Assertions.assertEquals(mantisSite.getViewIssueDetailsPage().getCurrentUrl() + issueIdShort, driver.getCurrentUrl(), "Current url isn't ViewIssueDetailsPage");

        softAssertions.assertThat(mantisSite.getViewIssueDetailsPage().getIssueNotFound()).as("not found").isEqualTo("Issue " + Integer.parseInt(issueId) + " not found.");
        softAssertions.assertAll();

        //logout
        mantisSite.getViewIssuesPage().clickUserInfo();
        mantisSite.getViewIssuesPage().clickLogout();
        Assertions.assertEquals(mantisSite.getLoginPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't LoginPage");
    }
}
