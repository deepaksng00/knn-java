import java.util.ArrayList; 

class Main {
	public static void main(String[] args) {
		DatasetCSV training_examples = new DatasetCSV("iris.csv");
		ArrayList<ArrayList<Double>> data = training_examples.getData();
	}	
}
