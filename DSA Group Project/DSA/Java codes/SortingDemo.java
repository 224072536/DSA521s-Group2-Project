public class SortingDemo {

    // The service-time array specified in the project brief for Tasks B1-B4
    private static int[] original() {
        return new int[]{17, 5, 23, 8, 14, 3, 11, 20, 6, 9};
    }

    public static void main(String[] args) {
        System.out.println("PART B - SORTING ALGORITHM CHALLENGE");
        System.out.println("Data set: " + SelectionSort.toString(original()));

        System.out.println("\n============================================================");
        System.out.println("B1 - SELECTION SORT");
        System.out.println("============================================================");
        int[] a = original();
        SelectionSort.sort(a, true);
        System.out.println("Sorted: " + SelectionSort.toString(a));
        System.out.println("Comparisons: " + SelectionSort.comparisons + "   Swaps: " + SelectionSort.swaps);

        System.out.println("\n============================================================");
        System.out.println("B2 - INSERTION SORT");
        System.out.println("============================================================");
        int[] b = original();
        InsertionSort.sort(b, true);
        System.out.println("Sorted: " + InsertionSort.toString(b));
        System.out.println("Comparisons: " + InsertionSort.comparisons + "   Shifts: " + InsertionSort.shifts);

        System.out.println("\n============================================================");
        System.out.println("B3 - MERGE SORT");
        System.out.println("============================================================");
        int[] c = original();
        MergeSort.sort(c, true);
        System.out.println("Sorted: " + MergeSort.toString(c));
        System.out.println("Comparisons: " + MergeSort.comparisons);

        System.out.println("\n============================================================");
        System.out.println("B4 - QUICK SORT  (pivot = last element of the section)");
        System.out.println("============================================================");
        int[] d = original();
        QuickSort.sort(d, true);
        System.out.println("Sorted: " + QuickSort.toString(d));
        System.out.println("Comparisons: " + QuickSort.comparisons + "   Swaps: " + QuickSort.swaps);

        System.out.println("\n============================================================");
        System.out.println("SUMMARY FOR THE 10-ELEMENT ARRAY");
        System.out.println("============================================================");
        System.out.printf("%-16s %-14s %s%n", "ALGORITHM", "COMPARISONS", "SWAPS / SHIFTS");
        System.out.printf("%-16s %-14d %d%n", "Selection Sort", SelectionSort.comparisons, SelectionSort.swaps);
        System.out.printf("%-16s %-14d %d%n", "Insertion Sort", InsertionSort.comparisons, InsertionSort.shifts);
        System.out.printf("%-16s %-14d %s%n", "Merge Sort", MergeSort.comparisons, "-");
        System.out.printf("%-16s %-14d %d%n", "Quick Sort", QuickSort.comparisons, QuickSort.swaps);
    }
}
