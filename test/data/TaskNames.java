package data;

import java.util.ArrayList;
import java.util.List;

public class TaskNames {
	
	private List<String> taskNames;
	private final String testingValue = "testing";

	public String[] getTaskNames(int count) {
		
		taskNames = new ArrayList<String>();
		
		for(int i = 0; i <= count ; i++) {
			taskNames.add(testingValue + i + 1);
		}
		
		return taskNames.toArray(new String[taskNames.size()]);
	}

}
