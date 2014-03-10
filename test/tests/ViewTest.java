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

public class ViewTest extends TestBase {
	
	 
	@Test
	public void testIndex() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = gotoIndexPage(browser);
					indexPage.isAt();
	
				}
			});
	}
	
	@Test
	public void testSingleItem() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = gotoIndexPage(browser);
					indexPage.isAt();
					
					submitNames(browser, indexPage, new TaskNameGenerator().getTaskNames(1));
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 1 of 1");
		
				}

			});
	}

	@Test
	public void testFullPageItems() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = gotoIndexPage(browser);
					indexPage.isAt();
					
					submitNames(browser, indexPage, new TaskNameGenerator().getTaskNames(10));
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 10 of 10");
		
				}
			});
	}
	
	@Test
	public void testMultiPageItems() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = gotoIndexPage(browser);
					indexPage.isAt();
				
					submitNames(browser, indexPage, new TaskNameGenerator().getTaskNames(15));
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 10 of 15");
					
					indexPage.getPagerNext().click();
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("11 to 15 of 15");
					
					indexPage.getPagerPrev().click();
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 10 of 15");
		
				}
			});
	}		

}
