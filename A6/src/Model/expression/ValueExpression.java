package Model.expression;

import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;

public class ValueExpression implements IExpression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
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