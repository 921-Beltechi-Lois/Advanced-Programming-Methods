package Model.expression;

import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.Value;
import Exception.InterpreterException;

public class ValueExpression implements IExpression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        return value.getType();
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) {
        return this.value;
    }

    @Override
    public IExpression deepCopy() {
        return new ValueExpression(value);
    }


    @Override
    public String toString() {
        return this.value.toString();
    }
}