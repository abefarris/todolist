import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.HTMLUNIT;
import static play.test.Helpers.fakeApplication;
import static play.test.Helpers.running;
import static play.test.Helpers.testServer;

import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.junit.Test;

import play.libs.F.Callback;
import play.test.TestBrowser;


public class IntegrationTest {

	@Test
	public void test() {
		running(testServer(3333, fakeApplication()), HTMLUNIT,
				new Callback<TestBrowser>() {
					public void invoke(TestBrowser browser) {

						String testingValue = "testing";
						int testingCount = 10;
						browser.goTo("http://localhost:3333");
						
						for (int i = 0; i < testingCount; i++) {

							assertThat(browser.$("legend").first().getText())
									.isEqualTo(i + " Task(s)");
							browser.$("input").text(testingValue + i);
							browser.$("button[name='addButton']").click();

						}

						FluentList<FluentWebElement> formList;

						do {
							formList = browser.$(".list-group-item form");
							formList.get(0).submit();
						} while (formList.size()>1);
					}
				});
	}
}

