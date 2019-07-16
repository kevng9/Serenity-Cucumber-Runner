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
  Then David verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/319110427852
Scenario: Login user with valid username, empty password
  Given David is a registered user
  When David navigates to the Login page
  And he or she enters 'dsmith@demo.io' into the login Username field
  And he or she enters '' into the login Password field
  And he or she submits the login request
  Then David verifies he or she is presented with a error message indicating invalid credentials or access denied
  
  ##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/319110428520
Scenario: Login user with valid username, valid password, remember me selected, account is enabled, account not expired, account not locked, credentials not expired
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'jsmith@demo.io' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she clicks the Remember Me checkbox
  And he or she submits the login request
  Then David verifies he or she is at the Home page
  And he or she verifies the remember-me cookie is present

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/319110433120
Scenario: Login user with valid username, valid password, remember me not selected, account is enabled, account not expired, account not locked, credentials not expired
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'jsmith@demo.io' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she submits the login request
  Then David verifies he or she is at the Home page
  And he or she verifies the remember-me cookie is not present
  
