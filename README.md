# NopCommerce
A Maven Java Project that Creates an automated test-case for User Registration and Login: https://demo.nopcommerce.com/


Techs:
- Java
- OOP
- Maven
- Selenium
- DDT
- BDD
- Cucumber
- Allure
- Page Facory
- POM 

It automates a scenario of Registration with many different data set coming from excel sheet, csv file, Json file , properties file and Facker data provider .

then the user Login with the data used in the previous registeration.
then rhe user log out.

Page object model is used in this project which means each Web page has a corresponding Java class all needed elements are instintiated there and PageFactory is also used.

The pom.xml file has all used dependiencies.
The testng.xml file has all classes used in the scenario.
The project enable cross browser testing.
There is a useful function that takes screenshots of failed tests.


