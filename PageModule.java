package developmentEnvironment;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * This class extends Initialization Class and has methods based on the page
 * title name.
 * 
 * @author Santosh Dubey.
 */

class PageModule extends Initialization {
	
	
	protected static void openBrowser() {

		ieDriver = new File(ieDriverPath);
		System.setProperty("webdriver.ie.driver", ieDriver.getAbsolutePath());
		capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		driver = new InternetExplorerDriver(capabilities);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.MINUTES);
	}

	/**
	 * This method will validate validate_CSEEnvironment_UserCredential such as
	 * TEAM and authenticate windows security pop up
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void validate_CSEEnvironment_UserCredential()
			throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		driver.get(linkToOpen);
		driver.findElement(By.id("overridelink")).click();
		Thread.sleep(100);
		driver.findElement(By.name("username")).sendKeys(username);
		Thread.sleep(100);

		driver.findElement(By.name("password")).sendKeys(password);
		/*
		 * executor = ((JavascriptExecutor) driver);
		 * executor.executeScript(String
		 * .format("document.getElementById('password').value='{0}';",
		 * password));
		 */Thread.sleep(1500);
		driver.findElement(By.id("Default")).click();

		// Assert.assertNotNull(element);
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	/**
	 * this method will validate localhost and authenticate windows security pop
	 * up
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void Validate_localhost_UserCredential()
			throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		driver.get(linkToOpen);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Alert alert = wait.until(ExpectedConditions.alertIsPresent());
		alert.authenticateUsing(new UserAndPassword(username, password));

		// Assert.assertNotNull(element);
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	/**
	 * This method will process request for referral search page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void incomingInterstateReferralSearchResultsPage()
			throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		driver.get(link_For_Incomming_Interstate_Referral_Search_Page);
		Thread.sleep(100);

		dropList = new Select(
				driver.findElement(By
						.id("referralStatus-referralStatus:dateReferralStatusStarts:dateReferralStatusEnds")));
		dropList.selectByVisibleText("PROCESSED");
		Thread.sleep(100);
		driver.findElement(By.id("searchButton")).click();
		Thread.sleep(100);
		System.out.println();
		System.out
				.println("--------Incomming Interstate Referral SearchPage (Other Jurisdiction)--------");

		element = driver.findElement(By.className("listTable"));
		allRows = element.findElements(By.tagName("tr"));
		int rowCount = 1;

		for (WebElement tableRow : allRows) {
			columns = tableRow.findElements(By.xpath("./*"));

			for (WebElement column : columns) {
				if (rowCount > 2) {
					System.out.print(column.getText() + "\t\t");
				}
			}
			if (rowCount > 2) {
				System.out.println("\n");
			}
			rowCount++;
		}

		System.out.println("-----------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	/**
	 * This method will process request for referral detail page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void incomingInterstateReferralDetailPage()
			throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		try {
			driver.get(link_For_Incomming_Interstate_Referral_Search_Page);
			Thread.sleep(100);

			dropList = new Select(
					driver.findElement(By
							.id("referralStatus-referralStatus:dateReferralStatusStarts:dateReferralStatusEnds")));
			dropList.selectByVisibleText("PROCESSED");
			Thread.sleep(100);
			driver.findElement(By.id("searchButton")).click();
			Thread.sleep(100);
			System.out.println();
			System.out
					.println("--------Incomming Interstate Referral Detail (Other Jurisdiction)--------");

			element = driver.findElement(By.className("listTable"));
			allRows = element.findElements(By.tagName("tr"));
			int rowCount = 1;

			for (WebElement tableRow : allRows) {
				columns = tableRow.findElements(By.xpath("//table[@class='listTable']/tbody/tr/td"));

				if (rowCount == 2) {
					System.out.println("Referral Selected: " + columns.get(0).getText());
					columns.get(0).findElement(By.tagName("a")).click();
				}
				if (rowCount > 2) {
					System.out.println("\n");
				}
				rowCount++;
			}
		} catch (Exception e) {
		}

		System.out.println("-------------------------------------------------------------------------");
		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

	}

	/**
	 * This method will process request for referral support order detail page.
	 * 
	 * @author Santosh Dubey.
	 */

	protected static void IncomingInterstateReferralSupportOrderDetailPage()
			throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		try {
			driver.get(link_For_Incomming_Interstate_Referral_Search_Page);
			Thread.sleep(100);

			dropList = new Select(
					driver.findElement(By
							.id("referralStatus-referralStatus:dateReferralStatusStarts:dateReferralStatusEnds")));
			dropList.selectByVisibleText("PROCESSED");
			Thread.sleep(100);
			driver.findElement(By.id("searchButton")).click();
			Thread.sleep(100);
			System.out.println();
			System.out
					.println("--------Incomming Interstate Referral Detail (Other Jurisdiction)--------");

			element = driver.findElement(By.className("listTable"));
			allRows = element.findElements(By.tagName("tr"));
			int rowCount = 1;

			for (WebElement tableRow : allRows) {
				columns = tableRow.findElements(By
						.xpath("//table[@class='listTable']/tbody/tr/td"));

				if (rowCount == 2) {
					System.out.println("Referral Selected: "
							+ columns.get(0).getText());
					columns.get(0).findElement(By.tagName("a")).click();
					break;
				}
				if (rowCount > 2) {
					System.out.println("\n");
				}
				rowCount++;
			}

			ul = driver.findElements(By
					.xpath("//div[@id='enterRefferalLinks']/ul"));

			for (WebElement lis : ul) {
				List<WebElement> columnsLi = lis.findElements(By
						.xpath("//ul[@id='infoList']/li"));

				System.out.println("Referral Page Selected: "
						+ columnsLi.get(2).getText());
				columnsLi.get(2).findElement(By.tagName("a")).click();
			}

			element = driver.findElement(By.className("listTable"));
			List<WebElement> tribunal = element.findElements(By.tagName("tr"));

			System.out.println("Tribunal Case Number Selected: "
					+ tribunal.get(2).getText());
			tribunal.get(2).findElement(By.tagName("a")).click();

			System.out
					.println("-------------------------------------------------------------------------");
			System.out.println();
			System.out.println("Exiting -" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "()");

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	
	/**
	 * This method will take appropriate input from the user and process it accordingly.
	 * 
	 * @author Santosh Dubey.
	 */
	private static String takeInputFromUser() {

		String participantPage = "Intergovernmental Status Request Search";
		String intergovernmentalPage = "Intergovernmental Referral Request Search";

		if (driver.findElement(By.id("pageTitle")).getText()
				.contains(participantPage)
				|| driver.findElement(By.id("pageTitle")).getText()
						.contains(intergovernmentalPage)) {

			String inputCaseNumber = JOptionPane
					.showInputDialog("Please type Other Jurisdiction Country Code, eg: US");
			return inputCaseNumber;
		} else {

			String inputCaseNumber = JOptionPane
					.showInputDialog("type case number or participant name");
			return inputCaseNumber;
		}

	}

	/**
	 * This method will process request for CASE Overview Page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void caseSearchPage() throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		String caseNumber = takeInputFromUser();
		System.out.println("-----------------------------------------------");
		System.out.println("Case Number/Participant Name: " + caseNumber);

		driver.findElement(By.id("_HeaderSearchText")).sendKeys(caseNumber);
		Thread.sleep(100);
		driver.findElement(By.id("headerSearchButton")).click();

		String participantPage = "Participant Search";

		if (driver.findElement(By.id("pageTitle")).getText()
				.contains(participantPage)) {
			driver.findElement(By.id("searchResultLink1")).click();
			Thread.sleep(100);
		}
		String participantOverviewPage = "Participant Overview";
		if (driver.findElement(By.id("pageTitle")).getText()
				.contains(participantOverviewPage)) {
			element = driver.findElement(By
					.xpath("//table[@class='listTable']"));
			List<WebElement> caseNum = element.findElements(By.xpath("//tr"));
			List<WebElement> columns = null;
			for (WebElement w : caseNum) {
				columns = w.findElements(By
						.xpath("//table[@class='listTable']/tbody/tr/td"));
			}

			System.out.println("Case selected: " + columns.get(10).getText());
			System.out
					.println("-----------------------------------------------");
			columns.get(10).findElement(By.tagName("a")).click();

		}

		Thread.sleep(100);
		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

	}

	/**
	 * This method will process request for Interstate Case List Page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void interstateCaseListPage() throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		caseSearchPage();

		element = driver.findElement(By.xpath("//li[@class='current']"));
		allRows = element.findElements(By.xpath("//ul"));
		List<WebElement> columns = null;
		for (WebElement tableRow : allRows) {
			columns = tableRow.findElements(By
					.xpath("//li[@class='current']/ul/li"));
		}

		System.out.println("Clicked: " + columns.get(6).getText());
		columns.get(6).findElement(By.tagName("a")).click();

		Thread.sleep(100);
		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

	}

	/**
	 * This method will process request for Interstate Case Detail Page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void interstateCaseDetailPage()
			throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
		try {
			interstateCaseListPage();

			String interstateCaseList = "Interstate Case List";

			if (driver.findElement(By.id("pageTitle")).getText()
					.contains(interstateCaseList)) {

				element = driver.findElement(By
						.xpath("//table[@class='listTable']"));
				List<WebElement> otherJurisdictionRows = element
						.findElements(By.xpath("//tr"));
				List<WebElement> otherJurisdictionCols = null;
				for (WebElement w : otherJurisdictionRows) {
					otherJurisdictionCols = w.findElements(By
							.xpath("//table[@class='listTable']/tbody/tr/td"));
				}

				System.out.println("Other jurisdiction case Number: "
						+ otherJurisdictionCols.get(3).getText());
				System.out
						.println("-----------------------------------------------");
				otherJurisdictionCols.get(3).findElement(By.tagName("a"))
						.click();
			}

			Thread.sleep(100);
			System.out.println();

			System.out.println();
			System.out.println("Exiting -" + new Object() {
			}.getClass().getEnclosingMethod().getName() + "()");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * This method will process request for Child Support Search Page Page.
	 * 
	 * @author Santosh Dubey.
	 */
	private static void processChildSupportAgencySearchPage() {
		try {
			String[] selection = new String[] { "LCSA Offices",
					"International Agency", "International Payee",
					"Tribal Agenecy", "Tribal Payee" };
			String selectedInput = askUserSelectionForGenerateInterstateTransaction(selection);
			System.out.println("You selected: " + selectedInput);
			if (selectedInput.equals("LCSA Offices")) {
				dropList = new Select(driver.findElement(By.id("searchType")));
				dropList.selectByVisibleText("LCSA Offices");

			} else if (selectedInput.equals("International Agency")) {
				dropList = new Select(driver.findElement(By.id("searchType")));
				dropList.selectByVisibleText("International Agency");
				Thread.sleep(100);

			} else if (selectedInput.equals("International Payee")) {
				dropList = new Select(driver.findElement(By.id("searchType")));
				dropList.selectByVisibleText("International Payee");
				Thread.sleep(100);

			} else if (selectedInput.equals("Tribal Agency")) {
				dropList = new Select(driver.findElement(By.id("searchType")));
				dropList.selectByVisibleText("Tribal Agency");
				Thread.sleep(100);

			} else if (selectedInput.equals("Tribal Payee")) {
				dropList = new Select(driver.findElement(By.id("searchType")));
				dropList.selectByVisibleText("Tribal Payee");
				Thread.sleep(100);
			}
		} catch (Exception e) {
		}

	}
	
	/**
	 * This method will take the the input from the user.
	 * 
	 * @author Santosh Dubey.
	 */
	private static String askUserSelectionForGenerateInterstateTransaction(
			String[] selection) {
		String selectedInput = (String) JOptionPane.showInputDialog(null,
				"Make your choice", "Search selection",
				JOptionPane.PLAIN_MESSAGE, null, selection, selection[0]);
		return selectedInput;
	}

	/**
	 * This method is for confirmation.
	 * 
	 * @author Santosh Dubey.
	 */
	private static int askUserforYesOrNo() {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Do you want to select Other Jurisdiction?",
				"Generate Interstate Transaction", JOptionPane.YES_NO_OPTION);

		return confirm;

	}

	private static int askUserforYesOrNoForOtherJurisdiction() {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Do you want to select more?",
				"Generate Interstate Transaction", JOptionPane.YES_NO_OPTION);

		return confirm;

	}

	/**
	 * This method will process the selected record.
	 * 
	 * @author Santosh Dubey.
	 */
	private static void processSelectSelection() throws InterruptedException {
		int staleCount = 0;
		try {
			element = driver.findElement(By
					.xpath("//table[@class='listTable']"));
			List<WebElement> select = element.findElements(By
					.xpath("//table[@class='listTable']/tbody/tr"));
			List<WebElement> cols = null;
			int count = 0;
			String n = "Select";
			for (WebElement w : select) {
				cols = w.findElements(By
						.xpath("//table[@class='listTable']/tbody/tr/td/a"));

				String storeCols = cols.get(count).getText();
				if (storeCols.equals(n)) {

					cols.get(count).click();
					break;
				} else {
					count++;
				}

			}

			String title = "Child Support Agency Search";
			if (driver.findElement(By.id("pageTitle")).getText()
					.contains(title)) {
				processChildSupportAgencySearchPage();
				Thread.sleep(6000);

			}
			driver.findElement(By.id("searchButton")).click();
			Thread.sleep(100);

			element = driver.findElement(By
					.xpath("//div[@class='contentBody']"));
			List<WebElement> button = element.findElements(By
					.xpath("//div[@class='contentBody']//ul"));
			List<WebElement> buttonCol = null;

			for (WebElement w : button) {
				buttonCol = w.findElements(By
						.xpath("//div[@class='contentBody']//ul/li[2]"));
			}
			List<WebElement> forForRecords = noRecordsFound();

			String noRecords = "No Records";
			if (forForRecords.get(0).getText().equals(noRecords)) {
				driver.findElement(By.id("cancelButton")).click();
				processSelectSelection();
			} else {
				buttonCol.get(0).findElement(By.tagName("a")).click();
			}
		} catch (StaleElementReferenceException e) {
			e.toString();
			System.out.println("Trying to recover from a stale element:\n"
					+ e.getMessage());
			staleCount = staleCount + 1;
		}
	}

	/**
	 * This method will remove the selected record so that user can select new record.
	 * 
	 * @author Santosh Dubey.
	 */
	private static void processRemoveSelection() throws InterruptedException {
		int staleCount = 0;

		try {
			element = driver.findElement(By
					.xpath("//table[@class='listTable']"));
			List<WebElement> select = element.findElements(By
					.xpath("//table[@class='listTable']/tbody/tr"));
			List<WebElement> cols = null;
			String n = "Remove";
			int count = 0;
			for (WebElement w : select) {
				cols = w.findElements(By
						.xpath("//table[@class='listTable']/tbody/tr/td/a"));

				String storeCols = cols.get(count).getText();
				if (storeCols.equals(n)) {

					cols.get(count).click();
					break;
				} else {
					count++;
				}

			}

			Thread.sleep(100);
			processSelectSelection();

		} catch (StaleElementReferenceException e) {
			e.toString();
			System.out.println("Trying to recover from a stale element:\n"
					+ e.getMessage());
			staleCount = staleCount + 1;
		}
	}

	/**
	 * This method will process request for Interstate transaction Page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void generateInterstateTransactionPage()
			throws InterruptedException {
		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		caseSearchPage();

		element = driver.findElement(By.xpath("//div[@id='levelThree']"));
		allRows = element.findElements(By.xpath("//ul"));
		List<WebElement> columns = null;

		for (WebElement tableRow : allRows) {
			columns = tableRow.findElements(By
					.xpath("//div[@id='levelThree']/ul/li"));
		}

		System.out.println("Page Selected: " + columns.get(4).getText());
		columns.get(4).findElement(By.tagName("a")).click();

		dropList = new Select(driver.findElement(By.id("functionOptions")));
		dropList.selectByVisibleText("Case Status Information");
		Thread.sleep(100);
		dropList = new Select(driver.findElement(By.name("actionOptions")));
		dropList.selectByVisibleText("Provision of information/Response");
		Thread.sleep(100);
		dropList = new Select(driver.findElement(By.id("reasonOptions")));
		dropList.selectByVisibleText("Provide all available case information (FSINF)");
		Thread.sleep(100);

		element = driver.findElement(By.xpath("//div[@class='buttonCluster']"));
		List<WebElement> buttonRows = element.findElements(By.xpath("//ul"));

		List<WebElement> buttonCols = null;

		for (WebElement w : buttonRows) {
			buttonCols = w.findElements(By
					.xpath("//div[@class='buttonCluster']/ul/li"));
		}
		buttonCols.get(4).findElement(By.tagName("a")).click();

		int selectConfirm = askUserforYesOrNo();

		if (selectConfirm == JOptionPane.YES_OPTION) {
			processSelectSelection();
		}

		int removeConfirm = askUserforYesOrNoForOtherJurisdiction();
		if (removeConfirm == JOptionPane.YES_OPTION) {
			processRemoveSelection();

		}

		Thread.sleep(100);
		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

	}

	/**
	 * This method will confirm Date and CASE ID.
	 * 
	 * @author Santosh Dubey.
	 */
	private static int askUserToCheckDateAndCaseID()
			throws InterruptedException {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Please check date and case ID", "Search Verification",
				JOptionPane.PLAIN_MESSAGE);

		return confirm;

	}

	/**
	 * This method will confirm to rerun of the program.
	 * 
	 * @author Santosh Dubey.
	 */
	private static int askUserToRunAgain() throws InterruptedException {
		int confirm = JOptionPane.showConfirmDialog(null,
				"Do you want to run again ?", "Search Verification",
				JOptionPane.PLAIN_MESSAGE);

		return confirm;

	}

	/**
	 * This method will process request for Interstate Transaction Result Page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void interstateTransactionSearchResultsPage()
			throws InterruptedException {

		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		caseSearchPage();

		element = driver.findElement(By.xpath("//div[@id='levelThree']"));
		allRows = element.findElements(By.xpath("//ul"));
		List<WebElement> columns = null;

		for (WebElement tableRow : allRows) {
			columns = tableRow.findElements(By
					.xpath("//div[@id='levelThree']/ul/li"));
		}

		System.out.println("Page Selected: " + columns.get(5).getText());
		columns.get(5).findElement(By.tagName("a")).click();
		Thread.sleep(500);

		int confirmCase = askUserToCheckDateAndCaseID();
		Thread.sleep(6000);

		if (confirmCase == JOptionPane.OK_OPTION) {

			driver.findElement(By.id("searchButton")).click();
			String noResutlFound = "No Search Results Found.";
			String interstateTransactionSearch = "Interstate Transaction Search";

			if (driver.findElement(By.id("pageTitle")).getText()
					.equals(interstateTransactionSearch)) {

				element = driver.findElement(By
						.xpath("//table[@class='listTable']"));
				List<WebElement> interstateTransactionSearchRows = element
						.findElements(By.xpath("//tbody"));
				List<WebElement> interstateTransactionSearchCols = null;

				for (WebElement w : interstateTransactionSearchRows) {
					interstateTransactionSearchCols = w.findElements(By
							.xpath("//table[@class='listTable']/tbody/tr"));
				}
				if (interstateTransactionSearchCols.get(0).getText()
						.equals(noResutlFound)) {
					interstateTransactionSearchResultsPage();
				} else {
					for (WebElement w : interstateTransactionSearchRows) {
						interstateTransactionSearchCols = w
								.findElements(By
										.xpath("//table[@class='listTable']/tbody/tr/td"));
					}
				}

				System.out
						.println("-----------------------------------------------");
				interstateTransactionSearchCols.get(1)
						.findElement(By.tagName("a")).click();
			}
		}

		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	
	/**
	 * This method will get first level of the MENU records and store the menu objects in a LIST.
	 * 
	 * @author Santosh Dubey.
	 */
	private static List<WebElement> getFirstLevelMenu() {
		element = driver.findElement(By.xpath("//div[@id='levelOne']"));
		List<WebElement> CSEHomePageallRows = element.findElements(By
				.xpath("//ul"));
		List<WebElement> CSEHomePagecolumns = null;
		for (WebElement tableRow : CSEHomePageallRows) {
			CSEHomePagecolumns = tableRow.findElements(By
					.xpath("//div[@id='levelOne']/ul/li"));

		}
		return CSEHomePagecolumns;
	}

	
	/**
	 * This method will get second level of the MENU records and store the menu objects in a LIST.
	 * 
	 * @author Santosh Dubey.
	 */
	private static List<WebElement> getSecondLevelMenu() {
		element = driver.findElement(By.xpath("//div[@id='levelTwo']"));
		List<WebElement> CSEHomePageallRows = element.findElements(By
				.xpath("//ul"));
		List<WebElement> CSEHomePagecolumns = null;

		for (WebElement tableRow : CSEHomePageallRows) {
			CSEHomePagecolumns = tableRow.findElements(By
					.xpath("//div[@id='levelTwo']/ul/li"));
		}
		return CSEHomePagecolumns;
	}

	
	/**
	 * This method will get side MENU records and store the menu objects in a LIST.
	 * 
	 * @author Santosh Dubey.
	 */
	private static List<WebElement> getSideMenu() {
		element = driver.findElement(By.xpath("//div[@id='levelThree']"));
		List<WebElement> CSEHomePageallRows = element.findElements(By
				.xpath("//ul"));
		List<WebElement> CSEHomePagecolumns = null;

		for (WebElement tableRow : CSEHomePageallRows) {
			CSEHomePagecolumns = tableRow.findElements(By
					.xpath("//div[@id='levelThree']/ul/li"));
		}
		return CSEHomePagecolumns;
	}
	
	
	/**
	 * This method will process the request for Formset Search Page.
	 * 
	 * @author Santosh Dubey.
	 */
	private static void formSetSearchSelectionPage()
			throws InterruptedException {
		String[] selection = new String[] { "CASE MANAGEMENT", "ENFORCEMENT",
				"ESTABLISHMENT", "INTERSTATE", "LOCATE", "NON IV-D" };
		String selectedInput = askUserSelectionForGenerateInterstateTransaction(selection);
		System.out.println("You selected: " + selectedInput);
		if (selectedInput.equals("CASE MANAGEMENT")) {
			dropList = new Select(driver.findElement(By
					.id("category-category:subcategory")));
			dropList.selectByVisibleText("CASE MANAGEMENT");

		} else if (selectedInput.equals("ENFORCEMENT")) {
			dropList = new Select(driver.findElement(By
					.id("category-category:subcategory")));
			dropList.selectByVisibleText("ENFORCEMENT");
			Thread.sleep(100);

		} else if (selectedInput.equals("ESTABLISHMENT")) {
			dropList = new Select(driver.findElement(By
					.id("category-category:subcategory")));
			dropList.selectByVisibleText("ESTABLISHMENT");
			Thread.sleep(100);

		} else if (selectedInput.equals("INTERSTATE")) {
			dropList = new Select(driver.findElement(By
					.id("category-category:subcategory")));
			dropList.selectByVisibleText("INTERSTATE");
			Thread.sleep(100);

		} else if (selectedInput.equals("LOCATE")) {
			dropList = new Select(driver.findElement(By
					.id("category-category:subcategory")));
			dropList.selectByVisibleText("LOCATE");
			Thread.sleep(100);
		} else if (selectedInput.equals("NON IV-D")) {
			dropList = new Select(driver.findElement(By
					.id("category-category:subcategory")));
			dropList.selectByVisibleText("NON IV-D");
			Thread.sleep(100);
		}

	}

	/**
	 * This method will process the request for Formset Parameter Search Page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void formSetParameterSearchPage()
			throws InterruptedException {

		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		List<WebElement> storeFirstLevelMenu = getFirstLevelMenu();

		storeFirstLevelMenu.get(7).findElement(By.tagName("a")).click();

		List<WebElement> storeSecondLevelMenu = getSecondLevelMenu();

		storeSecondLevelMenu.get(1).findElement(By.tagName("a")).click();
		formSetSearchSelectionPage();

		driver.findElement(By.id("searchButton")).click();

		element = driver.findElement(By.className("listTable"));
		List<WebElement> formSetParameterPage = element.findElements(By
				.xpath("//table[@class='listTable']/tbody/tr/td"));

		System.out.println("Form Set Number: "
				+ formSetParameterPage.get(3).getText());
		formSetParameterPage.get(3).findElement(By.tagName("a")).click();

		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	
	/**
	 * This method will process the request for Intergovernmental referral search page.
	 * 
	 * @author Santosh Dubey.
	 */
	private static String intergovernmentalReferralRequestSearchResultsPageSelection()
			throws InterruptedException {
		String[] selection = new String[] { "Analyst", "Assignment - CSENET",
				"Assignment - HARDCOPY", "Technician", "General" };
		String selectedInput = askUserSelectionForGenerateInterstateTransaction(selection);
		System.out.println("You selected: " + selectedInput);
		if (selectedInput.equals("Analyst")) {
			dropList = new Select(driver.findElement(By.id("searchType")));
			dropList.selectByVisibleText("Analyst");

		} else if (selectedInput.equals("Assignment - CSENET")) {
			dropList = new Select(driver.findElement(By.id("searchType")));
			dropList.selectByVisibleText("Assignment - CSENET");
			askUserToCheckDateAndCaseID();

		} else if (selectedInput.equals("Assignment - HARDCOPY")) {
			dropList = new Select(driver.findElement(By.id("searchType")));
			dropList.selectByVisibleText("Assignment - HARDCOPY");
			askUserToCheckDateAndCaseID();

		} else if (selectedInput.equals("Technician")) {
			dropList = new Select(driver.findElement(By.id("searchType")));
			dropList.selectByVisibleText("Technician");
			askUserToCheckDateAndCaseID();

		} else if (selectedInput.equals("General")) {
			dropList = new Select(driver.findElement(By.id("searchType")));
			dropList.selectByVisibleText("General");
			askUserToCheckDateAndCaseID();
		}
		return selectedInput;

	}

	/**
	 * This method will process the request for Intergovernmental referral search page.
	 * 
	 * @author Santosh Dubey.
	 */
	private static void IntergovernmentalReferralRequestSearchOtherJurisdiction()
			throws InterruptedException {
		String[] selection = new String[] { "ALABAMA", "ALASKA",
				"AMERICAN SAMOA", "ARIZONA", "ARKANSAS", "CALIFORNIA",
				"COLORADO", "CONNECTICUT", "DELAWARE", "DISTRICT OF COLUMBIA",
				"FLORIDA", "GEORGIA", "GUAM", "HAWAII", "IDAHO", "ILLINOIS",
				"INDIANA", "IOWA", "KANSAS", "KENTUCKY", "LOUISIANA", "MAINE",
				"MARYLAND", "MASSACHUSETTS", "MICHIGAN", "MINNESOTA",
				"MISSISSIPPI", "MISSOURI", "MONTANA", "NEBRASKA", "NEVADA",
				"NEW HAMPSHIRE", "NEW JERSEY", "NEW MEXICO", "NEW YORK",
				"NORTH CAROLINA", "NORTH DAKOTA", "NORTH MARIANA ISLANDS",
				"OHIO", "OKLAHOMA", "OREGON", "PENNSYLVANIA", "PUERTO RICO",
				"RHODE ISLAND", "SOUTH CAROLINA", "SOUTH DAKOTA", "TENNESSEE",
				"TEXAS", "UTAH", "VERMONT", "VIRGIN ISLANDS", "VIRGINIA",
				"WASHINGTON", "WEST VIRGINIA", "WISCONSIN", "WYOMING" };
		String selectedInput = askUserSelectionForGenerateInterstateTransaction(selection);
		System.out.println("You selected: " + selectedInput);
		if (selectedInput.equals("ALABAMA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("ALABAMA");

		} else if (selectedInput.equals("ALASKA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("ALASKA");

		} else if (selectedInput.equals("AMERICAN SAMOA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("AMERICAN SAMOA");

		} else if (selectedInput.equals("ARIZONA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("ARIZONA");

		} else if (selectedInput.equals("ARKANSAS")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("ARKANSAS");

		} else if (selectedInput.equals("CALIFORNIA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("CALIFORNIA");

		} else if (selectedInput.equals("COLORADO")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("COLORADO");

		} else if (selectedInput.equals("CONNECTICUT")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("CONNECTICUT");

		} else if (selectedInput.equals("DELAWARE")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("DELAWARE");

		} else if (selectedInput.equals("DISTRICT OF COLUMBIA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("DISTRICT OF COLUMBIA");

		} else if (selectedInput.equals("FLORIDA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("FLORIDA");

		} else if (selectedInput.equals("GEORGIA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("GEORGIA");

		} else if (selectedInput.equals("GUAM")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("GUAM");

		} else if (selectedInput.equals("HAWAII")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("HAWAII");

		} else if (selectedInput.equals("IDAHO")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("IDAHO");

		} else if (selectedInput.equals("ILLINOIS")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("ILLINOIS");

		} else if (selectedInput.equals("INDIANA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("INDIANA");

		} else if (selectedInput.equals("IOWA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("IOWA");

		} else if (selectedInput.equals("KANSAS")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("KANSAS");

		} else if (selectedInput.equals("KENTUCKY")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("KENTUCKY");

		} else if (selectedInput.equals("LOUISIANA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("LOUISIANA");

		} else if (selectedInput.equals("MAINE")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MAINE");

		} else if (selectedInput.equals("MARYLAND")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MARYLAND");

		} else if (selectedInput.equals("MASSACHUSETTS")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MASSACHUSETTS");

		} else if (selectedInput.equals("MICHIGAN")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MICHIGAN");

		} else if (selectedInput.equals("MINNESOTA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MINNESOTA");

		} else if (selectedInput.equals("MISSISSIPPI")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MISSISSIPPI");

		} else if (selectedInput.equals("MISSOURI")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MISSOURI");

		} else if (selectedInput.equals("MONTANA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("MONTANA");

		} else if (selectedInput.equals("NEBRASKA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NEBRASKA");

		} else if (selectedInput.equals("NEVADA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NEVADA");

		} else if (selectedInput.equals("NEW HAMPSHIRE")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NEW HAMPSHIRE");

		} else if (selectedInput.equals("NEW JERSEY")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NEW JERSEY");

		} else if (selectedInput.equals("NEW MEXICO")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NEW MEXICO");

		} else if (selectedInput.equals("NEW YORK")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NEW YORK");

		} else if (selectedInput.equals("NORTH CAROLINA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NORTH CAROLINA");

		} else if (selectedInput.equals("NORTH DAKOTA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NORTH DAKOTA");

		} else if (selectedInput.equals("NORTH MARIANA ISLANDS")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("NORTH MARIANA ISLANDS");

		} else if (selectedInput.equals("OHIO")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("OHIO");

		} else if (selectedInput.equals("OKLAHOMA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("OKLAHOMA");

		} else if (selectedInput.equals("OREGON")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("OREGON");

		} else if (selectedInput.equals("PENNSYLVANIA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("PENNSYLVANIA");

		} else if (selectedInput.equals("PUERTO RICO")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("PUERTO RICO");

		} else if (selectedInput.equals("RHODE ISLAND")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("RHODE ISLAND");

		} else if (selectedInput.equals("SOUTH CAROLINA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("SOUTH CAROLINA");

		} else if (selectedInput.equals("SOUTH DAKOTA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("SOUTH DAKOTA");

		} else if (selectedInput.equals("TENNESSEE")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("TENNESSEE");

		} else if (selectedInput.equals("TEXAS")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("TEXAS");

		} else if (selectedInput.equals("UTAH")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("UTAH");

		} else if (selectedInput.equals("VERMONT")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("VERMONT");

		} else if (selectedInput.equals("VIRGIN ISLANDS")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("VIRGIN ISLANDS");

		} else if (selectedInput.equals("VIRGINIA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("VIRGINIA");

		} else if (selectedInput.equals("WASHINGTON")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("WASHINGTON");

		} else if (selectedInput.equals("WEST VIRGINIA")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("WEST VIRGINIA");

		} else if (selectedInput.equals("WISCONSIN")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("WISCONSIN");

		} else if (selectedInput.equals("WYOMING")) {
			dropList = new Select(
					driver.findElement(By.id("otherJurisdiction")));
			dropList.selectByVisibleText("WYOMING");

		}
	}

	
	/**
	 * This method will process the request for Intergovernmental referral search Result page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void intergovernmentalReferralRequestSearchResultsPage()
			throws InterruptedException {

		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		List<WebElement> storeFirstLevelMenu = getFirstLevelMenu();

		storeFirstLevelMenu.get(0).findElement(By.tagName("a")).click();

		List<WebElement> storeSecondLevelMenu = getSecondLevelMenu();

		storeSecondLevelMenu.get(5).findElement(By.tagName("a")).click();

		List<WebElement> storeSideMenu = getSideMenu();

		storeSideMenu.get(4).findElement(By.tagName("a")).click();

		String storeSearchType = intergovernmentalReferralRequestSearchResultsPageSelection();

		String countrytyped = processOtherCountryJurisdiction();

		String country = "UNITED STATES";
		if (countrytyped.equals(country) && storeSearchType.equals("General")) {
			IntergovernmentalReferralRequestSearchOtherJurisdiction();
		}

		Thread.sleep(6000);

		driver.findElement(By.id("searchButton")).click();

		int userInput = askUserToRunAgain();

		if (userInput == JOptionPane.OK_OPTION) {
			List<WebElement> storeSideMenus = getSideMenu();
			storeSideMenus.get(3).findElement(By.tagName("a")).click();
			intergovernmentalReferralRequestSearchResultsPage();
		}

		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	
	/**
	 * This method will check if the requested is present on the page.
	 * 
	 * @author Santosh Dubey.
	 */
	private static List<WebElement> noRecordsFound() {
		element = driver.findElement(By.xpath("//table[@class='listTable']"));
		List<WebElement> noRecordRows = element.findElements(By
				.xpath("//table[@class='listTable']/tbody"));
		List<WebElement> noRecordCols = null;
		for (WebElement w : noRecordRows) {
			noRecordCols = w.findElements(By
					.xpath("//table[@class='listTable']/tbody/tr"));

		}
		return noRecordCols;
	}

	
	/**
	 * This method will process the record if the record is available on the Page.
	 * 
	 * @author Santosh Dubey.
	 */
	private static List<WebElement> recordsFound() {
		element = driver.findElement(By.xpath("//table[@class='listTable']"));
		List<WebElement> RecordRows = element.findElements(By
				.xpath("//table[@class='listTable']/tbody/tr"));
		List<WebElement> RecordCols = null;

		for (WebElement w : RecordRows) {
			RecordCols = w.findElements(By
					.xpath("//table[@class='listTable']/tbody/tr/td"));

		}
		return RecordCols;
	}

	
	/**
	 * This method will process the request for Intergovernmental referral detail page.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void intergovernmentalReferralRequestDetailPage()
			throws InterruptedException {

		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		List<WebElement> storeFirstLevelMenu = getFirstLevelMenu();

		storeFirstLevelMenu.get(0).findElement(By.tagName("a")).click();

		List<WebElement> storeSecondLevelMenu = getSecondLevelMenu();

		storeSecondLevelMenu.get(5).findElement(By.tagName("a")).click();

		List<WebElement> storeSideMenu = getSideMenu();

		storeSideMenu.get(4).findElement(By.tagName("a")).click();

		String storeSearchType = intergovernmentalReferralRequestSearchResultsPageSelection();

		String countrytyped = processOtherCountryJurisdiction();

		String country = "UNITED STATES";
		if (countrytyped.equals(country) && storeSearchType.equals("General")) {
			IntergovernmentalReferralRequestSearchOtherJurisdiction();
		}

		Thread.sleep(6000);

		driver.findElement(By.id("searchButton")).click();

		List<WebElement> forNoRecords = noRecordsFound();

		String noRecords = "No Records";
		if (forNoRecords.get(0).getText().equals(noRecords)) {
			int userInput = askUserToRunAgain();

			if (userInput == JOptionPane.OK_OPTION) {
				List<WebElement> storeSideMenus = getSideMenu();
				storeSideMenus.get(3).findElement(By.tagName("a")).click();
				intergovernmentalReferralRequestSearchResultsPage();
			}

		} else {
			List<WebElement> recordsFound = recordsFound();
			recordsFound.get(1).findElement(By.tagName("a")).click();
		}

		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	
	/**
	 * This method will store all Jurisdiction name in a HashMap.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static String storeOtherCountryJurisdiction() {
		String Country = "";
		Map<String, String> otherCountryJurisdiction = new HashMap<String, String>();
		String[] inpCountry = { "UNITED STATES", "MEXICO", "AFGHANISTAN",
				"ALAND ISLANDS", "ALBANIA", "ALGERIA", "AMERICAN SAMOA",
				"ANDORRA", "ANGOLA", "ANGUILLA", "ANTARCTICA",
				"ANTIGUA AND BARBUDA", "ARGENTINA", "ARMENIA", "ARUBA",
				"ASHMORE AND CARTIER ISLANDS", "AUSTRALIA", "AUSTRIA",
				"AZERBAIJAN", "BAHAMAS", "BAHRAIN", "BAKER ISLAND",
				"BANGLADESH", "BARBADOS", "BASSAS DA INDIA", "BELARUS",
				"BELGIUM", "BELIZE", "BENIN", "BERMUDA", "BHUTAN", "BOLIVIA",
				"BONAIRE, SAINT EUSTATIUS AND SABA", "BOSNIA AND HERZEGOWINA",
				"BOTSWANA", "BOUVET ISLAND", "BRAZIL",
				"BRITISH INDIAN OCEAN TERRITORY", "BRUNEI DARUSSALAM",
				"BULGARIA", "BURKINA FASO", "BURUNDI", "CAMBODIA", "CAMEROON",
				"CANADA", "CAPE VERDE", "CAYMAN ISLANDS",
				"CENTRAL AFRICAN REprivate", "CHAD", "CHILE", "CHINA",
				"CHRISTMAS ISLAND", "CLIPPERTON ISLAND",
				"COCOS (KEELING) ISLANDS", "COLOMBIA", "COMOROS", "CONGO",
				"CONGO, THE DEMOCRATIC REprivate OF THE", "COOK ISLANDS",
				"CORAL SEA ISLANDS", "COSTA RICA", "COTE D'IVOIRE",
				"CROATIA (local name: Hrvatska)", "CUBA", "CURACAO", "CYPRUS",
				"CZECH REprivate", "DENMARK", "DJIBOUTI", "DOMINICA",
				"DOMINICAN REprivate", "EAST TIMOR", "ECUADOR", "EGYPT",
				"EL SALVADOR", "EQUATORIAL GUINEA", "ERITREA", "ESTONIA",
				"ETHIOPIA", "EUROPA ISLAND", "FALKLAND ISLANDS (MALVINAS)",
				"FAROE ISLANDS", "FIJI", "FINLAND", "FRANCE", "FRENCH GUIANA",
				"FRENCH POLYNESIA", "FRENCH SOUTHERN TERRITORIES", "GABON",
				"GAMBIA", "GAZA STRIP", "GEORGIA", "GERMANY", "GHANA",
				"GIBRALTAR", "GLORIOSO ISLANDS", "GREECE", "GREENLAND",
				"GRENADA", "GUADELOUPE", "GUATEMALA", "GUERNSEY", "GUINEA",
				"GUINEA-BISSAU", "GUYANA", "HAITI",
				"HEARD AND MC DONALD ISLANDS", "HONDURAS", "HONG KONG",
				"HOWLAND ISLAND", "HUNGARY", "ICELAND", "INDIA", "INDONESIA",
				"IRAN (ISLAMIC REprivate OF)", "IRAQ", "IRELAND",
				"ISLE OF MAN", "ISRAEL", "ITALY", "JAMAICA", "JAPAN",
				"JARVIS ISLAND", "JERSEY", "JOHNSTON ATOLL", "JORDAN",
				"JUAN DE NOVA ISLAND", "KAZAKHSTAN", "KENYA", "KINGMAN REEF",
				"KIRIBATI", "KOREA, DEMOCRATIC PEOPLE'S REprivate OF",
				"KOREA, REprivate OF", "KUWAIT", "KYRGYZSTAN",
				"LAO PEOPLE'S DEMOCRATIC REprivate", "LATVIA", "LEBANON",
				"LESOTHO", "LIBERIA", "LIBYAN ARAB JAMAHIRIYA",
				"LIECHTENSTEIN", "LITHUANIA", "LUXEMBOURG", "MACAU",
				"MACEDONIA, THE FORMER YUGOSLAV REprivate OF", "MADAGASCAR",
				"MALAWI", "MALAYSIA", "MALDIVES", "MALI", "MALTA",
				"MARSHALL ISLANDS", "MARTINIQUE", "MAURITANIA", "MAURITIUS",
				"MAYOTTE", "MICRONESIA, FEDERATED STATES OF", "MIDWAY ISLANDS",
				"MOLDOVA, REprivate OF", "MONACO", "MONGOLIA", "MONTENEGRO",
				"MONTSERRAT", "MOROCCO", "MOZAMBIQUE", "MYANMAR", "NAMIBIA",
				"NAURU", "NAVASSA ISLAND", "NEPAL", "NETHERLANDS",
				"NETHERLANDS ANTILLES", "NEW CALEDONIA", "NEW ZEALAND",
				"NICARAGUA", "NIGER", "NIGERIA", "NIUE", "NORFOLK ISLAND",
				"NORTHERN MARIANA ISLANDS", "NORWAY", "OMAN", "PAKISTAN",
				"PALAU", "PALESTINIAN TERRITORY, OCCUPIED", "PALMYRA ATOLL",
				"PANAMA", "PAPUA NEW GUINEA", "PARACEL ISLANDS", "PARAGUAY",
				"PERU", "PHILIPPINES", "PITCAIRN", "POLAND", "PORTUGAL",
				"PUERTO RICO", "QATAR", "REUNION", "ROMANIA",
				"RUSSIAN FEDERATION", "RWANDA", "SAINT BARTHELEMY",
				"SAINT KITTS AND NEVIS", "SAINT LUCIA",
				"SAINT MARTIN (FRENCH PART)",
				"SAINT VINCENT AND THE GRENADINES", "SAMOA", "SAN MARINO",
				"SAO TOME AND PRINCIPE", "SAUDI ARABIA", "SENEGAL", "SERBIA",
				"SERBIA AND MONTENEGRO", "SEYCHELLES", "SIERRA LEONE",
				"SINGAPORE", "SINT MAARTEN (DUTCH PART)",
				"SLOVAKIA (SLOVAK REprivate)", "SLOVENIA", "SOLOMON ISLANDS",
				"SOMALIA", "SOUTH AFRICA",
				"SOUTH GEORGIA AND THE SOUTH SANDWICH ISLANDS", "SPAIN",
				"SPRATLY ISLANDS", "SRI LANKA", "ST. HELENA",
				"ST. PIERRE AND MIQUELON", "SUDAN", "SURINAME",
				"SVALBARD AND JAN MAYEN ISLANDS", "SWAZILAND", "SWEDEN",
				"SWITZERLAND", "SYRIAN ARAB REprivate", "TAIWAN", "TAJIKISTAN",
				"TANZANIA, UNITED REprivate OF", "THAILAND", "TOGO", "TOKELAU",
				"TONGA", "TRINIDAD AND TOBAGO", "TROMELIN ISLAND", "TUNISIA",
				"TURKEY", "TURKMENISTAN", "TURKS AND CAICOS ISLANDS", "TUVALU",
				"UGANDA", "UKRAINE", "UNITED ARAB EMIRATES", "UNITED KINGDOM",
				"UNITED STATES MINOR OUTLYING ISLANDS", "UNKNOWN", "URUGUAY",
				"UZBEKISTAN", "VANUATU", "VATICAN CITY STATE (HOLY SEE)",
				"VENEZUELA", "VIETNAM", "VIRGIN ISLANDS (BRITISH)",
				"VIRGIN ISLANDS (U.S.)", "WALLIS AND FUTUNA ISLANDS",
				"WESTERN SAHARA", "YEMEN", "ZAMBIA", "ZIMBABWE" };
		String[] inpCountryCode = { "US", "MX", "AF", "AX", "AL", "DZ", "AS",
				"AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AC", "AU",
				"AT", "AZ", "BS", "BH", "FQ", "BD", "BB", "BX", "BY", "BE",
				"BZ", "BJ", "BM", "BT", "BO", "BQ", "BA", "BW", "BV", "BR",
				"IO", "BN", "BG", "BF", "BI", "KH", "CM", "CA", "CV", "KY",
				"CF", "TD", "CL", "CN", "CX", "IP", "CC", "CO", "KM", "CG",
				"CD", "CK", "CB", "CR", "CI", "HR", "CU", "CW", "CY", "CZ",
				"DK", "DJ", "DM", "DO", "TL", "EC", "EG", "SV", "GQ", "ER",
				"EE", "ET", "EU", "FK", "FO", "FJ", "FI", "FR", "GF", "PF",
				"TF", "GA", "GM", "GZ", "GE", "DE", "GH", "GI", "GO", "GR",
				"GL", "GD", "GP", "GT", "GG", "GN", "GW", "GY", "HT", "HM",
				"HN", "HK", "HQ", "HU", "IS", "IN", "ID", "IR", "IQ", "IE",
				"IM", "IL", "IT", "JM", "JP", "DQ", "JE", "JQ", "JO", "JU",
				"KZ", "KE", "KQ", "KI", "KP", "KR", "KW", "KG", "LA", "LV",
				"LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MK", "MG",
				"MW", "MY", "MV", "ML", "MT", "MH", "MQ", "MR", "MU", "YT",
				"FM", "MI", "MD", "MC", "MN", "ME", "MS", "MA", "MZ", "MM",
				"NA", "NR", "BQ", "NP", "NL", "AN", "NC", "NZ", "NI", "NE",
				"NG", "NU", "NF", "MP", "NO", "OM", "PK", "PW", "PS", "LQ",
				"PA", "PG", "PI", "PY", "PE", "PH", "PN", "PL", "PT", "PR",
				"QA", "RE", "RO", "RU", "RW", "BL", "KN", "LC", "MF", "VC",
				"WS", "SM", "ST", "SA", "SN", "RS", "CS", "SC", "SL", "SG",
				"SX", "SK", "SI", "SB", "SO", "ZA", "GS", "ES", "SP", "LK",
				"SH", "PM", "SD", "SR", "SJ", "SZ", "SE", "CH", "SY", "TW",
				"TJ", "TZ", "TH", "TG", "TK", "TO", "TT", "TE", "TN", "TR",
				"TM", "TC", "TV", "UG", "UA", "AE", "GB", "UM", "ZZ", "UY",
				"UZ", "VU", "VA", "VE", "VN", "VG", "VI", "WF", "EH", "YE",
				"ZM", "ZW" };
		ArrayList<String> key = new ArrayList<String>();
		ArrayList<String> value = new ArrayList<String>();

		for (int i = 0; i < inpCountry.length; i++) {
			key.add(inpCountryCode[i]);
			value.add(inpCountry[i]);
			otherCountryJurisdiction.put(key.get(i), value.get(i));

		}

		String storeCountryCode = takeInputFromUser();
		System.out.println("Typed Country Code: " + storeCountryCode);

		for (Entry<String, String> key1 : otherCountryJurisdiction.entrySet()) {
			String s = key1.getKey();
			if (s.equals(storeCountryCode.toUpperCase())) {

				System.out.println("Country received: " + key1.getValue());
				Country = key1.getValue();
			}
		}

		System.out.println("-----------------------------------------------");

		return Country;

	}

	
	/**
	 * This method will process all Jurisdiction name from a HashMap.
	 * 
	 * @author Santosh Dubey.
	 */
	private static String processOtherCountryJurisdiction()
			throws InterruptedException {
		String selectedInput = storeOtherCountryJurisdiction();
		System.out.println("You selected: " + selectedInput);
		dropList = new Select(driver.findElement(By
				.id("otherJurisdictionCountry")));
		dropList.selectByVisibleText(selectedInput);
		return selectedInput;
	}

	
	/**
	 * This method will process the request for Intergovernmental Status Request Search Page
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void intergovernmentalStatusRequestSearchPage()
			throws InterruptedException {

		System.out.println("Entering +" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");

		List<WebElement> storeFirstLevelMenu = getFirstLevelMenu();

		storeFirstLevelMenu.get(0).findElement(By.tagName("a")).click();

		List<WebElement> storeSecondLevelMenu = getSecondLevelMenu();

		storeSecondLevelMenu.get(5).findElement(By.tagName("a")).click();

		List<WebElement> storeSideMenu = getSideMenu();

		storeSideMenu.get(6).findElement(By.tagName("a")).click();

		String countrytyped = processOtherCountryJurisdiction();

		String country = "UNITED STATES";
		if (countrytyped.equals(country)) {
			IntergovernmentalReferralRequestSearchOtherJurisdiction();
		}

		driver.findElement(By.id("searchButton")).click();

		List<WebElement> forNoRecords = noRecordsFound();

		String noRecords = "No Records";
		if (forNoRecords.get(0).getText().equals(noRecords)) {
			int userInput = askUserToRunAgain();

			if (userInput == JOptionPane.OK_OPTION) {
				List<WebElement> storeSideMenus = getSideMenu();
				storeSideMenus.get(3).findElement(By.tagName("a")).click();
				intergovernmentalStatusRequestSearchPage();
			}

		} else {
			List<WebElement> recordsFound = recordsFound();
			recordsFound.get(0).findElement(By.tagName("a")).click();
		}

		System.out.println();
		System.out.println("Exiting -" + new Object() {
		}.getClass().getEnclosingMethod().getName() + "()");
	}

	
	/**
	 * Close the browser once all request is processed.
	 * 
	 * @author Santosh Dubey.
	 */
	protected static void closeBrowser() {
		driver.close();
		driver.quit();
	}

}