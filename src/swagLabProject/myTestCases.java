package swagLabProject;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;

import static org.testng.Assert.fail;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.checkerframework.checker.units.qual.C;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCases extends MyParameter {

	@BeforeTest

	public void mySetUp() {

		TheBeginingOfWebSite();

	}

	//////////////////////////////////////
	@Test(priority = 1)

	public void LoginTest() throws InterruptedException {

		String LoginValue = (String) LogIn();
		Assert.assertEquals(LoginValue.contains("Products"), true);
	}

	///////////////////////////////////////
	@Test(priority = 3, enabled = false)
	public void AddAllItems() throws InterruptedException {
		List<WebElement> myAddToCartButtons = driver.findElements(By.className("btn_inventory"));
		int CountAllAddedInCart = AddAllitems();
		int CouuntAllitems = myAddToCartButtons.size();
		Assert.assertEquals(CountAllAddedInCart, CouuntAllitems);

	}

/////////////////////////////////////////
	@Test(priority = 4, enabled = false)
	public void RemoveTwoRandomItem() throws InterruptedException {
		int countCartBeforeRemove = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
		int ActualValueOfCart = RemoveItems();

		Assert.assertEquals(ActualValueOfCart, countCartBeforeRemove - 2);

	}

	/////////////////////////////////////

	@Test(priority = 2)
	public void AddTwoSpesificItem() throws InterruptedException {
		boolean Add2value = AddSpecificItems();
		Assert.assertEquals(Add2value, true);

	}

	/////////////////////////////////////
	@Test(priority = 3)
	public void OpenCartAndCheckOut() throws InterruptedException {
		String resultCheckout = CheckOut("Batool", "mansour", "987");
		Assert.assertEquals(resultCheckout.contains("Thank you for your order!"), true);
	}

	/////////////////////////////////////
	@Test(priority = 3,enabled = false)
	public void checkThePrices() throws InterruptedException {

		double resultcheckPricesEX = CheckTotalPrice();
		String TotalAc = driver.findElement(By.className("summary_total_label")).getText();
		double ActualTotal = Double.parseDouble(TotalAc.replace("Total: $", ""));
		Assert.assertEquals(ActualTotal, resultcheckPricesEX);

	}

	/////////////////////////////////////
	@Test(priority = 2, enabled = false)
	public void SortingZtoA() throws InterruptedException {

		boolean SortValue = SortZtoA();

		Assert.assertEquals(SortValue, true);
	}
@Test(priority = 3,enabled = false)
public void RemoveFromYourCartPage() throws InterruptedException {
	int countCartBeforeRemove = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
	int ActualValueOfCart = RemoveFromURCart();

	Assert.assertEquals(ActualValueOfCart, countCartBeforeRemove - 1);}
/////////////////////////////////////////
	@Test(priority = 4)
	public void BackHome() throws InterruptedException {
		String RActualHP=BackHomePge();
		String EXHP="https://www.saucedemo.com/inventory.html";
	Assert.assertEquals(RActualHP, EXHP);	
		
		
		
	}

	@AfterTest
	public void afterMyTest() {
	}

}
