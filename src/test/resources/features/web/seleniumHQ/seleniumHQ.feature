Feature: responseHQ

  @api
  Scenario: responseHQ repo
    Given Send GET request to "https://api.github.com/orgs/SeleniumHQ/repos" with query params and store response to var: "responseHQ"
      | per_page | 100   |
    And Verify Status is 200 from "responseHQ"
    And Calculate total number of Open Issues from "responseHQ"
    And Determine the repository with the highest star from "responseHQ"

