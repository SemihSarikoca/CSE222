public class MergeSort extends SortAlgorithm {
    
    public MergeSort(int input_array[]) {
        super(input_array);
    }
    
    private void merge(int low, int mid, int high) {
        // Find sizes of two subarrays to be merged
        int n1 = mid - low + 1;
        int n2 = high - mid;
        
        // Create temp arrays
        int[] left = new int[n1];
        int[] right = new int[n2];
        
        // Copy data to temp arrays
        System.arraycopy(arr, low, left, 0, n1);
        System.arraycopy(arr, mid + 1, right, 0, n2);
        
        // Merge the temp arrays
        
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;
        
        // Initial index of merged subarray array
        int k = low;
        while (i < n1 && j < n2) {
            comparison_counter++; // increment the comparison counter
            if (left[i] <= right[j]) {
                arr[k] = left[i];
                i++;
            } else {
                arr[k] = right[j];
                j++;
            }
            k++;
        }
        
        // Copy remaining elements of left[] if any
        while (i < n1) {
            arr[k] = left[i];
            i++;
            k++;
        }
        
        // Copy remaining elements of right[] if any
        while (j < n2) {
            arr[k] = right[j];
            j++;
            k++;
        }
    }
    
    private void sort(int low, int high) {
        if (low < high) {
            // Find the middle point
            int mid = (low + high) / 2;
            
            // Sort first and second halves
            sort(low, mid);
            sort(mid + 1, high);
            
            // Merge the sorted halves
            merge(low, mid, high);
        }
    }
    
    @Override
    public void sort() {
        sort(0, arr.length - 1);
    }
    
    @Override
    public void print() {
        System.out.print("Merge Sort\t=>\t");
        super.print();
    }
}
