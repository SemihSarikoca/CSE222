public class BubbleSort extends SortAlgorithm {
    
    public BubbleSort(int input_array[]) {
        super(input_array);
    }
    
    
    @Override
    public void sort() {
        // Get the length of the array
        int n = arr.length;
        
        // Loop over the array
        for (int i = 0; i < n - 1; i++) {
            // Flag to check if any swapping occurred in the inner loop
            boolean swapped = false;
            
            // Inner loop to compare each element with the next one
            for (int j = 0; j < n - i - 1; j++) {
                // Increment the comparison counter
                comparison_counter++;
                
                // If current element is greater than the next one, swap them
                if (arr[j] > arr[j + 1]) {
                    swap(j, j + 1);
                    // Mark that a swap occurred
                    swapped = true;
                }
            }
            
            // If no swapping occurred in the inner loop, the array is sorted
            if (!swapped) break;
        }
    }
    
    @Override
    public void print() {
        System.out.print("Bubble Sort\t=>\t");
        super.print();
    }
}
