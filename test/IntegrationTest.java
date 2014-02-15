import org.junit.*;

import play.test.*;
import play.libs.F.*;

import static play.test.Helpers.*;
import static org.fest.assertions.Assertions.*;


public class IntegrationTest {
	
	
	 @Test
	    public void test() {
	        running(testServer(3333, fakeApplication()), HTMLUNIT, new Callback<TestBrowser>() {
	            public void invoke(TestBrowser browser) {
	            	String testingValue = "testing";
	                browser.goTo("http://localhost:3333");
	                assertThat(browser.$("h1").first().getText()).isEqualTo("0 task(s)");
	                browser.$("#label").text(testingValue);
	                browser.$("input").click();
	                assertThat(browser.$("h1").first().getText()).isEqualTo("1 task(s)");
	                if (browser.$("li").getText().contains(testingValue)){
	                	browser.$("li * input[value='Delete']").click();
	                }

	                assertThat(browser.$("h1").first().getText()).isEqualTo("0 task(s)");
	                
	                
	            }
	        });
	    }

}
