// Java program for implementation of QuickSort 
import java.util.*; 
  
class QuickSort { 
    /* This function takes last element as pivot, 
    places the pivot element at its correct 
    position in sorted array, and places all 
    smaller (smaller than pivot) to left of 
    pivot and all greater elements to right 
    of pivot */
    static int partition(ArrayList<Double[]> arr, int low, int high) 
    { 
        double pivot = arr.get(high)[0]; 
        int i = (low - 1); // index of smaller element 
        for (int j = low; j <= high - 1; j++) { 
            // If current element is smaller than or 
            // equal to pivot 
            if (arr.get(j)[0] <= pivot) { 
                i++; 
  
                // swap arr[i] and arr[j] 
                Double[] temp = arr.get(i); 
                arr.set(i, arr.get(j)); 
                arr.set(j, temp); 
            } 
        } 
  
        // swap arr[i+1] and arr[high] (or pivot) 
        Double[] temp = arr.get(i + 1); 
        arr.set(i + 1, arr.get(high)); 
        arr.set(high, temp); 
  
        return i + 1; 
    } 
  
    /* The main function that implements QuickSort() 
    arr[] --> Array to be sorted, 
    low --> Starting index, 
    high --> Ending index */
    static void qSort(ArrayList<Double[]> arr, int low, int high) 
    { 
        if (low < high) { 
            /* pi is partitioning index, arr[pi] is 
            now at right place */
            int pi = partition(arr, low, high); 
  
            // Recursively sort elements before 
            // partition and after partition 
            qSort(arr, low, pi - 1); 
            qSort(arr, pi + 1, high); 
        } 
    } 
  
    public ArrayList<Double[]> sort(ArrayList<Double[]> arr) {
        qSort(arr, 0, arr.size() - 1);
        return arr;
    }
} 