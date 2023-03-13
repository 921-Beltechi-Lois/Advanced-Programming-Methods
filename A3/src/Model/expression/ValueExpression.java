package Model.expression;

import Model.utils.MyIDictionary;
import Model.value.Value;

public class ValueExpression implements IExpression {
    Value value;

    public ValueExpression(Value value) {
        this.value = value;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable) {
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