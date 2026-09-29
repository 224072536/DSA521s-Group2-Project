public class DoubleStack {

    private double[] items;   // the storage for the stack
    private int top;          // index of the top item; -1 means empty

    public DoubleStack(int capacity) {
        items = new double[capacity];
        top = -1;
    }

    // Put a value on TOP of the stack
    public void push(double value) {
        if (top == items.length - 1) {
            System.out.println("Stack overflow - cannot push " + value);
            return;
        }
        top++;
        items[top] = value;
    }

    // Remove and return the value on TOP of the stack
    public double pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow - cannot pop.");
            return 0;
        }
        double value = items[top];
        top--;
        return value;
    }

    // Look at the top value without removing it
    public double peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty - nothing to peek.");
            return 0;
        }
        return items[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public int size() {
        return top + 1;
    }

    // Show the stack contents from bottom to top, e.g. [ 5.0 3.0 ] <- top
    public String contents() {
        if (isEmpty()) {
            return "[ empty ]";
        }
        String result = "[ ";
        for (int i = 0; i <= top; i++) {
            result = result + format(items[i]) + " ";
        }
        return result + "] <- top";
    }

    // Print whole numbers without a trailing .0
    public static String format(double value) {
        if (value == (long) value) {
            return String.valueOf((long) value);
        }
        return String.valueOf(value);
    }
}
