package Model.expression;

import Model.utils.MyIDictionary;
import Model.value.Value;
import Exception.ADTException;
public class VariableExpression implements IExpression {
    String key; //id, var

    public VariableExpression(String key) {
        this.key = key;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable) throws ADTException {
        return symTable.lookUp(key);        // key= given_var
    }

    @Override
    public IExpression deepCopy() {
        return new VariableExpression(key);
    }

    @Override
    public String toString() {
        return key;
    }
}
