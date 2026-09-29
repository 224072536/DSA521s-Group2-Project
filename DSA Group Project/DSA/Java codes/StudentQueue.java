public class StudentQueue {

    // One "box" in the queue: holds a student and points to the next box
    private class Node {
        Student data;
        Node next;

        Node(Student data) {
            this.data = data;
            this.next = null;
        }
    }

    private Node front;   // student who will be served next
    private Node rear;    // student who arrived most recently
    private int size;

    public StudentQueue() {
        front = null;
        rear = null;
        size = 0;
    }

    // Add a student at the REAR of the queue
    public void enqueue(Student student) {
        Node newNode = new Node(student);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
        size++;
    }

    // Remove and return the student at the FRONT of the queue
    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty - no student to serve.");
            return null;
        }
        Student served = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        size--;
        return served;
    }

    // Look at the front student without removing them
    public Student peek() {
        if (isEmpty()) {
            return null;
        }
        return front.data;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public int size() {
        return size;
    }

    // Walk from front to rear and print every student
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Waiting queue is empty.");
            return;
        }
        System.out.println("Waiting queue (front -> rear):");
        Node current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current.data);
            current = current.next;
            position++;
        }
        System.out.println("Total waiting: " + size);
    }
}
