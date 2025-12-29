package stepdefinitions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.datatable.DataTable;
import io.restassured.response.Response;
import io.cucumber.java.en.*;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ApiSteps {
    private Response response;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Given("Send GET request to {string} with query params and store response to var: {string}")
    public void sendGetRequestWithQueryParams(String endpoint, String var, DataTable dataTable) {
        Map<String, String> queryParams = dataTable.asMap(String.class, String.class);
        response = SerenityRest.given().header("Accept", "application/json").queryParams(queryParams).when().get(endpoint);
        Serenity.setSessionVariable(var).to(response);
        response.prettyPrint();
    }

    @Given("Calculate total number of Open Issues from {string}")
    public void getTotalOpenIssues(String var) throws JsonProcessingException {
        Response response = Serenity.sessionVariableCalled(var);
        List<Map<String, Object>> repos = objectMapper.readValue(response.asString(), new TypeReference<List<Map<String, Object>>>() {
        });

        int total = repos.stream().mapToInt(repo -> {
            Object openIssues = repo.get("open_issues_count");
            return openIssues instanceof Number ? ((Number) openIssues).intValue() : 0;
        }).sum();

        System.out.println("[INFO] Total Open Issues: " + total);
        Serenity.recordReportData().withTitle("Total Open Issues").andContents(String.valueOf(total));

    }

    @Given("Determine the repository with the highest star from {string}")
    public void getStar(String var) throws JsonProcessingException {
        Response response = Serenity.sessionVariableCalled(var);
        List<Map<String, Object>> repos = objectMapper.readValue(response.asString(), new TypeReference<List<Map<String, Object>>>() {
        });

        Map<String, Object> topRepo = repos.stream().max(Comparator.comparingInt(repo -> ((Number) repo.getOrDefault("stargazers_count", 0)).intValue())).orElseThrow(() -> new RuntimeException("No repository found"));

        System.out.println("[INFO] Top starred repository: " + topRepo.get("name") + " - Stars: " + topRepo.get("stargazers_count"));
        Serenity.recordReportData().withTitle("Top Starred Repository").andContents(topRepo.get("name") + " - Stars: " + topRepo.get("stargazers_count"));

    }

    @Given("Verify Status is {int} from {string}")
    public void verifyStatus(int num, String var) {
        Response response = Serenity.sessionVariableCalled(var);
        assertThat("Status code mismatch!", response.getStatusCode(), equalTo(num));
    }

}
