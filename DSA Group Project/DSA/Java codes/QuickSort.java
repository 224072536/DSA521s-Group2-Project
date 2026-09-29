public class QuickSort {

    public static long comparisons;   // data-value comparisons
    public static long swaps;

    private static int stageCount;    // counts partitioning stages for the trace

    public static void sort(int[] arr, boolean showTrace) {
        comparisons = 0;
        swaps = 0;
        stageCount = 0;
        quickSort(arr, 0, arr.length - 1, showTrace);
    }

    public static void sort(int[] arr) {
        sort(arr, false);
    }

    private static void quickSort(int[] arr, int low, int high, boolean showTrace) {
        if (low >= high) {            // BASE CASE: 0 or 1 element is already sorted
            return;
        }

        int pivotIndex = partition(arr, low, high, showTrace);

        quickSort(arr, low, pivotIndex - 1, showTrace);    // sort the left partition
        quickSort(arr, pivotIndex + 1, high, showTrace);   // sort the right partition
    }

    // Lomuto partition scheme - PIVOT RULE: the LAST element of the section
    private static int partition(int[] arr, int low, int high, boolean showTrace) {
        int pivot = arr[high];
        int i = low - 1;              // boundary of the "smaller than pivot" region

        for (int j = low; j < high; j++) {
            comparisons++;                            // comparing two DATA values
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];                    // swap arr[i] and arr[j]
                arr[i] = arr[j];
                arr[j] = temp;
                swaps++;
            }
        }

        int temp = arr[i + 1];        // put the pivot into its final position
        arr[i + 1] = arr[high];
        arr[high] = temp;
        swaps++;

        int pivotIndex = i + 1;

        if (showTrace) {
            stageCount++;
            System.out.println("Stage " + stageCount + ": section " + section(arr, low, high)
                    + "  pivot = " + pivot);
            System.out.println("         left partition  " + section(arr, low, pivotIndex - 1)
                    + "  (values <= " + pivot + ")");
            System.out.println("         pivot placed at index " + pivotIndex);
            System.out.println("         right partition " + section(arr, pivotIndex + 1, high)
                    + "  (values > " + pivot + ")");
            System.out.println("         whole array now " + toString(arr));
        }

        return pivotIndex;
    }

    private static String section(int[] arr, int left, int right) {
        if (left > right) {
            return "[ empty ]";
        }
        String result = "[ ";
        for (int i = left; i <= right; i++) {
            result = result + arr[i] + " ";
        }
        return result + "]";
    }

    public static String toString(int[] arr) {
        return section(arr, 0, arr.length - 1);
    }

    public static void main(String[] args) {
        int[] data = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
        System.out.println("=== QUICK SORT ===");
        System.out.println("Pivot-selection rule: last element of the current section");
        System.out.println("Original : " + toString(data));
        System.out.println("--- partitioning stages ---");
        sort(data, true);
        System.out.println("---------------------------");
        System.out.println("Sorted   : " + toString(data));
        System.out.println("Total comparisons: " + comparisons);
        System.out.println("Total swaps      : " + swaps);
    }
}
