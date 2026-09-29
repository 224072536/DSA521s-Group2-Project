public class QueueDemo {
    public static void main(String[] args) {
        StudentQueue queue = new StudentQueue();

        System.out.println("=== Six students arrive ===");
        queue.enqueue(new Student("221045678", "Maria", "Registration", 12));
        queue.enqueue(new Student("222034512", "Tomas", "Student Card", 5));
        queue.enqueue(new Student("223041876", "Ndapewa", "Fees", 8));
        queue.enqueue(new Student("221067341", "Simon", "Documents", 4));
        queue.enqueue(new Student("224012345", "Helena", "Academic Enquiry", 7));
        queue.enqueue(new Student("222098765", "Petrus", "Fees", 6));
        queue.displayQueue();

        System.out.println("\nNext to be served (peek): " + queue.peek().getName());

        System.out.println("\n=== Three students are served ===");
        for (int i = 1; i <= 3; i++) {
            Student served = queue.dequeue();
            System.out.println("Served: " + served);
        }

        System.out.println();
        queue.displayQueue();
        System.out.println("\nIs the queue empty? " + queue.isEmpty());
    }
}
