Feature: Jenkins Login Feature

Scenario: Jenkins Login Test Scenario
	Given user is already on login page
	When title of login page is Jenkins
	Then user enter username and password
	Then user click on login button
	Then user is on home page
	Then close the browser
	
