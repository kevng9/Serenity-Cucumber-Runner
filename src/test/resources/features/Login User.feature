##Cucumber: Feature Tags[f100020]##
Feature: Login User
  As a registered user
  I want to authenticate into the application
  so I can manage my accounts


##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829544044
Scenario: Valid username, empty password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'enabled EQUALS 1 AND account_non_expired EQUALS 1 AND account_non_locked EQUALS 1 AND credentials_non_expired EQUALS 1 AND rolename EQUALS ROLE_USER' into the login Username field
  And he or she enters '' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829544748
Scenario: Valid username, valid password, remember me selected
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'enabled EQUALS 1 AND account_non_expired EQUALS 1 AND account_non_locked EQUALS 1 AND credentials_non_expired EQUALS 1 AND rolename EQUALS ROLE_USER' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she clicks the Remember Me checkbox
  And he or she submits the login request
  Then Josh verifies he or she is at the Home page
  And he or she verifies the remember-me cookie is present

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829545532
Scenario: Valid username, valid password, remember me not selected
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'enabled EQUALS 1 AND account_non_expired EQUALS 1 AND account_non_locked EQUALS 1 AND credentials_non_expired EQUALS 1 AND rolename EQUALS ROLE_USER' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is at the Home page
  And he or she verifies the remember-me cookie is not present

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829546292
Scenario: Valid username, invalid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'enabled EQUALS 1 AND account_non_expired EQUALS 1 AND account_non_locked EQUALS 1 AND credentials_non_expired EQUALS 1 AND rolename EQUALS ROLE_USER' into the login Username field
  And he or she enters 'GGUtBqwmBhzyBVpXw2382' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829546960
Scenario: Disabled Valid username, valid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'enabled EQUALS 0' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829547716
Scenario: Disabled Valid username, invalid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'enabled EQUALS 0' into the login Username field
  And he or she enters 'ECmxherTybrozo70' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829548744
Scenario: Empty username, valid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters '' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829549796
Scenario: Empty username, invalid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters '' into the login Username field
  And he or she enters 'lquEFJHGES94627' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829550564
Scenario: Expired username, valid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'account_non_expired EQUALS 0' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829551484
Scenario: Expired username, invalid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'account_non_expired EQUALS 0' into the login Username field
  And he or she enters 'JybinUUHigzqAE9' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829552140
Scenario: Invalid username, valid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters '' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829553000
Scenario: Invalid username, invalid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters '' into the login Username field
  And he or she enters 'zEDWPxDCSK00844' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829553680
Scenario: Locked username, valid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'account_non_locked EQUALS 0' into the login Username field
  And he or she enters 'Demo123!' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied

##Cucumber: Scenario Tags[p100021]##
@RALLYLINK_https://rally1.rallydev.com/#/270352442800/detail/testcase/330829554332
Scenario: Locked username, invalid password
  Given Josh is a registered user
  When Josh navigates to the Login page
  And he or she enters 'account_non_locked EQUALS 0' into the login Username field
  And he or she enters 'XWXuJNeITfPcgjBrNv66859' into the login Password field
  And he or she submits the login request
  Then Josh verifies he or she is presented with a error message indicating invalid credentials or access denied
