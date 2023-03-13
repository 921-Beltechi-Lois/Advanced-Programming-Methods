package Model.utils;

import java.util.Collection;
import java.util.Set;

import Exception.ADTException;
public interface MyIDictionary<T1,T2> {
    boolean isDefined(T1 key);
    void put(T1 key, T2 value);
    T2 lookUp(T1 key) throws ADTException;
    void update(T1 key, T2 value) throws ADTException;
    Collection<T2> values();
    void remove(T1 key) throws ADTException;
    Set<T1> keySet();
}