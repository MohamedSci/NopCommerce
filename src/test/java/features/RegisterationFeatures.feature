Feature: The Complete cycle of new User Registration
  Description: a new user go to the website main URL then make new registration with new data, then Login and at the end he logouts
 Scenario Outline: The user uses his new valid registration data to make new registration that enables him to Login and Logout

 Given The User opens the registration page
  When he inputs his "<firstname>" , "<lastname>" , "<email>" , "<password>" and Submit
      Then the Registration is done successfully
   Then The User CLicked Continue button on the success dialog
#   Then The User Log out
Given The User opens the Log in page
  When he inputs the new registered "<email>" and "<password>" and Submit
      Then The LogIn process is done successfully
      Then At the End The User Log out



   Examples:
     | firstname | lastname | email | password |
     | firstname0 | lastname0 | e000037mail0@test.com | password |
     | firstname01 | lastname01 | e001137mail0@test.com | password |
     | firstname02 | lastname02 | e002238mail0@test.com | password |
     | firstname03 | lastname03 | e003339mail0@test.com | password |


