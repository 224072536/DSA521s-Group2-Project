public class SelectionSort {

    public static long comparisons;   // data-value comparisons
    public static long swaps;         // actual exchanges performed

    // Sort the array into ascending order
    public static void sort(int[] arr, boolean showTrace) {
        comparisons = 0;
        swaps = 0;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;                       // assume position i holds the smallest

            for (int j = i + 1; j < n; j++) {
                comparisons++;                      // comparing two DATA values
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;                   // found a smaller value
                }
            }

            if (minIndex != i) {                    // only swap if a smaller value was found
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
                swaps++;
            }

            if (showTrace) {
                System.out.println("After pass " + (i + 1) + ": " + toString(arr)
                        + "   (comparisons so far: " + comparisons + ", swaps: " + swaps + ")");
            }
        }
    }

    public static void sort(int[] arr) {
        sort(arr, false);
    }

    public static String toString(int[] arr) {
        String result = "[ ";
        for (int i = 0; i < arr.length; i++) {
            result = result + arr[i] + " ";
        }
        return result + "]";
    }

    public static void main(String[] args) {
        int[] data = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
        System.out.println("=== SELECTION SORT ===");
        System.out.println("Original : " + toString(data));
        sort(data, true);
        System.out.println("Sorted   : " + toString(data));
        System.out.println("Total comparisons: " + comparisons);
        System.out.println("Total swaps      : " + swaps);
    }
}
