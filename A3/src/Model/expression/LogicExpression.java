package Model.expression;

import java.util.Objects;

import Model.type.BoolType;
import Model.utils.MyIDictionary;
import Model.value.BoolValue;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public class LogicExpression implements IExpression {
    IExpression expression1;
    IExpression expression2;
    String operation;

    public LogicExpression(String operation, IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operation = operation;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable);
        if (value1.getType().equals(new BoolType())) {
            value2 = this.expression2.eval(symTable);
            if (value2.getType().equals(new BoolType())) {
                BoolValue bool1 = (BoolValue) value1;
                BoolValue bool2 = (BoolValue) value2;
                boolean b1, b2;
                b1 = bool1.getValue();
                b2 = bool2.getValue();
                if (Objects.equals(this.operation, "and")) { // this.operation.equals...
                    return new BoolValue(b1 && b2);
                } else if (Objects.equals(this.operation, "or")) {
                    return new BoolValue(b1 || b2);
                }
            } else {
                throw new ExpressionEvaluationException("Second operand is not a boolean.");
            }
        } else {
            throw new ADTException("First operand is not a boolean.");
        }
        return null;
    }
    @Override
    public IExpression deepCopy() {
        return new LogicExpression(operation, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public String toString() {
        return expression1.toString() + " " + operation + " " + expression2.toString();
    }
}
