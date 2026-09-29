import java.util.Random;

public class SortingExperiment {

    private static final int[] SIZES = {20, 50, 100, 500};
    private static final long SEED = 2026;   // fixed seed, so the experiment is repeatable

    // ---------- helper methods ----------

    // Generate an array of random integers between 1 and 999
    private static int[] generateArray(int size, Random rng) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rng.nextInt(999) + 1;
        }
        return arr;
    }

    // Manual copy - every algorithm must receive exactly the same values
    private static int[] copyOf(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    // Safety check: confirm the array really is in ascending order
    private static boolean isSorted(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] > arr[i]) {
                return false;
            }
        }
        return true;
    }

    // ---------- warm-up ----------
    // Java compiles methods to machine code only after they have run a few times.
    // Without this, whichever algorithm runs FIRST looks artificially slow.
    // The warm-up is not timed and does not affect the recorded results.
    private static void warmUp() {
        Random rng = new Random(1);
        for (int round = 0; round < 200; round++) {
            int[] a = generateArray(100, rng);
            SelectionSort.sort(copyOf(a));
            InsertionSort.sort(copyOf(a));
            MergeSort.sort(copyOf(a));
            QuickSort.sort(copyOf(a));
        }
    }

    // ---------- one timed run ----------

    private static void runOne(String name, int[] data, int size) {
        long comparisons;
        long start;
        long end;

        if (name.equals("Selection Sort")) {
            start = System.nanoTime();
            SelectionSort.sort(data);
            end = System.nanoTime();
            comparisons = SelectionSort.comparisons;
        } else if (name.equals("Insertion Sort")) {
            start = System.nanoTime();
            InsertionSort.sort(data);
            end = System.nanoTime();
            comparisons = InsertionSort.comparisons;
        } else if (name.equals("Merge Sort")) {
            start = System.nanoTime();
            MergeSort.sort(data);
            end = System.nanoTime();
            comparisons = MergeSort.comparisons;
        } else {
            start = System.nanoTime();
            QuickSort.sort(data);
            end = System.nanoTime();
            comparisons = QuickSort.comparisons;
        }

        long executionTime = end - start;          // Execution Time = End Time - Start Time

        System.out.printf("%-16s %-12d %-16d %-16d %s%n",
                name, size, comparisons, executionTime, isSorted(data) ? "yes" : "NO");
    }

    // Run all four algorithms on identical copies of ONE original array
    private static void runAllFour(int[] original, int size) {
        runOne("Selection Sort", copyOf(original), size);
        runOne("Insertion Sort", copyOf(original), size);
        runOne("Merge Sort",     copyOf(original), size);
        runOne("Quick Sort",     copyOf(original), size);
    }

    private static void tableHeader() {
        System.out.println("--------------------------------------------------------------------");
        System.out.printf("%-16s %-12s %-16s %-16s %s%n",
                "ALGORITHM", "INPUT SIZE", "COMPARISONS", "TIME (ns)", "SORTED?");
        System.out.println("--------------------------------------------------------------------");
    }

    // ---------- main experiment ----------

    // main() simply calls runExperiment() so that menu option 10 in Part D
    // can run exactly the same experiment.
    public static void main(String[] args) {
        runExperiment();
    }

    public static void runExperiment() {
        System.out.println("====================================================================");
        System.out.println(" PART C - ALGORITHM EXPERIMENT");
        System.out.println(" Only comparisons between DATA VALUES are counted.");
        System.out.println(" Only the sorting call is timed (generation, copying and");
        System.out.println(" printing are excluded).");
        System.out.println("====================================================================");

        warmUp();

        Random rng = new Random(SEED);
        int[] hundredElementArray = null;

        System.out.println("\nMAIN EXPERIMENT - RANDOM ARRAYS");
        tableHeader();

        for (int s = 0; s < SIZES.length; s++) {
            int size = SIZES[s];

            // ONE original array per input size; each algorithm gets a copy of it
            int[] original = generateArray(size, rng);

            if (size == 100) {
                hundredElementArray = copyOf(original);   // kept for the almost-sorted test
            }

            runAllFour(original, size);
            System.out.println("--------------------------------------------------------------------");
        }

        // ---------- additional test: almost-sorted array ----------

        System.out.println("\nADDITIONAL TEST - ALMOST-SORTED 100-ELEMENT ARRAY");
        System.out.println("Step 1: sort the 100-element array into ascending order.");
        System.out.println("Step 2: swap FIVE pairs of neighbouring values.");

        int[] almostSorted = copyOf(hundredElementArray);
        MergeSort.sort(almostSorted);                      // step 1

        int[] swapPositions = {10, 25, 40, 60, 80};        // step 2
        for (int k = 0; k < swapPositions.length; k++) {
            int p = swapPositions[k];
            int temp = almostSorted[p];
            almostSorted[p] = almostSorted[p + 1];
            almostSorted[p + 1] = temp;
        }

        System.out.println("Swapped the pairs at index positions 10/11, 25/26, 40/41, 60/61, 80/81.");
        tableHeader();
        runAllFour(almostSorted, 100);
        System.out.println("--------------------------------------------------------------------");
        System.out.println("\nCompare these four rows with the random 100-element rows above.");
    }
}
