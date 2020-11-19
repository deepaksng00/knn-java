import java.util.ArrayList; 
import java.util.Arrays;
import java.lang.Math;
import java.math.RoundingMode;
import java.text.DecimalFormat;

class Main {
	public static void main(String[] args) {
		Dataset dataset = new Dataset("iris_shuffled_matlab.csv");
		KNNClassifier classification = new KNNClassifier(dataset.getTrainingExamples(), dataset.getTrainingClass(), 10);
		classification.predict(dataset.getTestExamples());
	}
}