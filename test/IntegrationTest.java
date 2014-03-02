import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import pages.IndexPage;
import play.libs.F.Callback;
import play.test.TestBrowser;
import data.TaskNames;

@RunWith(Parameterized.class)
public class IntegrationTest {

	private final int testPort = 3333;
	private final String testHost = "localhost";
	
	private String[] taskNames;
	private String expected;
	

	public IntegrationTest(String taskNames[], String expected) {
		this.taskNames = taskNames;
		this.expected = expected;
	}

	@Parameterized.Parameters(name = "{index}: nextPagerEnabled {1}")
	 public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { new TaskNames().getTaskNames(9), "next disabled"}, 
                { new TaskNames().getTaskNames(10), "next"}, 
           });
	 }
	 
	@Test
	public void test() {
		running(testServer(testPort, fakeApplication()), HTMLUNIT,
				new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {

						IndexPage indexPage = new IndexPage(
								browser.getDriver(), testHost, testPort, 0);
						browser.goTo(indexPage);
						indexPage.isAt();
						
						for(int i = 1; i < taskNames.length ; i++) {
							indexPage.submitForm(taskNames[i]);
							assertThat(browser.pageSource()).contains(taskNames[i]);
						}
						
						assertThat(indexPage.pagerNextClass()).isEqualTo(expected);
						assertThat(indexPage.pagerNextClass()).isEqualTo(expected);

					}
				});
	}
}
