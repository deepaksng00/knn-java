import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.Map;

class KNNClassifier {
	public ArrayList<ArrayList<Double>> training_examples; // storing training examples for the training of the classifier
	public ArrayList<String> training_labels; // storing class labels related to the training examples
	public int k; // stores hyperparameter "k" nearest neighbours
	public ArrayList<Double> mean;
	public ArrayList<Double> std;

	KNNClassifier(ArrayList<ArrayList<Double>> training_examples, ArrayList<String> training_labels, int k) {	
		this.training_examples = training_examples;
		this.training_labels = training_labels;
		this.k = k;

		/* starting standardisation process with z-score standardisation */

		/* calculating mean */
		this.mean = mean();

		/* calculating std of each feature column */
		this.std = std();

		/* standarding the data */
		standardiseData(training_examples);

		/* end of standardisation process */
	}

	/* finds the mean of each training example value */
	private ArrayList<Double> mean() {
		ArrayList<Double> mean = new ArrayList<Double>();

		/* calculating mean of each feature column */

		for(int i = 0; i < training_examples.get(0).size(); i++) {
			double column = mean_of_column(i);
			mean.add(column);
		}

		return mean;
	}

	/* finds the standard deviation of each training example value */
	private ArrayList<Double> std() {
		ArrayList<Double> std = new ArrayList<Double>();

		for(int i = 0; i < training_examples.get(0).size(); i++) {
			double column = std_of_column(i);
			std.add(column);
		}

		return std;
	}

	/* finds the mean for one feature in the training examples */
	private double mean_of_column(int column_index) {
		double total = 0;
		for(int row_index = 0; row_index < training_examples.size(); row_index++) {
			total += training_examples.get(row_index).get(column_index);
		}
		return total / training_examples.size();
	}

	/* finds the standard deviation for one feature in the training examples */
	private double std_of_column(int column_index) {
		double current_mean = mean.get(column_index);
		double sumOfDifferences = 0;

		for(int row_index = 0; row_index < training_examples.size(); row_index++) {
			double currentValue = training_examples.get(row_index).get(column_index) - current_mean;
			double currentSquared = Math.pow(currentValue, 2);
			sumOfDifferences += currentSquared;
		}

		double variance = sumOfDifferences / training_examples.size();

		return Math.sqrt(variance);
	}

	/* standardises each value in the training examples using the mean and the std (z-score standardisation) */
	private void standardiseData(ArrayList<ArrayList<Double>> examples) {
		for(int row_index = 0; row_index < examples.size(); row_index++) {
			ArrayList<Double> row = examples.get(row_index);
			for(int column_index = 0; column_index < examples.get(0).size(); column_index++) {
				double value = row.get(column_index);
				double current_std = std.get(column_index);
				double current_mean = mean.get(column_index);

				value = value - current_mean;
				value = value / current_std;

				row.set(column_index, value);
			}
		}
	}

	public ArrayList<String> predict(ArrayList<ArrayList<Double>> test_examples) {
		standardiseData(test_examples);

		ArrayList<String> predictions = new ArrayList<String>();

		for(int row_index = 0; row_index < test_examples.size(); row_index++) {
			String prediction = predict_row(test_examples.get(row_index), row_index);
			predictions.add(prediction);
		}

		return predictions;
	}

	private String predict_row(ArrayList<Double> this_test_example, int row_index) {
		ArrayList<Double[]> distances = findEuclidianDistances(this_test_example, row_index);
		ArrayList<Double[]> sorted_with_indicies = find_nn_indicies(distances);
		return make_prediction(sorted_with_indicies);
	}

	private ArrayList<Double[]> findEuclidianDistances(ArrayList<Double> this_test_example, int row_index) {
		ArrayList<Double[]> distances = new ArrayList<Double[]>();

		for(int i = 0; i < training_examples.size(); i++) {
			ArrayList<Double> this_training_example = training_examples.get(i);
			Double distance = findEuclidianDistance(this_training_example, this_test_example);
			Double[] distance_array = new Double[]{distance, Double.valueOf(i)};
			distances.add(distance_array);
		}

		return distances;
	}

	/* applying Euclidian formula */
	private double findEuclidianDistance(ArrayList<Double> P, ArrayList<Double> Q) {
		ArrayList<Double> differences = new ArrayList<Double>();
		
		for(int i = 0; i < P.size(); i++) {
			differences.add(Q.get(i) - P.get(i));
		}

		for(int i = 0; i < differences.size(); i++) {
			differences.set(i, Math.pow(differences.get(i), 2));
		}

		double totalDifference = 0;

		for(int i = 0; i < differences.size(); i++) {
			totalDifference += differences.get(i);
		}

		return Math.sqrt(totalDifference);
	}

	/* find nearest neighbours */
	private ArrayList<Double[]> find_nn_indicies(ArrayList<Double[]> distances) {
		QuickSort sorted = new QuickSort();

		sorted.sort(distances);

		ArrayList<Double[]> nearest_neighbours = new ArrayList<Double[]>();

		for(int i = 0; i < k; i++) {
			nearest_neighbours.add(distances.get(i));
		}

		return nearest_neighbours;
	}

	/* make prediction using k */
	private String make_prediction(ArrayList<Double[]> sorted_with_indicies) {
		ArrayList<Integer> original_indicies = new ArrayList<Integer>();

		for(int i = 0; i < sorted_with_indicies.size(); i++) {
			original_indicies.add(sorted_with_indicies.get(i)[1].intValue());
		}

		ArrayList<String> nn_class_labels = new ArrayList<String>();

		for(Integer nn_index : original_indicies) {
			nn_class_labels.add(training_labels.get(nn_index));
		}

		return mode(nn_class_labels);
	}

	/* find mode to calculate winning class */
	private String mode(ArrayList<String> nn_class_labels) { 
 		Map<String, Long> occurrences = nn_class_labels.stream().collect(Collectors.groupingBy(w -> w, Collectors.counting()));

 		long largestOccurrence = 0;
 		String prediction = "";

 		for (String name : occurrences.keySet()) {
 			if(largestOccurrence < occurrences.get(name)) {
 				prediction = name;
 				largestOccurrence = occurrences.get(name);
 			}
 		}

 		return prediction;
	}

}