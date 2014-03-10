package tests;

import static org.fest.assertions.Assertions.assertThat;
import pages.IndexPage;
import play.test.TestBrowser;

public class TestBase {
	
	protected final int testPort = 3333;
	protected final String testHost = "localhost";
	
	protected IndexPage gotoIndexPage(TestBrowser browser) {
		IndexPage indexPage = new IndexPage(
				browser.getDriver(), testHost, testPort, 0);
		browser.goTo(indexPage);
		return indexPage;
	}
	
	protected void submitNames(TestBrowser browser,
			IndexPage indexPage, String[] taskNames) {
		for(int i = 1; i < taskNames.length ; i++) {
			indexPage.submitForm(taskNames[i]);
			assertThat(browser.pageSource()).contains(taskNames[i]);
		}
	}

}
