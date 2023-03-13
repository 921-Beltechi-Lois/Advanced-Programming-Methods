package Model.expression;

import Model.type.BoolType;
import Model.type.IntType;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.BoolValue;
import Model.value.IntValue;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
import java.util.Objects;

public class RelationalExpression implements IExpression{
    IExpression expression1;
    IExpression expression2;
    String operator;

    public RelationalExpression(String operator, IExpression expression1, IExpression expression2) {
        this.expression1 = expression1;
        this.expression2 = expression2;
        this.operator = operator;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException {
        Type type1, type2;
        type1 = expression1.typeCheck(typeEnv);
        type2 = expression2.typeCheck(typeEnv);
        if (type1.equals(new IntType())) {
            if (type2.equals(new IntType())) {
                return new BoolType();         //!! t/f for relational exp: >,=..
            } else
                throw new ExpressionEvaluationException("Second operand is not an integer.");
        } else
            throw new ExpressionEvaluationException("First operand is not an integer.");
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value1, value2;
        value1 = this.expression1.eval(symTable,heap);
        if (value1.getType().equals(new IntType())) {
            value2 = this.expression2.eval(symTable,heap);
            if (value2.getType().equals(new IntType())) {
                IntValue val1 = (IntValue) value1;
                IntValue val2 = (IntValue) value2;
                int v1, v2;
                v1 = val1.getValue();
                v2 = val2.getValue();
//                if (Objects.equals(this.operator, "<"))
                  if (this.operator.equals("<"))
                    return new BoolValue(v1 < v2);
//                else if (Objects.equals(this.operator, "<="))
                  else if (this.operator.equals("<="))
                    return new BoolValue(v1 <= v2);
//                else if (Objects.equals(this.operator, "=="))
                  else if (this.operator.equals("=="))
                      return new BoolValue(v1 == v2);
//                else if (Objects.equals(this.operator, "!="))
                  else if (this.operator.equals("!="))
                      return new BoolValue(v1 != v2);
//                else if (Objects.equals(this.operator, ">"))
                  else if (this.operator.equals(">"))
                      return new BoolValue(v1 > v2);
//                else if (Objects.equals(this.operator, ">="))
                  else if (this.operator.equals(">="))
                      return new BoolValue(v1 >= v2);
            } else
                throw new ExpressionEvaluationException("Second operand is not an integer.");
        } else
            throw new ExpressionEvaluationException("First operand in not an integer.");
        return null;
    }

    @Override
    public IExpression deepCopy() {
        return new RelationalExpression(operator, expression1.deepCopy(), expression2.deepCopy());
    }

    @Override
    public String toString() {
        return this.expression1.toString() + " " + this.operator + " " + this.expression2.toString();
    }
}
