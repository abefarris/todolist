package tests;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.inMemoryDatabase;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.junit.Test;

import pages.IndexPage;
import play.libs.F.Callback;
import play.test.TestBrowser;
import data.TaskNameGenerator;

public class ConstraintTest extends TestBase {
	
	@Test
	public void testInvalidLengthItem() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = gotoIndexPage(browser);
					indexPage.isAt();
					
					submitNames(browser, indexPage, new TaskNameGenerator().getInvalidLengthName(1));
					
					//assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 1 of 1");
		
				}

			});
	}

}
