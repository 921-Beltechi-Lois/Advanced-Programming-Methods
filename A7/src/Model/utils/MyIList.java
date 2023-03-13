package Model.utils;

import java.util.List;
import Exception.InterpreterException;
public interface MyIList<T> {
    void add(T elem);
    T pop() throws InterpreterException;
    boolean isEmpty();
    List<T> getList();
}
