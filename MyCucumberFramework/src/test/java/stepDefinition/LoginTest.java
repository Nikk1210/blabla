package stepDefinition;

import com.CucumberDemo.pages.AccountPage;
import com.CucumberDemo.pages.LoginPage;
import com.CucumberDemo.utilities.TestBase;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import junit.framework.Assert;

public class LoginTest extends TestBase {
	
	LoginPage login=null;
	AccountPage account=null;

	public LoginTest() {}

	@Given("^The application \"([^\"]*)\" is opened in \"([^\"]*)\"$")
	public void the_application_is_opened_in(String url, String browser) {
//		System.out.println("first test");
		login=new LoginPage();
		startBrowsingSession(url, browser);
	}
	
	@When("^user clicks on Login option under MyAccount$")
	public void user_clicks_on_Login_option_under_MyAccount() throws Throwable {
		login.clickOnLogin();
		String title=login.getTitle();
		Assert.assertTrue("Page title invalid before Login", title.equals("Login"));
//		login.clickOnSignUp();	//Signup functionality
	}

	@SuppressWarnings("deprecation")
	@When("^enters \"([^\"]*)\" & \"([^\"]*)\"$")
	public void enters(String email, String password) throws Throwable {
		account=login.performLogin(driver, email, password);
		Thread.sleep(1000);
		String title=login.getTitle();
		Assert.assertTrue("Page title invalid after Login", title.equals("My Account"));

	}

	@Then("^application is opened successfully$")
	public void application_is_opened_successfully() throws Throwable {
		Assert.assertTrue("Assertion failed since bookings section not visible", account.checkSuccessfulLogin());
	}


	
}
