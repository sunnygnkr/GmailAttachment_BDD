Feature: Send mail with attachment 

Scenario: Login into your gmail account
Given User is on gmail landing page
When logging in with his username "abc@gmail.com" and password "12345"
And clicking on compose button
And User is attaching a file , and selecting recipient "abc@yahoo.com"
Then Clicking on send button
And mail is sent sucessfully

