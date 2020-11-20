import java.io.BufferedReader; 
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.List;
import java.util.Arrays;

class DatasetCSV {
	ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();
	ArrayList<String> class_labels = new ArrayList<String>();
	String[] feature_labels;

	/* constructor takes filename */
	DatasetCSV(String filename) {
		Path path = Paths.get(filename);

		try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
			String line = br.readLine();

			int lineCount = 0;

			while (line != null) {
				lineCount++;

				if(lineCount != 1) {
					String[] feature_values = line.split(",");
					ArrayList<Double> features = new ArrayList<Double>();

					for (String s : feature_values) {
						try {
							features.add(Double.parseDouble(s));
						} catch (NumberFormatException e) {
							class_labels.add(s);
						}
					}

					data.add(features);

					line = br.readLine();
				} else {
					feature_labels = line.split(",");
					line = br.readLine();
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	public ArrayList<ArrayList<Double>> getExamples() {
		return data;
	}

	public ArrayList<String> getClassLabels() {
		return class_labels;
	}

	public String[] getFeatureLabels() {
		return feature_labels;
	}
}