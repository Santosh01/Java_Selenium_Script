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

		String[] page = { "Incoming Interstate Referral Search Results",
				"Incoming Interstate Referral Detail",
				"Incoming Interstate Referral Support Order Detail Page",
				"Case Overview Page", "Interstate Case List Page",
				"Interstate Case Detail Page",
				"Generate Interstate Transaction Page",
				"Interstate Transaction Search Results Page",
				"Form Set Parameter Search Page",
				"Intergovernmental Referral Request Search Results Page",
				"Intergovernmental Referral Request Detail Page",
				"Intergovernmental Status Request Search Page" };

		String selectedPage = selectPage(page);

		switch (selectedPage) {
		case "Incoming Interstate Referral Search Results":
			incomingInterstateReferralSearchResultsPage();
			break;

		case "Incoming Interstate Referral Detail":
			incomingInterstateReferralDetailPage();
			break;

		case "Incoming Interstate Referral Support Order Detail Page":
			IncomingInterstateReferralSupportOrderDetailPage();
			break;

		case "Case Overview Page":
			caseSearchPage();
			break;

		case "Interstate Case List Page":
			interstateCaseListPage();
			break;

		case "Interstate Case Detail Page":
			interstateCaseDetailPage();
			break;

		case "Generate Interstate Transaction Page":
			generateInterstateTransactionPage();
			break;

		case "Interstate Transaction Search Results Page":
			interstateTransactionSearchResultsPage();
			break;

		case "Form Set Parameter Search Page":
			formSetParameterSearchPage();
			break;

		case "Intergovernmental Referral Request Search Results Page":
			intergovernmentalReferralRequestSearchResultsPage();
			break;

		case "Intergovernmental Referral Request Detail Page":
			intergovernmentalReferralRequestDetailPage();
			break;

		case "Intergovernmental Status Request Search Page":
			intergovernmentalStatusRequestSearchPage();
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
