package pages;

import static org.fest.assertions.Assertions.assertThat;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.WebDriver;

public class IndexPage extends FluentPage {
	
	private String url;
	
	 public IndexPage(WebDriver webDriver, String host, int port, int page) {
		    super(webDriver);
		    this.url = "http://" + host + ":" + port + "/?p=" + page;
		  }
	 
	 @Override
	  public String getUrl() {
	    return this.url;
	  }
	 
	 @Override
	  public void isAt() {
	    assertThat(title()).isEqualTo("Todo list");
	  }
	 
	 public void submitForm(String taskName) {
		 fill("#taskName").with(taskName);
		 submit("#addButton");
	 }
	 
	 public FluentWebElement getPagerPrev(){
		 return find("#previous a").get(0);
	 }
	 
	 public FluentWebElement getPagerNext(){
		 return find("#next a").get(0);
	 }
	 
	 public String getPagerDisplay(){
		 return find("#pagerCurrent").getText();
	 }
	 
	 

}
