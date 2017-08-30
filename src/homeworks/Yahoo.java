package homeworks;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Yahoo {

	private WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", "C:/Users/Askar/Documents/Libraries/drivers/chromedriver.exe");

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterTest
	public void afterTest() {
		driver.close();
	}
	//sysout

	@Test(priority = 0, testName = "Browser Pop Up")

	public void browserPopUp() {
		// open the url
		driver.get("https://www.primefaces.org/showcase/ui/overlay/confirmDialog.xhtml");

		// click on button "Destroy the world"
		driver.findElement(By.xpath("//span[text()='Destroy the World']")).click();

		// verify that confirmation dialog is displayed
		String expectResult = "Confirmation";
		String actulResult = driver.findElement(By.xpath("//span[text()='Confirmation']")).getText();
		Assert.assertEquals(expectResult, actulResult);

		// click no (don't click yes)
		driver.findElement(By.xpath("//span[text()='No']")).click();

	}

	@Test(priority = 1, testName = "javascript alert")

	public void javascriptAlert() throws InterruptedException {
		// open the url
		driver.get("http://t4t5.github.io/sweetalert/");

		// click on the first "Show error message" button
		driver.findElement(By.xpath("(//button[text()='Show error message'])[1]")).click();

		// verify that alert has text "Oops... Something went wrong!"
		Alert alert1 = driver.switchTo().alert();
		String expectResult1 = "Oops... Something went wrong!";
		String actulResult1 = alert1.getText();
		Assert.assertEquals(expectResult1, actulResult1);

		// dismiss the alert
		alert1.dismiss();

		// click on the second "Show error message" button
		driver.findElement(By.xpath("//div[@class='showcase sweet']//button")).click();

		// verify that the pop up has text "Something went wrong!
		String expectResult2 = "Something went wrong!";
		String actulResult2 = driver.findElement(By.xpath("//p[text()='Something went wrong!']")).getText();
		Assert.assertTrue(actulResult2.contains(expectResult2));
	}

	@Test(priority = 2, testName = "Tab-Selenium demo sites")
	public void tab_Seleniumd_emo_sites() {

		// open the url
		driver.get("http://seleniumframework.com/demo-sites");
		String parentHandle = driver.getWindowHandle();

		// click link: seleniumframework.com/practiceform
		driver.findElement(By.linkText("http://www.seleniumframework.com/Practiceform/")).click();

		// switch to new tab using title "Selenium Framework | Practiceform"
		Set<String> handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parentHandle)) {
				driver.switchTo().window(handle);
			}
		}

		// verify that new url is http://www.seleniumframework.com/Practiceform
		String expectResult = "http://www.seleniumframework.com/Practiceform/";
		String actulResult = driver.getCurrentUrl();
		Assert.assertEquals(expectResult, actulResult);
		String childHandle = driver.getWindowHandle();

		// click on button New Browser Window
		driver.findElement(By.id("button1")).click();

		// switch to new tab using title" Selenium Framework |
		// Selenium,Cucumber, Ruby, Java et al."
		handles = driver.getWindowHandles();
		for (String handle : handles) {
			if (!handle.equals(parentHandle) && handle.equals(childHandle)) {
				driver.switchTo().window(handle);
				Assert.assertEquals("http://www.seleniumframework.com/", driver.getCurrentUrl());
			}
		}
	}

	@Test(priority = 3, testName = "Tab-Yahoo Search")
	public void yahooSearch() throws InterruptedException {
		// open the url
		driver.get("http://www.yahoo.com");

		// search for "how to handle iframe in selenium"
		driver.findElement(By.id("uh-search-box")).sendKeys("how to handle iframe in selenium\n");

		// Find all the links
		Thread.sleep(1000);
		List<WebElement> elements = driver.findElements(By.tagName("a"));

		String parentHandle = driver.getWindowHandle();

		// find the links separated with a hyphen (‐),and click each of them.
		Thread.sleep(1000);
		for (WebElement webElement : elements) {
			if (webElement.getText().contains("-")) {
				webElement.click();

				String titileOfResultPage = webElement.getText().split("-")[0];
				String domain = webElement.getText().split("-")[1];

				System.out.println("this is title: " + titileOfResultPage);
				System.out.println("this is domain:" + domain);

				// switch to the result link tab
				for (String handle : driver.getWindowHandles()) {
					if (!handle.equals(parentHandle)) {
						driver.switchTo().window(handle);
						System.out.println("current url:" + driver.getCurrentUrl() + "\n");

						// Assert.assertTrue(driver.getCurrentUrl().contains(domain));
						Thread.sleep(1000);
						driver.close();
						//yuyuyuyuyuyuyuyuyuyuyuyuyuyuyuyuy
						//uououtruuritir
					}
				}

				// switch to searching result page
				driver.switchTo().window(parentHandle);
			}
		}

	}

}
