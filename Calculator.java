
public class Calculator {
    Item[] expr;
    int ip;
    Stack stack;
    int size = 1024;

    public Calculator(Item[] expr) {
        this.expr = expr;
        this.ip = 0;
        this.stack = new Stack(size);
    }

    public int runStatic() {
        while (ip < expr.length) {
            step();
        }
        return stack.pop();
    }

    public void step() {
        Item next = expr[ip++];
        switch (next.getType()) {
            case ADD -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x + y);
            }
            case SUB -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x - y);
            }
            case DIV -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x / y);
            }
            case MUL -> {
                int y = stack.pop();
                int x = stack.pop();
                stack.push(x * y);
            }
            case VALUE -> {
                stack.push(next.getValue());
            }
            default -> throw new IllegalArgumentException("wack value: " + next.getType());
        }
    }
}
