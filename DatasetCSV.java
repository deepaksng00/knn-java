import java.io.BufferedReader; 
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path; 
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.List;

class DatasetCSV {
	ArrayList<ArrayList<Double>> data = new ArrayList<ArrayList<Double>>();

	DatasetCSV(String filename) {
		Path path = Paths.get(filename);

		try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)) {
			String line = br.readLine();

			while (line != null) {
				String[] feature_values = line.split(",");
				ArrayList<Double> features = new ArrayList<Double>();
				
				for (String s : feature_values) {
					try {
						features.add(Double.parseDouble(s));
					} catch (NumberFormatException e) {

					}
				}

				data.add(features);
				line = br.readLine();
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		data.remove(0);
	}

	public ArrayList<ArrayList<Double>> getData() {
		return data;
	}
}