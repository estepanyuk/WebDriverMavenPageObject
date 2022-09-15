package mantis.tests;

import org.junit.jupiter.api.Test;

public class IssuesTest extends BaseTest{

    @Test
    public void addDelIssues(){
        String summary = "Summary1";
        String description = "Bug1";

        //шаг 1 авторизация
        mantisSite.login("admin", "admin20");
        mantisSite.getMainPage().waitCurrentUrlContains();

        //шаг 2 создание бага
        mantisSite.getBugReportPage().clickReportIssue();
        mantisSite.getBugReportPage().waitCurrentUrlContains();

        mantisSite.getBugReportPage().pasteSummary(summary);
        softAssertions.assertThat(mantisSite.getBugReportPage().getSummary()).as("Summary").isEqualTo(summary);

        mantisSite.getBugReportPage().pasteDescription(description);
        softAssertions.assertThat(mantisSite.getBugReportPage().getDescription()).as("Description").isEqualTo(description);

        mantisSite.getBugReportPage().clickSubmitIssue();

        String issueId = mantisSite.getBugReportPage().getNumberIssueId();
        String issueIdShort = String.valueOf(Integer.parseInt(issueId));

        mantisSite.getViewIssuesPage().waitCurrentUrlContains();

        //Шаг 3 Ищем наш созданный баг в поиске
        mantisSite.getViewIssuesPage().searchByBugId(issueId);
        mantisSite.getViewIssueDetailsPage().waitCurrentUrlContains(issueIdShort);

        //Шаг 4 Удаление
        mantisSite.getViewIssueDetailsPage().clickSubmitDelete();
        mantisSite.getDeleteIssuesPage().waitCurrentUrlContains();

        mantisSite.getDeleteIssuesPage().clickDeleteIssues();
        mantisSite.getViewIssuesPage().waitCurrentUrlContains();

        //Шаг 5 Проверяем удалился ли наш баг репорт
        mantisSite.getViewIssuesPage().searchByBugId(issueId);
        mantisSite.getViewIssueDetailsPage().waitCurrentUrlContains(issueIdShort);

        softAssertions.assertThat(mantisSite.getViewIssueDetailsPage().getIssueNotFound()).as("not found").isEqualTo("Issue " + Integer.parseInt(issueId) + " not found.");
        softAssertions.assertAll();

        //logout
        mantisSite.getViewIssuesPage().clickUserInfo();
        mantisSite.getViewIssuesPage().clickLogout();
        mantisSite.getLoginPage().waitCurrentUrlContains();
    }
}
