// class KNNClassifier {
// 	double[][] training_examples; // storing training examples for the training of the classifier
// 	double[][] training_labels; // storing class labels related to the training examples
// 	int k; // stores hyperparameter "k" nearest neighbours

// 	KNNClassifier(String[][] training_examples, String[][] training_labels, int k) {
// 		// start standardisation process z-score standardisation
// 		int column = 0;
// 		double column1 = mean(training_examples, training_examples, 0);

// 		System.out.println(column1);

// 		this.training_examples = training_examples;
// 		this.training_labels = training_labels;
// 		this.k = k;
// 	}

// 	private double mean_of_column(String[][] training_examples_nonstd, int column_index) {
// 		total = 0;
// 		for(int row_index = 0; row < training_examples; row_index++) {
// 			total += training_examples[row_index][column_index];
// 		}
// 		total = total / training_examples.length;
// 	}
// }