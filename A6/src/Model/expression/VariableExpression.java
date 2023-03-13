package Model.expression;

import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.Value;
import Exception.ADTException;
import Exception.ExpressionEvaluationException;
public class VariableExpression implements IExpression {
    String key; //id, var

    public VariableExpression(String key) {
        this.key = key;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        return typeEnv.lookUp(key);
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ADTException {
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
