import java.util.ArrayList;

class KNNClassifier {
	ArrayList<ArrayList<Double>> training_examples; // storing training examples for the training of the classifier
	ArrayList<ArrayList<String>> training_labels; // storing class labels related to the training examples
	int k; // stores hyperparameter "k" nearest neighbours

	KNNClassifier(ArrayList<ArrayList<Double>> training_examples, ArrayList<ArrayList<String>> training_labels, int k) {
		// start standardisation process z-score standardisation

		this.training_examples = training_examples;
		this.training_labels = training_labels;
		this.k = k;

		ArrayList<Double> means = mean();

		/* calculating std of each feature column */

		ArrayList<Double> std = std(means);

		/* standardise */

		System.out.println(training_examples.get(1));

		for(int row_index = 0; row_index < training_examples.size(); row_index++) {
			ArrayList<Double> row = training_examples.get(row_index);
			for(int column_index = 0; column_index < training_examples.get(0).size(); column_index++) {
				double value = row.get(column_index);
				double current_std = std.get(column_index);
				double current_mean = means.get(column_index);

				value = value - current_mean;
				value = value / current_std;

				row.set(column_index, value);
			}
		}

		System.out.println(training_examples.get(1));

	}

	private ArrayList<Double> mean() {
		ArrayList<Double> means = new ArrayList<Double>();

		/* calculating mean of each feature column */

		for(int i = 0; i < training_examples.get(0).size(); i++) {
			double column = mean_of_column(i);
			means.add(column);
		}

		return means;
	}

	private ArrayList<Double> std(ArrayList<Double> means) {
		ArrayList<Double> std = new ArrayList<Double>();

		for(int i = 0; i < training_examples.get(0).size(); i++) {
			double column = std_of_column(means, i);
			std.add(column);
		}

		return std;
	}

	private double mean_of_column(int column_index) {
		double total = 0;
		for(int row_index = 0; row_index < training_examples.size(); row_index++) {
			total += training_examples.get(row_index).get(column_index);
		}
		return total / training_examples.size();
	}

	private double std_of_column(ArrayList<Double> means, int column_index) {
		double mean = means.get(column_index);
		double sumOfDifferences = 0;

		for(int row_index = 0; row_index < training_examples.size(); row_index++) {
			double currentValue = training_examples.get(row_index).get(column_index) - mean;
			double currentSquared = Math.pow(currentValue, 2);
			sumOfDifferences += currentSquared;
		}

		double variance = sumOfDifferences / training_examples.size();

		return Math.sqrt(variance);
	}
}