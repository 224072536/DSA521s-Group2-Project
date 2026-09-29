public class InsertionSort {

    public static long comparisons;   // data-value comparisons
    public static long shifts;        // elements moved one position right

    public static void sort(int[] arr, boolean showTrace) {
        comparisons = 0;
        shifts = 0;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];        // the value being inserted into the sorted part
            int j = i - 1;

            // Move every larger value one position to the right
            while (j >= 0) {
                comparisons++;                      // comparing key with arr[j]
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shifts++;
                    j--;
                } else {
                    break;                          // correct place found
                }
            }
            arr[j + 1] = key;                       // drop the key into its slot

            if (showTrace) {
                System.out.println("After pass " + i + " (inserted " + key + "): " + toString(arr)
                        + "   (comparisons so far: " + comparisons + ", shifts: " + shifts + ")");
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
        System.out.println("=== INSERTION SORT ===");
        System.out.println("Original : " + toString(data));
        sort(data, true);
        System.out.println("Sorted   : " + toString(data));
        System.out.println("Total comparisons: " + comparisons);
        System.out.println("Total shifts     : " + shifts);
    }
}
