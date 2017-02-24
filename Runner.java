package developmentEnvironment;

import javax.swing.JOptionPane;

/**
 * This method will pop-up a window for page selection. <br>
 * Based on the requested page <b>Selenium</b> redirect user on the selected
 * Page.<br>
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

public class Runner extends PageModule {

	/**
	 * This method will pop-up a window for page selection. <br>
	 * Based on the requested page <b>Selenium</b> redirect user on the selected
	 * Page.
	 * 
	 * @author Santosh Dubey.
	 */
	public static String takeInputFromUser() throws InterruptedException {

		String[] page = { "Page Name" };

		String selectedPage = selectPage(page);

		switch (selectedPage) {
		case "Page1":
			Page1();
			break;

		case "Page2":
			Page2();
			break;

		case "Page3":
			Page3();
			break;

		case "Page4":
			Page4();
			break;

		case "Page5":
			Page5();
			break;

		case "Page6":
			Page6();
			break;

		case "Page7":
			Page7();
			break;

		case "Page8":
			Page8();
			break;

		case "Page9":
			Page9();
			break;

		case "Page10":
			Page10();
			break;

		case "Page11":
			Page11();
			break;

		case "Page12":
			Page12();
			break;

		default:
			System.out.println("Oops! You selected wrong page.");

		}

		System.out.println(selectedPage);
		return selectedPage;

	}

	public static String selectPage(String[] page) {
		
		String selectedInput = (String) JOptionPane.showInputDialog(null,
					"Please select page", "Page Selection",
					JOptionPane.PLAIN_MESSAGE, null, page, page[0]);
		
		return selectedInput;
	}

	public static void main(String[] Args) throws InterruptedException {

		openBrowser();

		// Enable it when working on remote environment

		validate_CSEEnvironment_UserCredential();

		// Enable it when working on local environment
		// Validate_localhost_UserCredential();

		takeInputFromUser();

		int input = JOptionPane.showConfirmDialog(null,
				"Do you want to close this window now?", "Confirmation",
				JOptionPane.YES_NO_OPTION);

		if (input == JOptionPane.YES_OPTION) {
			closeBrowser();
		}

	}

}
