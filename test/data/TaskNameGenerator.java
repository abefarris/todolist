package data;

import java.util.ArrayList;
import java.util.List;

public class TaskNameGenerator {

	private List<String> taskNames;

	private final String testingValue = "testing";

	// 255+ chars
	private final String invalidLengthValue = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
			+ " Suspendisse ornare et velit a luctus. Suspendisse at gravida felis, non commodo ipsum. Proin"
			+ " vel purus sit amet elit scelerisque pulvinar eget tincidunt nulla. Aenean hendrerit sollicitudin posuere.";
	
	private final String invalidEmptyValue = "";

	public String[] getTaskNames(int count) {
		return getTaskNames(count, testingValue);
	}

	public String[] getInvalidLengthName(int count) {
		return getTaskNames(count, invalidLengthValue);
	}
	
	public String[] getInvalidEmptyName(int count) {
		return getTaskNames(count, invalidEmptyValue);
	}
	

	private String[] getTaskNames(int count, String value) {

		taskNames = new ArrayList<String>();

		for (int i = 0; i <= count; i++) {
			taskNames.add(value + i + 1);
		}

		return taskNames.toArray(new String[taskNames.size()]);
	}

}
