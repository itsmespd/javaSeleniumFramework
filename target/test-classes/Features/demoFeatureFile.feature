@demotag
Feature: Demo Automation


@demo1
Scenario Outline: Demo Automation- Open Google and search
Given I set the data for "<ScenarioName>" from "<JsonFileName>" JSON File
Given I launch "<URL1>" portal
And I enter the "<Search Query>" in Google
Then I click on search button in Google

Examples:
|ScenarioName|JsonFileName|URL1|Search Query|
|scenario1|DemoTestData1.json|url1|credentials|

@demo2
Scenario Outline: Demo Automation- Open Yahoo and search
Given I set the data for "<ScenarioName>" from "<JsonFileName>" JSON File
Given I launch "<URL2>" portal
And I enter the "<Search Query>" in Yahoo
Then I click on search button in Yahoo

Examples:
|ScenarioName|JsonFileName|URL2|Search Query|
|scenario2|DemoTestData2.json|url2|searchText|