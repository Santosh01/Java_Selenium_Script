package developmentEnvironment;

import java.io.File;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

/**
 * This class is the core component of Agile Selenium process, All the set-up
 * initialization will happen in this class.<br>
 * 
 * <br>
 * There are <b>TWO</b> ways to access an Application:
 * 
 * <pre>
 *   1. UIT/PROD environment
 *   2. LOCAL Development Environment
 * </pre>
 * 
 * @author Santosh Dubey.
 */
public class Initialization {
	
	//------Static variable Declaration--------
	protected static JavascriptExecutor executor;
	protected static File ieDriver;
	protected static DesiredCapabilities capabilities;
	protected static Select dropList;
	protected static WebDriver driver = null;
	protected static WebElement element;
	protected static List<WebElement> allRows;
	protected static List<WebElement> columns;
	protected static List<WebElement> ul;
	
	// Can be replaced with your own username and password
	protected static String username = "";
	protected static String password  = "";

	// Change this if driver is located in some other path
	protected static String ieDriverPath = "C:\\rad_workspaces\\sdubey_RAD8_JAVA_Pgms\\libs\\IEDriverServer_Win32_2.53.1\\IEDriverServer.exe";

	// Can be enabled for Remote application
	protected static String linkToOpen = "";

	// Can be enabled for Local application
	// protected static String linkToOpen = "";

	// Incoming Interstate ReferralSearch Page, can be enabled for Remote application
	protected static String link_For_Incomming_Interstate_Referral_Search_Page = "";

	// Incoming Interstate ReferralSearch Page , can be enabled for Local application
	// protected static String linkForIncommingInterstateReferralSearchPage = "";
	
	
}

