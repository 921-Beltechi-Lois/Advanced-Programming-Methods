package Model.utils;

import java.util.List;
import Exception.InterpreterException;

public interface MyIStack<T> {
    T pop() throws InterpreterException;
    void push(T element);
    T peek();
    boolean isEmpty();
    List<T> getReversed();
}