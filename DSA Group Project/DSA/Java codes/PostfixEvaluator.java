public class PostfixEvaluator {

    // Returns true if the token is one of the four supported operators
    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-")
            || token.equals("*") || token.equals("x") || token.equals("X")
            || token.equals("/");
    }

    // Applies one operator to two operands
    private static double apply(String operator, double left, double right) {
        if (operator.equals("+")) {
            return left + right;
        } else if (operator.equals("-")) {
            return left - right;
        } else if (operator.equals("*") || operator.equals("x") || operator.equals("X")) {
            return left * right;
        } else {
            if (right == 0) {
                System.out.println("Error: division by zero.");
                return 0;
            }
            return left / right;
        }
    }

    // Evaluate a postfix expression whose tokens are separated by spaces
    public static double evaluate(String expression) {
        String[] tokens = expression.trim().split("\\s+");
        DoubleStack stack = new DoubleStack(tokens.length);

        System.out.println("Evaluating postfix expression: " + expression);
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-8s %-34s %s%n", "TOKEN", "ACTION", "STACK (bottom -> top)");
        System.out.println("-------------------------------------------------------------");

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (isOperator(token)) {
                double right = stack.pop();   // second operand popped first
                double left = stack.pop();    // first operand popped second
                double result = apply(token, left, right);
                stack.push(result);
                String action = "pop " + DoubleStack.format(right)
                              + ", pop " + DoubleStack.format(left)
                              + ", push " + DoubleStack.format(left) + " " + token + " "
                              + DoubleStack.format(right) + " = " + DoubleStack.format(result);
                System.out.printf("%-8s %-34s %s%n", token, action, stack.contents());
            } else {
                double value = Double.parseDouble(token);
                stack.push(value);
                System.out.printf("%-8s %-34s %s%n", token,
                        "push " + DoubleStack.format(value), stack.contents());
            }
        }

        System.out.println("-------------------------------------------------------------");
        double answer = stack.pop();
        System.out.println("Final result: " + DoubleStack.format(answer));
        return answer;
    }

    public static void main(String[] args) {
        evaluate("5 3 + 2 *");

        System.out.println();
        evaluate("8 2 / 3 + 4 *");

        System.out.println();
        evaluate("12 4 - 2 * 8 /");

        System.out.println("\n=== Direct demonstration of push, pop and peek ===");
        DoubleStack demo = new DoubleStack(5);
        demo.push(5);
        System.out.println("push(5)  -> " + demo.contents());
        demo.push(3);
        System.out.println("push(3)  -> " + demo.contents());
        System.out.println("peek()   -> " + DoubleStack.format(demo.peek())
                           + "   (stack unchanged: " + demo.contents() + ")");
        System.out.println("pop()    -> " + DoubleStack.format(demo.pop())
                           + "   (stack now: " + demo.contents() + ")");
        System.out.println("isEmpty() -> " + demo.isEmpty());
    }
}
