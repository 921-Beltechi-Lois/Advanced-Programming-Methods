package Model.type;

import Model.value.BoolValue;
import Model.value.Value;

public class BoolType implements Type{
    @Override            //Object
    public boolean equals(Type anotherType) {
        return anotherType instanceof BoolType;
    }

    @Override
    public Value defaultValue() {
        return new BoolValue(false);
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public Type deepCopy() {
        return new BoolType();
    }
}