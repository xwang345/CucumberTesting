Feature: Use the website to find restaurants

Scenario: Search restaurants in my area
Given Open the Chrome and start application
When Find restaurants near NW1 0DJ
Then Page location area should be NW1 0DJ
And Close browser
      
Scenario: Search for restaurants in an area
Given Open the Chrome and start application
When Find restaurants near AR51 1AA
Then Page location area should be AR51 1AA
And Close browser