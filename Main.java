import java.util.ArrayList; 
import java.util.Arrays;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class Main {
	public static void main(String[] args) {
		System.out.println("KNN-Classification");
		Dataset dataset = new Dataset("iris_shuffled_matlab.csv");
		KNNClassifier classification = new KNNClassifier(dataset.getTrainingExamples(), dataset.getTrainingClass(), 1);
		ArrayList<String> predictions = classification.predict(dataset.getTestExamples());
		
		// Map<String, Integer> occurrences = new Map<String, Integer>();
		
		// for(int i = 0; i < predictions.size(); i++) {
		// 	if(occurrences.containsKey(predictions.get(i))) {
		// 		occurrences.put(predictions.get(i), occurrences.get(predictions.get(i)) + 1);
		// 	} else {
		// 		occurrences.put(predictions.get(i), 1);
		// 	}
		// }

		// for(String prediction : occurrences.keySet()) {
		// 	System.out.println("Predicton " + prediction + " was predicicted " + predictions.get(i));
		// }

		ArrayList<String> test_class = dataset.getTestClass();


		int correctPredictions = 0;
		int incorrectPredictions = 0;

		for(int i = 0; i < test_class.size(); i++) {
			if(test_class.get(i).equals(predictions.get(i))) {
				correctPredictions += 1;
			} else {
				incorrectPredictions += 1;
			}
		}

		double classificationAccuracy = Double.valueOf(correctPredictions) / Double.valueOf(test_class.size()) * 100;

		System.out.println("Correct predictions: " + correctPredictions);
		System.out.println("Incorrect predictions: " + incorrectPredictions);
		System.out.println("Classification accuracy: " + classificationAccuracy + "%");
	}
}