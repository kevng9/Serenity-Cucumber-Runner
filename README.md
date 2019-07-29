# Serenity Cucumber Test Runner
This is a Java - Maven Application to Execute Cucumber tests based on Serenity BDD framework.
The code performs on Digital Bank and Digital Credit applications. 

Test is Driven based on the .feature files located in src/test/resources/features directory.
The .feature files must be generated from CA Agile Requirements Designer and must have a link to Agile Central for every scenario.

The code fetches test data from CA Test Data Manager's Find and Reserve Models, at runtime, as per Test Data needs specified in the Test Definition.
Follows "use-get-from-where" pattern to fetch data from CA TDM.
use - is the Test Data Environment.
get - is the item to get from tdm model.
from - is the Name of the Test Data Model.
where - specifies different where clauses to search.


Example: To get a valid digital bank username.
use = "DigitalBank";
get = "username";
from = "Login";
where = "account_non_expired EQUALS 1 AND account_non_locked EQUALS 1 AND credentials_non_expired EQUALS 1 AND rolename EQUALS ROLE_USER";

This Code also Updates the verdict in CA Agile Central. (Pass/Fail)


Notes:
Running automated tests on Windows requires Firefox Quantum (64-bit) to be installed 

Command to Execute:
mvn clean verify -Dwebdriver.base.url="Base URL of System Under Test"

