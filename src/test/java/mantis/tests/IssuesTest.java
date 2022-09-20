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
        Assertions.assertEquals(summary, mantisSite.getBugReportPage().getSummary(), "Summary don't correct");

        mantisSite.getBugReportPage().pasteDescription(description);
        Assertions.assertEquals(description, mantisSite.getBugReportPage().getDescription(), "Description don't correct");

        mantisSite.getBugReportPage().clickSubmitIssue();

        String issueId = mantisSite.getBugReportPage().getNumberIssueId();
        String issueIdShort = String.valueOf(Integer.parseInt(issueId));

        sleep(5000);
        Assertions.assertEquals(mantisSite.getViewIssuesPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't ViewIssuesPage");

        //Шаг 3 Ищем наш созданный баг в поиске
        mantisSite.getViewIssuesPage().searchByBugId(issueId);
        Assertions.assertEquals(mantisSite.getViewIssueDetailsPage().getCurrentUrl() + issueIdShort, driver.getCurrentUrl(), "Current url isn't ViewIssueDetailsPage");

        softAssertions.assertThat(mantisSite.getViewIssueDetailsPage().getBugSummary()).as("Summary").isEqualTo(issueId +": "+ summary);
        softAssertions.assertThat(mantisSite.getViewIssueDetailsPage().getBugDescription()).as("Description").isEqualTo(description);
        softAssertions.assertAll();

        //Шаг 4 Удаление
        mantisSite.getViewIssueDetailsPage().clickSubmitDelete();
        Assertions.assertEquals(mantisSite.getDeleteIssuesPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't DeleteIssuesPage");

        mantisSite.getDeleteIssuesPage().clickDeleteIssues();
        Assertions.assertEquals(mantisSite.getViewIssuesPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't ViewIssuesPage");

        //Шаг 5 Проверяем удалился ли наш баг репорт
        mantisSite.getViewIssuesPage().searchByBugId(issueId);
        Assertions.assertEquals(mantisSite.getViewIssueDetailsPage().getCurrentUrl() + issueIdShort, driver.getCurrentUrl(), "Current url isn't ViewIssueDetailsPage");

        Assertions.assertEquals("Issue " + Integer.parseInt(issueId) + " not found.", mantisSite.getViewIssueDetailsPage().getIssueNotFound());

        //logout
        mantisSite.getViewIssuesPage().clickUserInfo();
        mantisSite.getViewIssuesPage().clickLogout();
        Assertions.assertEquals(mantisSite.getLoginPage().getCurrentUrl(), driver.getCurrentUrl(), "Current url isn't LoginPage");
    }
}
