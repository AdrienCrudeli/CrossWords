import java.util.Iterator;
import java.util.NoSuchElementException;

public class DynamicArray<MyType> implements Iterable<MyType> {
    private int capacity;
    private int lastIndex;
    private MyType[] array;

    public DynamicArray() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public DynamicArray(int capacity) {
        this.capacity = Math.max(capacity, 1);
        array = (MyType[]) new Object[this.capacity];
    }

    public void append(MyType value) {
        add(value);
    }

    @SuppressWarnings("unchecked")
    public void add(MyType value) {
        if (lastIndex >= capacity) {
            MyType[] newArray = (MyType[]) new Object[capacity * 2];
            for (int i = 0; i < lastIndex; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            capacity *= 2;
        }
        array[lastIndex++] = value;
    }

    public MyType get(int index) {
        if (index < 0 || index >= lastIndex) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + lastIndex);
        }
        return array[index];
    }

    public int size() {
        return lastIndex;
    }

    public boolean isEmpty() {
        return lastIndex == 0;
    }

    @Override
    public Iterator<MyType> iterator() {
        return new Iterator<MyType>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return current < lastIndex;
            }

            @Override
            public MyType next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[current++];
            }
        };
    }
}
