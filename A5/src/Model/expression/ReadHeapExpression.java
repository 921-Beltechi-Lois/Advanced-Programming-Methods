package Model.expression;

import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.RefValue;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public class ReadHeapExpression implements IExpression{
    private final IExpression expression;

    public ReadHeapExpression(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public Value eval(MyIDictionary<String, Value> symTable, MyIHeap heap) throws ExpressionEvaluationException, ADTException {
        Value value = expression.eval(symTable, heap);
        if (!(value instanceof RefValue))
            throw new ExpressionEvaluationException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        return heap.get(refValue.getAddress());
    }

    @Override
    public IExpression deepCopy() {
        return new ReadHeapExpression(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("ReadHeap(%s)", expression);
    }
}
