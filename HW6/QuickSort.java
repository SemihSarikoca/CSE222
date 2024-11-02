public class QuickSort extends SortAlgorithm {
    
    public QuickSort(int input_array[]) {
        super(input_array);
    }
    
    private int partition(int low, int high) {
        int pivot = arr[high];
        
        // index of smaller element
        int i = (low - 1);
        
        // loop over the array
        for (int j = low; j < high; j++) {
            // increment the comparison counter
            comparison_counter++;
            
            // If current element is smaller than or equal to pivot
            if (arr[j] <= pivot) {
                i++;
                
                // swap arr[i] and arr[j]
                swap(i, j);
            }
        }
        
        // swap arr[i+1] and arr[high] (or pivot)
        swap(i + 1, high);
        
        return i + 1;
    }
    
    private void sort(int low, int high) {
        if (low < high) {
            // pi is partitioning index, arr[pi] is now at right place
            int pi = partition(low, high);
            
            // Recursively sort elements before partition and after partition
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
    }
    
    @Override
    public void sort() {
        sort(0, arr.length - 1);
    }
    
    
    @Override
    public void print() {
        System.out.print("Quick Sort\t=>\t");
        super.print();
    }
}
