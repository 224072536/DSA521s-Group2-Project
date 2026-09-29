public class StudentLinkedList {

    // One node = data part (the student) + next pointer
    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node head;   // first node in the list
    private int count;   // how many records are stored

    public StudentLinkedList() {
        head = null;
        count = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        return count;
    }

    // ---------- INSERTION ----------

    // Insert a new record at the BEGINNING of the list
    public void insertAtBeginning(Student student) {
        Node newNode = new Node(student);
        newNode.next = head;
        head = newNode;
        count++;
        System.out.println("Inserted at beginning: " + student.getName());
    }

    // Insert a new record at the END of the list
    public void insertAtEnd(Student student) {
        Node newNode = new Node(student);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        count++;
        System.out.println("Inserted at end: " + student.getName());
    }

    // Insert at a specified position (position 1 = beginning)
    public void insertAtPosition(Student student, int position) {
        if (position < 1 || position > count + 1) {
            System.out.println("Invalid position. Use 1 to " + (count + 1) + ".");
            return;
        }
        if (position == 1) {
            insertAtBeginning(student);
            return;
        }
        Node newNode = new Node(student);
        Node current = head;
        // stop at the node just BEFORE the target position
        for (int i = 1; i < position - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        count++;
        System.out.println("Inserted at position " + position + ": " + student.getName());
    }

    // General insert used by the menu in Part D (adds at the end)
    public void insertStudent(Student student) {
        insertAtEnd(student);
    }

    // ---------- DELETION ----------

    // Delete the record with the given student number
    public boolean deleteStudent(String studentNo) {
        if (isEmpty()) {
            System.out.println("List is empty - nothing to delete.");
            return false;
        }
        // Case 1: the record to delete is the head
        if (head.data.getStudentNo().equals(studentNo)) {
            System.out.println("Deleted: " + head.data.getName());
            head = head.next;
            count--;
            return true;
        }
        // Case 2: search for the node BEFORE the one to delete
        Node current = head;
        while (current.next != null && !current.next.data.getStudentNo().equals(studentNo)) {
            current = current.next;
        }
        if (current.next == null) {
            System.out.println("Student " + studentNo + " not found - nothing deleted.");
            return false;
        }
        System.out.println("Deleted: " + current.next.data.getName());
        current.next = current.next.next;   // bypass the deleted node
        count--;
        return true;
    }

    // ---------- SEARCHING ----------

    // Linear search by student number; returns the Student or null
    public Student searchStudent(String studentNo) {
        Node current = head;
        int position = 1;
        while (current != null) {
            if (current.data.getStudentNo().equals(studentNo)) {
                System.out.println("Found at position " + position + ": " + current.data);
                return current.data;
            }
            current = current.next;
            position++;
        }
        System.out.println("Student " + studentNo + " not found.");
        return null;
    }

    // ---------- TRAVERSAL ----------

    public void displayStudents() {
        if (isEmpty()) {
            System.out.println("No student service records.");
            return;
        }
        System.out.println("Student service records (head -> null):");
        Node current = head;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("Total records: " + count);
    }

    // Copy all service times into an array (used by Part A4 statistics)
    public int[] getServiceTimes() {
        int[] times = new int[count];
        Node current = head;
        int i = 0;
        while (current != null) {
            times[i] = current.data.getServiceTime();
            current = current.next;
            i++;
        }
        return times;
    }
}
