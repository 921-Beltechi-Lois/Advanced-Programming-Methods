package Model.expression;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public interface IExpression {

    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws ExpressionEvaluationException, ADTException;
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap) throws ExpressionEvaluationException, ADTException;

    IExpression deepCopy();


}
