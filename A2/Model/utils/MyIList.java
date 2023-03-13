package Model.utils;

import java.util.List;
import Exception.ADTException;
public interface MyIList<T> {
    void add(T elem);
    T pop() throws ADTException;
    boolean isEmpty();
    List<T> getList();
}
