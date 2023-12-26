package swagLabProject;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class MyParameter {

	String myWebsite = "https://www.saucedemo.com/";
	String userName = "standard_user";
	String password = "secret_sauce";
	WebDriver driver = new ChromeDriver();
	Random rand = new Random();
	double TheITemPriceAsDouble = 0.0;

	public void TheBeginingOfWebSite() {

		driver.manage().window().maximize();
		driver.get(myWebsite);
	}

	public String LogIn() throws InterruptedException {

		// TYPE THE USERName and password to log in in standard user
		WebElement UserNameInput = driver.findElement(By.id("user-name"));
		WebElement PasswordInput = driver.findElement(By.id("password"));
		WebElement LoginButton = driver.findElement(By.id("login-button"));
		UserNameInput.sendKeys(userName);
		PasswordInput.sendKeys(password);
		LoginButton.click();
		Thread.sleep(2000);
		String labelActual = driver.findElement(By.className("title")).getText();
		return labelActual;

	}

	public boolean SortZtoA() throws InterruptedException {

		Select sortOptions = new Select(driver.findElement(By.cssSelector(".product_sort_container")));
		sortOptions.selectByIndex(1);

		Thread.sleep(3000);

		List<WebElement> ItemsNameAftterSorting = driver.findElements(By.className("inventory_item_name"));

		char[] firstItemValue = (ItemsNameAftterSorting.get(0).getText()).toCharArray();
		char[] LastItemValue = (ItemsNameAftterSorting.get(ItemsNameAftterSorting.size() - 1).getText()).toCharArray();
		if (firstItemValue[0] > LastItemValue[0]) {

			return true;
		} else {
			return false;
		}
	}

	public int AddAllitems() {

		// add all items in the cart
		List<WebElement> myAddToCartButtons = driver.findElements(By.className("btn_inventory"));

		for (int i = 0; i < myAddToCartButtons.size(); i++) {
			myAddToCartButtons.get(i).click();

		}
		int countCartAfterAdd = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());

		return countCartAfterAdd;
	}

	public int RemoveItems() throws InterruptedException {

		// remove 2 items from the cart
		Thread.sleep(3000);
		List<WebElement> RemoveButton = driver.findElements(By.className("btn_secondary"));
		int RandIndex = rand.nextInt(RemoveButton.size());
		// remove two items from cart
		RemoveButton.get(RandIndex).click();
		Thread.sleep(3000);
		RemoveButton = driver.findElements(By.className("btn_secondary"));
		RandIndex = rand.nextInt(RemoveButton.size());
		RemoveButton.get(RandIndex).click();

		int countCartAfterRemove = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
		Thread.sleep(3000);

		return countCartAfterRemove;
	}

	public boolean AddSpecificItems() throws InterruptedException {

		/// add two specific items
		List<WebElement> allIButtons = driver.findElements(By.className("btn_inventory"));

		List<WebElement> allItems = driver.findElements(By.className("inventory_item_name"));

		for (int i = 0; i < allIButtons.size(); i++) {

			String ItemName = allItems.get(i).getText();

			if (ItemName.contains("Bike") || ItemName.contains("Onesie")) {
				allIButtons.get(i).click();

			}
		}
		WebElement Cart = driver.findElement(By.className("shopping_cart_link"));
		Cart.click();
		Thread.sleep(3000);
		List<WebElement> Itemsnames = driver.findElements(By.className("inventory_item_name"));
		String FirstItem = Itemsnames.get(0).getText();
		String SecoendItem = Itemsnames.get(1).getText();

		if (FirstItem.contains("Onesie") || FirstItem.contains("Bike") && SecoendItem.contains("Onesie")
				|| SecoendItem.contains("Bike")) {
			return true;
		} else {
			return false;
		}
	}

	public String CheckOut(String fn, String ln, String zc) throws InterruptedException {
		/// open cart
		WebElement OpenTheCart = driver.findElement(By.className("shopping_cart_container"));
		OpenTheCart.click();
		Thread.sleep(2000);
		String ActualValueyourCart = driver.findElement(By.className("title")).getText();
		Assert.assertEquals(ActualValueyourCart.contains("Your Cart"), true);
		Thread.sleep(3000);
		/// checkout
		WebElement checkout = driver.findElement(By.className("btn_action"));
		checkout.click();
		Thread.sleep(3000);

		/// fill information
		WebElement FirstName = driver.findElement(By.id("first-name"));
		WebElement LastName = driver.findElement(By.id("last-name"));
		WebElement ZipCode = driver.findElement(By.id("postal-code"));
		FirstName.sendKeys(fn);
		LastName.sendKeys(ln);
		ZipCode.sendKeys(zc);
		Thread.sleep(3000);

		/// Continue button clicked
		WebElement Continue = driver.findElement(By.className("cart_button"));
		Continue.click();
		Thread.sleep(3000);
		String ActualValueCheckoutstep2 = driver.findElement(By.className("title")).getText();
		Assert.assertEquals(ActualValueCheckoutstep2.contains("Checkout: Overview"), true);
		/// Continue button clicked
		WebElement Finish = driver.findElement(By.id("finish"));
		Finish.click();
		Thread.sleep(2000);
		String SuccessfullyCheckout = driver.findElement(By.className("complete-header")).getText();
		return SuccessfullyCheckout;

	}

	public double CheckTotalPrice() throws InterruptedException {

		driver.get("https://www.saucedemo.com/checkout-step-two.html");
		List<WebElement> PricesOfTheItems = driver.findElements(By.className("inventory_item_price"));
		String ItemPrice = null;
		double converttoDouble = 0.0;
		for (int i = 0; i < PricesOfTheItems.size(); i++) {

			ItemPrice = PricesOfTheItems.get(i).getText();
			converttoDouble = Double.parseDouble(ItemPrice.replace("$", ""));
			TheITemPriceAsDouble = TheITemPriceAsDouble + converttoDouble;

		}
		Thread.sleep(2000);
		String TaxValue = driver.findElement(By.className("summary_tax_label")).getText();

		double converttoDoubleTax = Double.parseDouble(TaxValue.replace("Tax: $", " "));
		double ExpectedTotalPrice = TheITemPriceAsDouble + converttoDoubleTax;

		return ExpectedTotalPrice;

	}
	public int RemoveFromURCart() throws InterruptedException {
		///open cart page
		
		driver.get("https://www.saucedemo.com/cart.html");
	
		// remove 2 items from the cart
		Thread.sleep(3000);
		List<WebElement> RemoveButton = driver.findElements(By.className("btn_secondary"));
		int RandIndex = rand.nextInt(RemoveButton.size());
		// remove two items from cart
		RemoveButton.get(RandIndex).click();
		Thread.sleep(3000);
		
		

		int countCartAfterRemove = Integer.parseInt(driver.findElement(By.className("shopping_cart_badge")).getText());
		Thread.sleep(3000);

		return countCartAfterRemove;
	}
public String BackHomePge() throws InterruptedException {
	WebElement BackButton = driver.findElement(By.className("btn_primary"));
	
	BackButton.click();
	Thread.sleep(5000);
	
	String ActualURL=driver.getCurrentUrl();
	return ActualURL;
	
}
}
