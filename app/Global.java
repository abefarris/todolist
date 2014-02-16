import play.Application;
import play.GlobalSettings;
import play.Logger;



public class Global extends GlobalSettings {
	  @Override
	  public void onStart(Application app) {
	    Logger.info("Application has started");
	  }  
	  
	  @Override
	  public void onStop(Application app) {
	    Logger.info("Application shutdown...");
	  }
	  
//	  @Override
//	    public Promise<SimpleResult> onError(RequestHeader request, Throwable t) {
//	        return Promise.<SimpleResult>pure(internalServerError(
//	            views.html.error.render(t)
//	        ));
//	    }
}