package model.expression;
import exceptions.InterpreterException;
import model.type.Type;
import model.utils.MyIDictionary;
import model.utils.MyIHeap;
import model.value.BoolValue;
import model.value.Value;

public class NotExpression implements IExpression{
    private final IExpression expression;

    public NotExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        return expression.typeCheck(typeEnv);
    }

    @Override                                   //table
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap heap) throws InterpreterException {
        BoolValue value = (BoolValue) expression.eval(tbl, heap);
        if (!value.getValue()) // daca valoarea E FALSA -> RET TRUE
            return new BoolValue(true);
        else
            return new BoolValue(false);

    }

    @Override
    public IExpression deepCopy() {
        return new NotExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("!(%s)", expression);
    }
}
