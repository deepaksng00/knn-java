import java.util.ArrayList; 
import java.util.Arrays;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class Main {
	public static void main(String[] args) {
		
		/* Parsing CSV file to collect all relevant data into separate data structures */
		DatasetCSV dataset = new DatasetCSV("iris_shuffled_matlab.csv");
		ArrayList<ArrayList<Double>> data = dataset.getExamples();
		ArrayList<ArrayList<String>> class_labels = dataset.getClassLabels();
		String[] feature_labels = dataset.getFeatureLabels();

		/* Holdout process to extract testing data and training data for the classifier */
		int numOfTests = (int)Math.round(data.size() * 0.25);

		ArrayList<ArrayList<Double>> test_examples = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<String>> test_class = new ArrayList<ArrayList<String>>();

		for(int row_index = 0; row_index < numOfTests; row_index++) {
			test_examples.add(data.get(row_index));
			test_class.add(class_labels.get(row_index));
		} 

		int numOfTraining = data.size() - numOfTests;
		ArrayList<ArrayList<Double>> training_examples = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<String>> training_class = new ArrayList<ArrayList<String>>();

		for(int row_index = numOfTests; row_index < data.size(); row_index++) {
			training_examples.add(data.get(row_index));
			training_class.add(class_labels.get(row_index));
		}

		KNNClassifier classification = new KNNClassifier(training_examples, training_class, 10);
	}	
}
