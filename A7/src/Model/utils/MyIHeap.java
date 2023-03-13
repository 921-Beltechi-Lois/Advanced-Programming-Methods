package Model.utils;

import Model.value.Value;

import java.util.HashMap;
import java.util.Set;
import Exception.InterpreterException;

public interface MyIHeap{
    int getFreeValue();
    HashMap<Integer, Value> getContent();
    void setContent(HashMap<Integer, Value> newMap);
    int add(Value value);
    void update(Integer position, Value value) throws InterpreterException;
    Value get(Integer position) throws InterpreterException;
    boolean containsKey(Integer position);
    void remove(Integer key) throws InterpreterException;
    Set<Integer> keySet();
}