package Model.utils;

import java.util.List;
import Exception.ADTException;

public interface MyIStack<T> {
    T pop() throws ADTException;
    void push(T element);
    T peek();
    boolean isEmpty();
    List<T> getReversed();
}