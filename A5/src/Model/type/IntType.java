package Model.type;

import Model.value.IntValue;
import Model.value.Value;

public class IntType implements Type{
    @Override             // Object@@@
    public boolean equals(Type anotherType) {
        return anotherType instanceof IntType;
    }

    @Override
    public Value defaultValue() {
        return new IntValue(0);
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public Type deepCopy() {
        return new IntType();
    }

}


//    boolean equals(Object another){
//        if (another instanceof IntType)
//            return true;
//        else
//            return false;
//    }