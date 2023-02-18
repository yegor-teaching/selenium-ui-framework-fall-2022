# Selenium Review

**What is HTML?**
1. Hyper Text Markup Language - used to create the structure or "skeleton"
of a webpage. 
2. Each element is a tag and most have to have open and closing tags content in
middle and attributes in the opening tag
3. Opening tag --> content --> closing tag
   Ex: \<h1>   Content   \</h1>

**What is CSS?**
1. Cascading Style Sheets - The language we use to style an HTML document it adds "beauty"
to the "skeleton"
2. Inline CSS example: \<h1 style='background-color:red;'>

**What is the DOM?**
1. Document Object Model - is the structure in which HTML documents are built

**What are the different kinds of locators?**
1. ID - Finds element by ID attribute *is unique*
2. Classname - Finds element by the class attribute *not unique*
3. Name - Finds element by the name attribute *not unique*
4. Tag name - Finds element by the name of the HTML tag *not unique*
5. Link text - Find element by the **text** of href attribute *not unique*
6. Partial link text - Find element by a partial portion of the **text** in the href attribute *not unique*
7. xPath - Method that lets find specific elements from the DOM (Document Object Model) based on a
on their location within it. Can use compound locators 
   8. There is Absolute and Relative xPath
      9. Absolute - The direct address to an element beginning form the root
         10. Starts with /
         11. /html/body/div[1]/main/section/article/div[1]/div/input[@class='username-input']
      10. Relative - Find the element using partial portions of the tag / attributes
          11. Starts with //
          12. //input[@class='username-input']
8. CSS locators - Uses similar syntax to regular CSS. Can use compound locators/attributes to find a specific element
   9. We use . to locate by class
   10. We use # to locate by ID
       11. .username-input

**What are some Selenium WebDriver/WebElement Methods?**
1. driver.findElement(By.locator("")) - returns a specific WebElement on the WebPage which is found by the locator provided
2. driver.findElements(By.locator("")) - returns a list of WebElement on the WebPage which is found by the locator provided
3. driver.get(String url) - initially opens the specific url in the driver session
4. driver.quit() - completely quits the entire driver session
5. driver.getTitle() - returns the title of the current page
6. element.click(); - clicks on the provided WebElement
7. element.sendKeys(String); - sends a String of characters to the WebElement
8. element.getText(); - returns the content text of the element
9. element.getAttribute(String attributeName); - returns the value of the given attribute
10. element.isDisplayed(); - returns boolean of wether or not the element is displayed
11. element.isEnabled(); - returns boolean of wether or not the element is enabled
12. element.isSelected(); - returns boolean of wether or not the element is selected
13. element.clear(); - clears the current value stored inside the element

**What are the different ways we can do browser synchronization?**
1. Implicit wait
   2. Applies to elements usually at the driver creation. 
   3. Used to give sometime before throwing NoSuchElementException
   4. If the element is present before the provided time we will move forward without waiting full time
   5. Syntax: driver.manage().timeouts().implictlyWait(Duration.ofSeconds(20));
2. Explicit wait
   3. Wait for a specific condition which is explicitly defined to be matched before proceeding forward
   4. If the condition is met before the time is up we will move forward without waiting the full time
   5. WebDriverWait driverWait = new WebDriverWait(driver, Duration);
   driverWait.until(ExpectedConditions.condition);

**How can we perform page navigation in selenium?**
1. driver.navigate().to(URL) - navigates to the URL passed as a argument from the current page
2. driver.navigate().back() - navigates back to previous page if one is present
3. driver.navigate().forward() - essentially undoes the navigate.back() method
4. driver.navigate().refresh() - refreshes the current page/frame

**How can we handle dropdowns in Selenium**
1. We use the Select class by creating an object of it and using the methods the object provides
   2. select.selectByVisibleText(String); - Selects an element inside of the select tag by visible text
   3. select.selectByValue(String); - Selects an element inside of the select tag by value
   4. select.selectByIndex(int); - Selects an element inside of the select tag by index of the tag in the select tag
   5. select.getOptions(); - returns a List<WebElement> of options in the provided select tag

**How can we perform advanced mouse interactions in Selenium**
1. We use the Actions class by creating an object of it and using the methods the object provides
   2. actions.keyUp() - Release a specific key on the "keyboard" undoes the keyDown method
   3. actions.keyDown() - Hold down the specific key passed as an argument
   4. actions.scrollToElement() - Will let us scroll to a specific WebElement passed as an argument
   5. actions.scrollByAmount() - Will let us scroll by a specific amount of x and y pixels
   6. actions.moveToElement() - Used to move our cursor to a specific WebElement
   7. actions.dragAndDrop() - Will take the first argument WebElement and drop it on the 2nd WebElement argument
   8. actions.dragAndDropBy() - Will take an element and move it by an amount of pixels on the x and y axis
   9. actions.doubleClick() - performs two sequential clicks in quick succession (double click)
   10. actions.contextClick() - performs a right (context click) on the element passed as an argument
   11. actions.build() - will build the sequence of events in the method chain
   12. actions.perform() - will perform the built sequence of events
       13. actions.moveToElement(WebElement).click().scrollToElement(WebElement).build().perform();

**What is JavaScript Executor in Selenium?**
1. An interface in Selenium which we can use to perform Javascript code in the driver session
2. Creating an instance: JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
   3. We can do this since JavascriptExecutor is an interface and the driver implements this interface
4. Methods:
   5. jsExecutor.executeScript(String script); - executes the passed Javascript code snippet in the driver session

**How to handle multiple windows/tabs in Selenium?**
1. driver.getWindowHandle() - returns the String "id" for the current active window
2. driver.getWindowHandles() - returns a Set<String> with the ids of all currently open windows
3. driver.switchTo().window(String windowHandle) - switches to the passed window handle
4. In order to go back to the primary (original) window we can save the primary window handle to a String value by using 
the driver.getWindowHandle() method
5. driver.switchTo.defaultContent() - will bring us back to the original content
6. driver.close() - closes the current window/tab we are in

**What are iFrames?**
1. Is an HTML Tag which can store (embed) a separate HTML document inside an already existing one. *Can be nested*

**How can we handle iFrames in Selenium?**
1. We can use driver.switchTo().frame(WebElement frame);
2. We need to switch into the iFrame before we can use any of the elements inside of it as it is considered a separate 
document from the primary document.

**How can we leave an iFrame and enter the main frame using Selenium?**
1. driver.switchTo().parentFrame(); - enters the frame which is a direct parent to the current frame **Note:** if
a frame is nested inside of another frame the method will have to be called twice to reach the super frame
2. driver.switchTO().defaultContent() - switches to the original super/main frame

**What is Page Object Model in the context of a Framework?**
1. The Page Object Model is a structure of framework where each Page of the website has its own Page class in the project
each page class includes WebElements, and methods that describe actions on the page.

**What is PageFactory in Selenium?**
1. PageFactory is a Class which essentially make Page Object Model possible and worth while. 
2. It allows us to dynamically initialize element via the @FindBy annotation
3. We can implement by calling the PageFactory.initElements(driver, pageObject);

**How can we handle Alerts in Selenium?**
1. We can use the Alert interface in Selenium
   2. alert.dismiss() - dismisses the alert
   3. alert.accept() - accepts the alert
   4. alert.getText() - returns the message inside the alert
   5. alert.sendKeys() - sends a string of characters to the alert iput
6. We can switch to an alert by calling driver.switchTo.alert();
7. Alert alert = driver.switchTo.alert();
8. Alert alert = webdriverWait.until(ExpectedConditions.alertIsPresent()); - We can use this method since .alertIsPresent() 
returns an alert

**What is a .properties file?**
1. Its a file type that store key and value pairs often used for configurations

**What is Selenium Grid?**
1. Its a server provided by the Selenium Suite allows us to run tests in parallel 
2. Works on a hub and node system where the client sends data to the hub and the hub distributes the data to the nodes
and then returns it back to the client

**What is Saucelabs?**
1. It is a cloud hosted tool which we can use to run automation tests remotely on the cloud using Selenium Grid





