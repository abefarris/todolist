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
import data.TaskNames;

public class ViewTest {
	
	private final int testPort = 3333;
	private final String testHost = "localhost";
	
	 
	@Test
	public void testIndex() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = new IndexPage(
							browser.getDriver(), testHost, testPort, 0);
					browser.goTo(indexPage);
					indexPage.isAt();
	
				}
			});
	}
	
	@Test
	public void testSingleItem() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = new IndexPage(
							browser.getDriver(), testHost, testPort, 0);
					browser.goTo(indexPage);
					indexPage.isAt();
					
					String[] taskNames = new TaskNames().getTaskNames(1); 
					
					for(int i = 1; i < taskNames.length ; i++) {
						indexPage.submitForm(taskNames[i]);
						assertThat(browser.pageSource()).contains(taskNames[i]);
					}
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 1 of 1");
		
				}
			});
	}

	@Test
	public void testFullPageItems() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = new IndexPage(
							browser.getDriver(), testHost, testPort, 0);
					browser.goTo(indexPage);
					indexPage.isAt();
					
					String[] taskNames = new TaskNames().getTaskNames(10); 
					
					for(int i = 1; i < taskNames.length ; i++) {
						indexPage.submitForm(taskNames[i]);
						assertThat(browser.pageSource()).contains(taskNames[i]);
					}
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 10 of 10");
		
				}
			});
	}
	
	@Test
	public void testMultiPageItems() {
		running(testServer(testPort, fakeApplication(inMemoryDatabase())), HTMLUNIT,
			new Callback<TestBrowser>() {
				public void invoke(TestBrowser browser) {
	
					IndexPage indexPage = new IndexPage(
							browser.getDriver(), testHost, testPort, 0);
					browser.goTo(indexPage);
					indexPage.isAt();
					
					String[] taskNames = new TaskNames().getTaskNames(15); 
					
					for(int i = 1; i < taskNames.length ; i++) {
						indexPage.submitForm(taskNames[i]);
						assertThat(browser.pageSource()).contains(taskNames[i]);
					}
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 10 of 15");
					
					indexPage.getPagerNext().click();
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("11 to 15 of 15");
					
					indexPage.getPagerPrev().click();
					
					assertThat(indexPage.getPagerDisplay()).isEqualTo("1 to 10 of 15");
		
				}
			});
	}		

}
