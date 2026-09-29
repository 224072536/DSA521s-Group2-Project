public class LinkedListDemo {
    public static void main(String[] args) {
        StudentLinkedList records = new StudentLinkedList();

        System.out.println("=== Insertion at the end ===");
        records.insertAtEnd(new Student("221045678", "Maria", "Registration", 12));
        records.insertAtEnd(new Student("222034512", "Tomas", "Student Card", 5));
        records.insertAtEnd(new Student("223041876", "Ndapewa", "Fees", 8));
        records.displayStudents();

        System.out.println("\n=== Insertion at the beginning ===");
        records.insertAtBeginning(new Student("221067341", "Simon", "Documents", 4));
        records.displayStudents();

        System.out.println("\n=== Insertion at position 3 ===");
        records.insertAtPosition(new Student("224012345", "Helena", "Academic Enquiry", 7), 3);
        records.displayStudents();

        System.out.println("\n=== Searching ===");
        records.searchStudent("223041876");
        records.searchStudent("229999999");

        System.out.println("\n=== Deletion ===");
        records.deleteStudent("222034512");
        records.displayStudents();

        System.out.println("\n=== Traversal after all operations ===");
        records.displayStudents();
    }
}
