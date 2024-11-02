public class SelectionSort extends SortAlgorithm {
    
    public SelectionSort(int input_array[]) {
        super(input_array);
    }
    
    @Override
    public void sort() {
        // Get the length of the array
        int n = arr.length;
        
        // Loop over the array
        for (int i = 0; i < n - 1; i++) {
            // Assume the first element of the unsorted part as the minimum
            int min_idx = i;
            
            // Inner loop to find the minimum element in the unsorted part
            for (int j = i + 1; j < n; j++) {
                // Increment the comparison counter
                comparison_counter++;
                
                // If the current element is less than the assumed minimum, update the minimum
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }
            
            // Swap the found minimum element with the first element of the unsorted part
            swap(min_idx, i);
        }
    }
    
    @Override
    public void print() {
        System.out.print("Selection Sort\t=>\t");
        super.print();
    }
}
