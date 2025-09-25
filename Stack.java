package oop.object;

import java.util.Arrays;

public class Stack implements IStack {
    private Object[] dataArray;
    private int size;
    private int capacity;
    private static final int DEFAULT_CAPACITY = 16;
    private static final int MAX_STACK_SIZE = Integer.MAX_VALUE - 1;

    public Stack() {
        dataArray = new Object[DEFAULT_CAPACITY];
        capacity = dataArray.length;
        size = 0;
    }

    public void push(Object value) {
        if (size >= capacity) {
            boolean resizeResult = upResize();
            if (!resizeResult) {
                throw new RuntimeException("Cannot add element");
            }
        }
        dataArray[size] = value;
        size += 1;
    }

    public Object pop() {
        if (size == 0) {
            return null;
        }

        size -= 1;
        Object element = dataArray[size];
        dataArray[size] = null;
        return element;
    }

    public Object peek() {
        if (size == 0) {
            return null;
        }
        return dataArray[size - 1];
    }

    public int size() {
        return size;
    }

    public boolean upResize() {
        if (capacity >= MAX_STACK_SIZE) {
            return false;
        }
        long newCapacityL = (capacity * 3L) / 2L + 1L;
        int newCapacity = (newCapacityL < MAX_STACK_SIZE) ? (int) newCapacityL : MAX_STACK_SIZE;
        dataArray = Arrays.copyOf(dataArray, newCapacity);
        capacity = newCapacity;
        return true;
    }

    public void trimToSize() {
        dataArray = Arrays.copyOf(dataArray, size);
        capacity = dataArray.length;
    }

    public void clear() {
        dataArray = new Object[DEFAULT_CAPACITY];
        capacity = dataArray.length;
        size = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i < size - 1) {
                result.append(dataArray[i]).append(", ");
            } else {
                result.append(dataArray[i]);
            }
        }
        return result.append("]").toString();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Stack stack)) return false;

        return Arrays.equals(dataArray, stack.dataArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(dataArray);
    }
}
