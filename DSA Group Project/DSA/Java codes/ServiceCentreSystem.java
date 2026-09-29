import java.util.Scanner;

/**
 * PART D - INTEGRATED SERVICE-CENTRE SYSTEM
 *
 * Brings the Queue (A1), Singly Linked List (A2), Array statistics (A4) and the
 * four sorting algorithms (B1-B4) together in one program.
 *
 * The postfix Stack task (A3) stays a separate exercise and is not part of this menu.
 */
public class ServiceCentreSystem {

    // ---- the data structures the whole system runs on ----
    private static StudentQueue waitingQueue = new StudentQueue();
    private static StudentLinkedList serviceRecords = new StudentLinkedList();
    private static DailyStatistics statistics = new DailyStatistics(200);

    private static Scanner input = new Scanner(System.in);

    // ================= MENU =================

    private static void displayMenu() {
        System.out.println();
        System.out.println("========================================");
        System.out.println("         CAMPUS SERVICE CENTRE");
        System.out.println("========================================");
        System.out.println("1.  Add student to waiting queue");
        System.out.println("2.  Serve next student (remove from queue)");
        System.out.println("3.  Display waiting students");
        System.out.println("4.  Add student service record");
        System.out.println("5.  Display student service records");
        System.out.println("6.  Search for student record");
        System.out.println("7.  Remove student record");
        System.out.println("8.  Display daily statistics");
        System.out.println("9.  Sort service times");
        System.out.println("10. Run sorting experiment");
        System.out.println("11. Exit");
        System.out.println("========================================");
        System.out.print("Select option: ");
    }

    public static void main(String[] args) {
        System.out.println("Welcome to the NUST Campus Service Centre Simulation.");
        loadSampleData();

        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt();

            switch (choice) {
                case 1:  addStudentToQueue();    break;
                case 2:  serveNextStudent();     break;
                case 3:  displayWaitingQueue();  break;
                case 4:  addServiceRecord();     break;
                case 5:  displayServiceRecords();break;
                case 6:  searchServiceRecord();  break;
                case 7:  removeServiceRecord();  break;
                case 8:  displayStatistics();    break;
                case 9:  sortServiceTimes();     break;
                case 10: SortingExperiment.runExperiment(); break;
                case 11:
                    System.out.println("\nClosing the service centre. Goodbye.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter a number from 1 to 11.");
            }
        }
    }

    // ================= OPTION 1 : QUEUE - ENQUEUE =================

    private static void addStudentToQueue() {
        System.out.println("\n--- Add student to waiting queue (Queue: enqueue) ---");
        Student student = readStudentDetails();
        waitingQueue.enqueue(student);
        System.out.println("Added to the waiting queue: " + student.getName());
        System.out.println("Students now waiting: " + waitingQueue.size());
    }

    // ================= OPTION 2 : QUEUE - DEQUEUE =================

    private static void serveNextStudent() {
        System.out.println("\n--- Serve next student (Queue: dequeue) ---");

        if (waitingQueue.isEmpty()) {
            System.out.println("The waiting queue is empty - there is nobody to serve.");
            return;
        }

        Student served = waitingQueue.dequeue();
        System.out.println("Now serving: " + served);

        // Serving a student automatically creates a service record (linked list)
        // and records the service time for the daily statistics (array).
        serviceRecords.insertStudent(served);
        statistics.addServiceTime(served.getServiceTime());

        System.out.println("A service record was created and the service time was recorded.");
        System.out.println("Students still waiting: " + waitingQueue.size());
    }

    // ================= OPTION 3 : QUEUE - TRAVERSAL =================

    private static void displayWaitingQueue() {
        System.out.println("\n--- Waiting students (Queue: traversal) ---");
        waitingQueue.displayQueue();
        if (!waitingQueue.isEmpty()) {
            System.out.println("Next to be served: " + waitingQueue.peek().getName());
        }
    }

    // ================= OPTION 4 : LINKED LIST - INSERTION =================

    private static void addServiceRecord() {
        System.out.println("\n--- Add student service record (Linked List: insertStudent) ---");
        Student student = readStudentDetails();

        System.out.println("Where should the record be inserted?");
        System.out.println("  1. At the beginning");
        System.out.println("  2. At the end");
        System.out.println("  3. At a specific position");
        System.out.print("Choice: ");
        int where = readInt();

        if (where == 1) {
            serviceRecords.insertAtBeginning(student);
        } else if (where == 3) {
            System.out.print("Enter position (1 to " + (serviceRecords.size() + 1) + "): ");
            int position = readInt();
            serviceRecords.insertAtPosition(student, position);
        } else {
            serviceRecords.insertStudent(student);   // insertAtEnd
        }

        // Keep the statistics array in step with the records
        statistics.loadTimes(serviceRecords.getServiceTimes());
    }

    // ================= OPTION 5 : LINKED LIST - TRAVERSAL =================

    private static void displayServiceRecords() {
        System.out.println("\n--- Student service records (Linked List: traversal) ---");
        serviceRecords.displayStudents();
    }

    // ================= OPTION 6 : LINKED LIST - SEARCH =================

    private static void searchServiceRecord() {
        System.out.println("\n--- Search for student record (Linked List: search) ---");
        System.out.print("Enter the student number to search for: ");
        String studentNo = input.nextLine().trim();
        serviceRecords.searchStudent(studentNo);   // prints found / not found
    }

    // ================= OPTION 7 : LINKED LIST - DELETION =================

    private static void removeServiceRecord() {
        System.out.println("\n--- Remove student record (Linked List: deletion) ---");
        System.out.print("Enter the student number to remove: ");
        String studentNo = input.nextLine().trim();

        boolean removed = serviceRecords.deleteStudent(studentNo);
        if (removed) {
            statistics.loadTimes(serviceRecords.getServiceTimes());   // statistics follow the records
            System.out.println("Records remaining: " + serviceRecords.size());
        }
    }

    // ================= OPTION 8 : ARRAY PROCESSING =================

    private static void displayStatistics() {
        System.out.println("\n--- Daily statistics (Array: traversal) ---");
        statistics.loadTimes(serviceRecords.getServiceTimes());
        statistics.displayStatistics();
    }

    // ================= OPTION 9 : SORTING =================

    private static void sortServiceTimes() {
        System.out.println("\n--- Sort service times (Sorting algorithms) ---");

        int[] times = serviceRecords.getServiceTimes();
        if (times.length < 2) {
            System.out.println("At least two service records are needed before sorting.");
            return;
        }

        System.out.println("Choose a sorting algorithm:");
        System.out.println("  1. Selection Sort");
        System.out.println("  2. Insertion Sort");
        System.out.println("  3. Merge Sort");
        System.out.println("  4. Quick Sort");
        System.out.println("  5. Run all four and compare");
        System.out.print("Choice: ");
        int algorithm = readInt();

        System.out.println("\nBefore sorting: " + SelectionSort.toString(times));

        if (algorithm == 1) {
            int[] copy = copyOf(times);
            SelectionSort.sort(copy);
            report("Selection Sort", copy, SelectionSort.comparisons, SelectionSort.swaps, "swaps");
        } else if (algorithm == 2) {
            int[] copy = copyOf(times);
            InsertionSort.sort(copy);
            report("Insertion Sort", copy, InsertionSort.comparisons, InsertionSort.shifts, "shifts");
        } else if (algorithm == 3) {
            int[] copy = copyOf(times);
            MergeSort.sort(copy);
            report("Merge Sort", copy, MergeSort.comparisons, -1, "");
        } else if (algorithm == 4) {
            int[] copy = copyOf(times);
            QuickSort.sort(copy);
            report("Quick Sort", copy, QuickSort.comparisons, QuickSort.swaps, "swaps");
        } else {
            int[] a = copyOf(times);
            SelectionSort.sort(a);
            report("Selection Sort", a, SelectionSort.comparisons, SelectionSort.swaps, "swaps");

            int[] b = copyOf(times);
            InsertionSort.sort(b);
            report("Insertion Sort", b, InsertionSort.comparisons, InsertionSort.shifts, "shifts");

            int[] c = copyOf(times);
            MergeSort.sort(c);
            report("Merge Sort", c, MergeSort.comparisons, -1, "");

            int[] d = copyOf(times);
            QuickSort.sort(d);
            report("Quick Sort", d, QuickSort.comparisons, QuickSort.swaps, "swaps");

            System.out.println("\nAll four produced the same sorted order, with different costs.");
        }
    }

    private static void report(String name, int[] sorted, long comparisons, long moves, String moveLabel) {
        System.out.println("\n" + name);
        System.out.println("  Sorted result : " + SelectionSort.toString(sorted));
        System.out.println("  Comparisons   : " + comparisons);
        if (moves >= 0) {
            System.out.printf("  %-14s: %d%n", moveLabel, moves);
        }
    }

    // ================= HELPER METHODS =================

    private static int[] copyOf(int[] source) {
        int[] copy = new int[source.length];
        for (int i = 0; i < source.length; i++) {
            copy[i] = source[i];
        }
        return copy;
    }

    // Read one whole line and convert it to a number.
    // Reading whole lines avoids the well-known Scanner problem where a leftover
    // newline from nextInt() is picked up by the next nextLine().
    private static int readInt() {
        while (true) {
            String line = input.hasNextLine() ? input.nextLine().trim() : "11";
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("That is not a whole number. Try again: ");
            }
        }
    }

    private static Student readStudentDetails() {
        System.out.print("Student number : ");
        String studentNo = input.nextLine().trim();
        System.out.print("Name           : ");
        String name = input.nextLine().trim();
        System.out.print("Service type   : ");
        String serviceType = input.nextLine().trim();
        System.out.print("Service time (minutes): ");
        int serviceTime = readInt();
        return new Student(studentNo, name, serviceType, serviceTime);
    }

    // A few students so the menu can be demonstrated immediately
    private static void loadSampleData() {
        waitingQueue.enqueue(new Student("221045678", "Maria",   "Registration",     12));
        waitingQueue.enqueue(new Student("222034512", "Tomas",   "Student Card",      5));
        waitingQueue.enqueue(new Student("223041876", "Ndapewa", "Fees",              8));
        waitingQueue.enqueue(new Student("221067341", "Simon",   "Documents",         4));
        waitingQueue.enqueue(new Student("224012345", "Helena",  "Academic Enquiry",  7));
        waitingQueue.enqueue(new Student("222098765", "Petrus",  "Fees",              6));
        System.out.println("Six sample students have been loaded into the waiting queue.");
    }
}
