import java.util.ArrayList; 
import java.util.Arrays;

class Main {
	public static void main(String[] args) {
		DatasetCSV training_examples = new DatasetCSV("iris.csv");
		ArrayList<ArrayList<Double>> data = training_examples.getExamples();
		ArrayList<ArrayList<String>> class_labels = training_examples.getClassLabels();
		String[] feature_labels = training_examples.getFeatureLabels();
	}	
}
