package stepDefinitions;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.And;
import cucumber.api.junit.Cucumber;

import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.inject.Key;
import com.sun.jna.platform.unix.X11.XClientMessageEvent.Data;

@RunWith(Cucumber.class)
public class stepDefinition {
	public static WebDriver driver = new ChromeDriver();
	WebDriverWait wait = new WebDriverWait(driver, 5);

	@Given("^User is on gmail landing page$")
	public void user_is_on_gmail_landing_page() throws Throwable {
		driver.manage().window().maximize();
		driver.get("https://www.gmail.com/");
	}

	@When("^logging in with his username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void logging_in_with_his_username_something_and_password_something(String strArg1, String strArg2)
			throws Throwable {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(By.xpath("//input[@type='email']"))).sendKeys(strArg1)
				.sendKeys(Keys.ENTER).build().perform();
		WebElement element = wait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"password\"]/div[1]/div/div[1]/input")));
		element.sendKeys(strArg2);
		driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/div/button/div[2]")).click();
	}

	@When("^clicking on compose button$")
	public void clicking_on_compose_button() throws Throwable {
		WebElement composeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='T-I T-I-KE L3']")));
		composeButton.click();
		Thread.sleep(2000);
	}

	@When("^User is attaching a file , and selecting recipient \"([^\"]*)\"$")
	public void user_is_attaching_a_file_and_selecting_recipient_something(String strArg1) throws Throwable {
		WebElement to = driver.findElement(By.xpath("//textarea[@name='to']"));
		to.sendKeys(strArg1);
		WebElement textMessage = driver.findElement(By.xpath("//div[@class='Am Al editable LW-avf tS-tW']"));
		textMessage.sendKeys("File attachment for yesterday's data");
//		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@class='a1 aaA aMZ']")).click();
	
	//Using robot class for selecting a file
		Robot robot = new Robot();
		robot.setAutoDelay(1000);
		StringSelection strSlctn = new StringSelection("Sunny CV.docx");
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSlctn, null);
		
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyPress(KeyEvent.VK_ENTER);
	}

	@Then("^Clicking on send button$")
	public void clicking_on_send_button() throws Throwable {
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='T-I J-J5-Ji aoO v7 T-I-atl L3']")).click();
	}

	@Then("^mail is sent sucessfully$")
	public void mail_is_sent_sucessfully() throws Throwable {
		System.out.println("Mail sent successfully");
		driver.close();
	}
}