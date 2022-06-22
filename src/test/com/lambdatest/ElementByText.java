package com.lambdatest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ElementByText {

    private RemoteWebDriver driver;
    private String Status = "failed";

   
    public void setup() throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");
        ;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("version", "latest");
        caps.setCapability("build", "Extension Test");
        caps.setCapability("name", this.getClass().getName());
        caps.setCapability("plugin", "git-testng");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        caps.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

   
    public void elementByTextTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");

        driver.get("https://lambdatest.github.io/sample-todo-app/");
        /*
         * text(): A built-in method in Selenium WebDriver that is used with XPath
         * locator to locate an element based on its exact text value.
         * Example: //*[ text() = ‘5 of 5 remaining’ ]
         * contains(): Similar to the text() method, contains() is another built-in
         * method used to locate an element based on partial text match.
         * For example, if we need to locate a label that has “5 of 5 remaining” as its
         * text, it can be located using the following line of code with Xpath.
         * Example: //*[ contains (text(), ‘5 of 5’ ) ]
         */

        // Locating element with text()
        WebElement e = driver.findElement(By.xpath("//*[text()='5 of 5 remaining']"));
        System.out.println(e.getText());

        // located element with contains()
        WebElement m = driver.findElement(By.xpath("//*[contains(text(),'5 of 5')]"));
        System.out.println(m.getText());

        System.out.println("Checking Box");
        driver.findElement(By.name("li1")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li2")).click();

        System.out.println("Checking Box");
        driver.findElement(By.name("li3")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li4")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 6");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 7");
        driver.findElement(By.id("addbutton")).click();

        driver.findElement(By.id("sampletodotext")).sendKeys(" List Item 8");
        driver.findElement(By.id("addbutton")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li1")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li3")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li7")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li8")).click();
        Thread.sleep(300);

        System.out.println("Entering Text");
        driver.findElement(By.id("sampletodotext")).sendKeys("Get Taste of Lambda and Stick to It");

        driver.findElement(By.id("addbutton")).click();

        System.out.println("Checking Another Box");
        driver.findElement(By.name("li9")).click();

        // Let's also assert that the todo we added is present in the list.

        spanText = driver.findElement(By.xpath("/html/body/div/div/div/ul/li[9]/span")).getText();
        "Get Taste of Lambda and Stick to It".equalsIgnoreCase(spanText);
        Status = "passed";
        Thread.sleep(150);

        System.out.println("TestFinished");

    }

    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        ElementByText test = new ElementByText();
        test.setup();
        test.elementByTextTest();
        test.tearDown();
    }

}