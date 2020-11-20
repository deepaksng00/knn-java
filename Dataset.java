import java.util.ArrayList;

class Dataset extends DatasetCSV {
	private ArrayList<ArrayList<Double>> test_examples = new ArrayList<ArrayList<Double>>();
	private ArrayList<String> test_class = new ArrayList<String>();
	private ArrayList<ArrayList<Double>> training_examples = new ArrayList<ArrayList<Double>>();
	private ArrayList<String> training_class = new ArrayList<String>();

	Dataset(String filename) {
		/* Parsing CSV file to collect all relevant data into separate data structures */
		super(filename);
		holdout();
	}

	/* Holdout process to extract testing data and training data for the classifier */
	private void holdout() {
		int numOfTests = (int)Math.round(data.size() * 0.25);

		for(int row_index = 0; row_index < numOfTests; row_index++) {
			test_examples.add(data.get(row_index));
			test_class.add(class_labels.get(row_index));
		} 

		int numOfTraining = data.size() - numOfTests;

		for(int row_index = numOfTests; row_index < data.size(); row_index++) {
			training_examples.add(data.get(row_index));
			training_class.add(class_labels.get(row_index));
		}
	}

	public ArrayList<ArrayList<Double>> getTestExamples() {
		return test_examples;
	}

	public ArrayList<String> getTestClass() {
		return test_class;
	}

	public ArrayList<ArrayList<Double>> getTrainingExamples() {
		return training_examples;
	}

	public ArrayList<String> getTrainingClass() {
		return training_class;
	}
}