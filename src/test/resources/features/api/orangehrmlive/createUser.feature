Feature: Create user

  @ui
  Scenario: Create user and verify
    Given User open web "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login" and login with user "ADMIN"
    And Navigate to dashboard menu: "Admin"
    And Navigate to menu: "User Management" and "Users"
    And Click button: "Add"
    And Random String length 7 and store "username"
    And Clear and input text "username" to: "Username"
    And Clear and input text "admin123456" to: "Password"
    And Clear and input text "admin123456" to: "Confirm Password"
    And Clear and search text "Ranga  Akunuri" to: "Employee Name"
    When Select "Admin" from the "User Role" dropdown
    When Select "Enabled" from the "Status" dropdown
    And Click Submit: "Save"
    And Pause 5 seconds
    And Navigate to menu: "User Management" and "Users"
    And Clear and input text "username" to: "Username"
    And Clear and search text "Ranga  Akunuri" to: "Employee Name"
    When Select "Admin" from the "User Role" dropdown
    When Select "Enabled" from the "Status" dropdown
    And Click Submit: "Search"
    Then Create success
    And Verify table has data below
      | Username | User Role | Employee Name | Status  |
      | username | Admin     | Ranga Akunuri | Enabled |


