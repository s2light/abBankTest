package stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import net.serenitybdd.model.environment.EnvironmentSpecificConfiguration;
import net.thucydides.model.util.EnvironmentVariables;
import pages.login;
import pages.dashboard;
import utils.CommonActions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CommonWebSteps extends CommonActions {
    EnvironmentVariables environmentVariables;

    @Given("User open web {string} and login with user {string}")
    public void loginWithUser(String url, String role) {
        getDriver().get(url);
        getDriver().manage().window().maximize();
        String username = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("accounts." + role + ".username");
        String password = EnvironmentSpecificConfiguration.from(environmentVariables).getProperty("accounts." + role + ".password");
        sendText(login.LOGIN_USERNAME, username);
        sendText(login.LOGIN_PASSWORD, password);
        click(login.LOGIN_BUTTON);
    }

    @And("Pause {int} seconds")
    public void pauseSeconds(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000L);
    }

    @And("Navigate to dashboard menu: {string}")
    public void navigateToTopBarMenu(String menu) throws InterruptedException {
        click(String.format(dashboard.DASHBOARD_MAIN_MENU, menu));
    }

    @And("Navigate to menu: {string} and {string}")
    public void topbarMenu(String menu, String subMenu) {
        click(String.format(dashboard.TOPBAR_MENU, menu));
        click(String.format(dashboard.TOPBAR_SUB_MENU, subMenu));
    }

    @And("Click button: {string}")
    public void clickButton(String button) {
        click(String.format(dashboard.BUTTON, button));
    }

    @And("Clear and input text {string} to: {string}")
    public void clearAndInputText(String var, String input) {
        String text = getVar(var);
        sendText(String.format(dashboard.INPUT, input), text);
    }

    @And("Clear and search text {string} to: {string}")
    public void clearAndSearchText(String text, String input) {
        sendText(String.format(dashboard.INPUT, input), text);
        click(String.format(dashboard.LISTBOX, text));
    }

    @And("Select {string} from the {string} dropdown")
    public void selectDropdown(String text, String select) {
        click(String.format(dashboard.SELECT, select));
        click(String.format(dashboard.OPTION_SELECT, text));
    }

    @And("Click Submit: {string}")
    public void submit(String submit) {
        click(String.format(dashboard.SUBMIT, submit));
    }

    @And("Random String length {int} and store {string}")
    public void randomString(int length, String var) {
        randomString(var, length);
    }

    @Then("Create success")
    public void verifySuccessToast() {
        boolean displayed = isSuccessToastDisplayed(dashboard.RECORD_FOUND_TEXT);
        if (!displayed) {
            throw new AssertionError("Success toast not displayed with message ");
        }
    }

    @And("Verify table has data below")
    public void verifyTable(DataTable dataTable) throws InterruptedException {
        List<Map<String, String>> expectedRows = dataTable.asMaps(String.class, String.class);
        List<Map<String, String>> actualRows = getTableData();

        for (Map<String, String> expectedRow : expectedRows) {

            // Lấy key đầu tiên của row
            String firstKey = expectedRow.keySet().iterator().next();
            String firstValue = getVar(expectedRow.get(firstKey));

            // Tạo map mới mutable từ expectedRow
            Map<String, String> rowToCheck = new HashMap<>(expectedRow);
            rowToCheck.put(firstKey, firstValue); // giờ có thể update

            // Kiểm tra row có tồn tại trong actualRows
            boolean found = actualRows.stream().anyMatch(actualRow -> {
                for (Map.Entry<String, String> entry : rowToCheck.entrySet()) {
                    String col = entry.getKey();
                    String expectedValue = entry.getValue();
                    String actualValue = actualRow.get(col);
                    if (!expectedValue.equals(actualValue)) {
                        return false;
                    }
                }
                return true;
            });

            if (!found) {
                throw new AssertionError("Row not found in table: " + rowToCheck);
            }
        }
    }

}
