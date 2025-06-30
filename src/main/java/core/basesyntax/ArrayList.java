package core.basesyntax;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    public static final int DEFAULT_CAPACITY = 10;
    public static final double GROW_FACTOR = 1.5;
    private int size = 0;

    Object[] data;

    public ArrayList() {
        data =  new Object[DEFAULT_CAPACITY];

    }

    @Override
    public void add(T value) {
        checkSize();
        data[size] = value;
        size++;
    }

    private void checkSize() {
        if(size == data.length-1) {
            data = grow();
        }
    }


    private Object[] grow() {
        int new_size = (int) (data.length * GROW_FACTOR);
        return data = Arrays.copyOf(data, new_size);
    }

    @Override
    public void add(T value, int index) {
        size++;
        checkCapacity(index);
        checkSize();
        System.arraycopy(data,  index, data, index+1, size-index);
        data[index] = value;
    }

    private void checkCapacity(int index) {
        if(index<0 || index >= size) {
            throw new ArrayListIndexOutOfBoundsException("");
        }
    }


    @Override
    public void addAll(List<T> list) {
        for (int i =0; i< list.size(); i++) {
            add(list.get(i));
        }
    }

    @Override
    public T get(int index) {
        checkCapacity(index);
        return (T) data[index];
    }

    @Override
    public void set(T value, int index) {
        checkCapacity(index);
        data[index] = value;
    }

    @Override
    public T remove(int index) {
        checkCapacity(index);
        Object oldItem = data[index];
        removeItem(index);
        return (T) oldItem;
    }

    private void removeItem(int index) {
        size--;
        System.arraycopy(data, index + 1, data, index, data.length-index- 1);
    }

    @Override
    public T remove(T element) {
        Object oldItem = null;
        for(int i = 0; i<=size; i++) {
            if(element != null) {
                if(data[i]!=null && data[i].equals(element)) {
                    oldItem = data[i];
                    removeItem(i);
                    return (T) oldItem;

                }
            } else {
                size--;
                return (T) oldItem;
            }
        }
        throw new NoSuchElementException("Incorrect value input");
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0 ? true : false;
    }
}
