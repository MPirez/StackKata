public class BoundedStack implements Stack {
    private int capacity;
    private int elements[];
    private int size;

    public static Stack Make(int capacity) {
        if (capacity < 0)
            throw new Stack.IllegalCapacity();
        if (capacity == 0)
            return new ZeroCapacityStack();
        return new BoundedStack(capacity);
    }
    
    private BoundedStack(int capacity) {
        this.capacity = capacity;
        elements = new int[capacity];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void push(int element) {
        if (size == capacity)
            throw new Stack.Overflow();
        this.elements[size++] = element;
    }

    public int pop() {
        if (isEmpty())
            throw new Stack.Underflow();
        return elements[--size];
    }

    public int top() {
        if (isEmpty())
            throw new Stack.Empty();
        return elements[size - 1];
    }

    public Integer find(int element) {
        for (int i = size - 1; i >= 0; i--)
            if (elements[i] == element)
                return (size - 1) - i;
        return null;
    }

    private static class ZeroCapacityStack implements Stack {
        public boolean isEmpty() {
            return true;
        }

        public int getSize() {
            return 0;
        }

        public void push(int element) {
            throw new Stack.Overflow();
        }

        public int pop() {throw new Stack.Underflow();}

        public int top() {
            throw new Stack.Empty();
        }

        public Integer find(int element) {
            return null;
        }
    }
}
