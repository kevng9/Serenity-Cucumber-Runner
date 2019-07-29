##Cucumber: Feature Tags[f100020]##
Feature: Login User
  As a registered user
  I want to authenticate into the application
  so I can manage my accounts


##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/319110427184
Scenario: Login user with valid username, invalid password
  Given David is a registered user
  When David navigates to the Login page
  And he or she enters 'dsmith@demo.io' into the login Username field
  And he or she enters 'sSXALkUSA97359' into the login Password field
  And he or she submits the login request
  Then David verifies he or she is at the Home page

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/319110427852
Scenario: Login user with valid username, empty password
  Given David is a registered user
  When David navigates to the Login page
  And he or she enters 'dsmith@demo.io' into the login Username field
  And he or she enters '' into the login Password field
  And he or she submits the login request
  Then David verifies he or she is at the Home page
  
  