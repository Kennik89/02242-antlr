package DetectionOfSigns;

import java.util.LinkedList;

public class Results {
	LinkedList<ResultVariable> results = new LinkedList<>();
	
	public Results(String[] names) {
		for (int j = 0; j < names.length; j++) {
			results.add(new ResultVariable(names[j]));
		}
	}
	
}
