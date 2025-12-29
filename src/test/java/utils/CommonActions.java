package utils;

import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.steps.UIInteractionSteps;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.*;
import java.util.Map;

public class CommonActions extends UIInteractionSteps {

    public void click(String xpath) {
        findBy(xpath).waitUntilClickable().click();
    }

    public void sendText(String xpath, String text) {
        clearText(xpath);
        findBy(xpath).waitUntilVisible().sendKeys(text);

    }

    public void sendTextAndEnter(String xpath, String text) {
        findBy(xpath).waitUntilVisible().sendKeys(text + Keys.ENTER);
    }

    public String getText(String xpath) {
        return findBy(xpath).waitUntilVisible().getText();
    }


    public boolean isDisplayed(String xpath) {
        return findAll(xpath).size() > 0 && findBy(xpath).isVisible();
    }

    public int countElements(String xpath) {
        return findAll(xpath).size();
    }


    public void clearText(String xpath) {
        findBy(xpath).waitUntilVisible().clear();
    }

    public void randomString(String var, int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(CHARACTERS.charAt(random.nextInt(CHARACTERS.length())));
        }
        Serenity.setSessionVariable(var).to(sb.toString());
    }

    public List<Map<String, String>> getTableData() {
        List<Map<String, String>> tableData = new ArrayList<>();
        // Lấy header
        List<WebElementFacade> headers = findAll(By.xpath("//div[@role='columnheader']"));
        List<String> headerNames = new ArrayList<>();
        for (WebElementFacade header : headers) {
            headerNames.add(header.getText().trim());
        }

        // Lấy body rows
        List<WebElementFacade> rows = findAll(By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));

        for (WebElementFacade row : rows) {
            Map<String, String> rowData = new HashMap<>();
            List<WebElementFacade> cells = row.thenFindAll(By.xpath("./div[@role='cell']"));
            for (int i = 0; i < cells.size(); i++) {
                String columnName = headerNames.get(i);
                String cellValue = cells.get(i).getText().trim();
                System.out.println(columnName + ":" + cellValue);
                rowData.put(columnName, cellValue);
            }
            tableData.add(rowData);
        }
        return tableData;
    }

    public String getVar(String var) {
        String text = Serenity.sessionVariableCalled(var);
        if (text == null) {
            text = var;
        }
        return text;
    }

    public boolean isSuccessToastDisplayed(String xpath) {
        String text = findBy(xpath).waitUntilVisible().getText();
        return "Records Found".contains(text);
    }
}

