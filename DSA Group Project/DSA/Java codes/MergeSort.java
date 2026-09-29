public class MergeSort {

    public static long comparisons;   // data-value comparisons made while merging

    public static void sort(int[] arr, boolean showTrace) {
        comparisons = 0;
        int[] temp = new int[arr.length];          // one reusable helper array
        mergeSort(arr, temp, 0, arr.length - 1, showTrace, 0);
    }

    public static void sort(int[] arr) {
        sort(arr, false);
    }

    // Recursive divide step
    private static void mergeSort(int[] arr, int[] temp, int left, int right,
                                  boolean showTrace, int depth) {
        if (left >= right) {                       // BASE CASE: 0 or 1 element
            if (showTrace && left == right) {
                System.out.println(indent(depth) + "base case: [ " + arr[left] + " ]");
            }
            return;
        }

        int mid = left + (right - left) / 2;       // middle without overflow

        if (showTrace) {
            System.out.println(indent(depth) + "divide " + section(arr, left, right)
                    + " -> " + section(arr, left, mid) + " and " + section(arr, mid + 1, right));
        }

        mergeSort(arr, temp, left, mid, showTrace, depth + 1);        // sort left half
        mergeSort(arr, temp, mid + 1, right, showTrace, depth + 1);   // sort right half
        merge(arr, temp, left, mid, right);                           // combine them

        if (showTrace) {
            System.out.println(indent(depth) + "merge  -> " + section(arr, left, right));
        }
    }

    // Combine two sorted halves into one sorted section
    private static void merge(int[] arr, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = arr[i];                      // copy the section to be merged
        }

        int i = left;          // reader for the left half
        int j = mid + 1;       // reader for the right half
        int k = left;          // writer into the original array

        while (i <= mid && j <= right) {
            comparisons++;                         // comparing two DATA values
            if (temp[i] <= temp[j]) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
        }

        while (i <= mid) {                         // leftovers from the left half
            arr[k] = temp[i];
            i++;
            k++;
        }
        while (j <= right) {                       // leftovers from the right half
            arr[k] = temp[j];
            j++;
            k++;
        }
    }

    private static String indent(int depth) {
        String s = "";
        for (int i = 0; i < depth; i++) {
            s = s + "    ";
        }
        return s;
    }

    private static String section(int[] arr, int left, int right) {
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
        System.out.println("=== MERGE SORT ===");
        System.out.println("Original : " + toString(data));
        System.out.println("--- divide and merge process ---");
        sort(data, true);
        System.out.println("--------------------------------");
        System.out.println("Sorted   : " + toString(data));
        System.out.println("Total comparisons: " + comparisons);
    }
}
