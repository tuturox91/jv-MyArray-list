package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private static final float RESIZE_MULTIPLIER = 1.5f;
    private Object[] elements = new Object[10];
    private int size = 0;

    @Override
    public void add(T value) {
        if (size == elements.length) {
            resize();
        }
        elements[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bound of list size");
        }
        if (size == elements.length) {
            resize();
        }
        System.arraycopy(elements, index, elements, index + 1, size - index);
        elements[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            if (size == elements.length) {
                resize();
            }
            elements[size] = list.get(i);
            size++;
        }
    }

    @Override
    public T get(int index) {
        indexInRange(index);
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return (T) elements[index];
    }

    @Override
    public void set(T value, int index) {
        indexInRange(index);
        elements[index] = value;
    }

    @Override
    public T remove(int index) {
        indexInRange(index);
        T tempValue = (T) elements[index];
        System.arraycopy(elements, index + 1, elements, index, elements.length - index - 1);
        size--;
        return tempValue;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (elements[i] == element || elements[i] != null && elements[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("Element not exists");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void resize() {
        Object[] newElements = new Object[Math.round(elements.length * RESIZE_MULTIPLIER)];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }

    private void indexInRange(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("Index out of bounds");
        }
    }
}
