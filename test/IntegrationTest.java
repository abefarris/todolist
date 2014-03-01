import org.junit.*;

import play.test.*;
import play.libs.F.*;
import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


public class IntegrationTest {
	
	
	 @Test
	    public void test() {
	        running(testServer(3333, fakeApplication()), HTMLUNIT, new Callback<TestBrowser>() {
	            public void invoke(TestBrowser browser) throws InterruptedException {
	            	String testingValue = "testing";
	                browser.goTo("http://localhost:3333");
	                assertThat(browser.$("legend").first().getText()).isEqualTo("0 Task(s)");
	                
	                browser.$("input").text(testingValue);
	                browser.$("button").click();
	                
	                assertThat(browser.$("legend").first().getText()).isEqualTo("1 Task(s)");
	                if (browser.$(".todo-list-item").getText().contains(testingValue)){
	                	browser.$(".list-group-item * button").submit();
	                }

	                assertThat(browser.$("legend").first().getText()).isEqualTo("0 Task(s)");
	                
	                
	            }
	        });
	    }

}
