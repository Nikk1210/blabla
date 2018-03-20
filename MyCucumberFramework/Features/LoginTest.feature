
Feature: Test Login feature
Scenario Outline: Test login feature for PHPTravels site
Given The application "<site>" is opened in "<browser>"
When user clicks on Login option under MyAccount
And enters "<username>" & "<password>"
Then application is opened successfully

Examples: 
|site|browser|username|password|
|https://www.phptravels.net/|chrome|user@phptravels.com|demouser|