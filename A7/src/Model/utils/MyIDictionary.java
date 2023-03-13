package Model.utils;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import Exception.InterpreterException;
                                //K=T1,V=T2
public interface MyIDictionary<T1,T2> {
    boolean isDefined(T1 key);
    void put(T1 key, T2 value);
    T2 lookUp(T1 key) throws InterpreterException;
    void update(T1 key, T2 value) throws InterpreterException;
    Collection<T2> values();
    void remove(T1 key) throws InterpreterException;
    Set<T1> keySet();
    Map<T1, T2> getContent();

    MyIDictionary<T1, T2> deepCopy() throws InterpreterException;
}