package Model.expression;
import Model.utils.MyIDictionary;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public interface IExpression {
    Value eval(MyIDictionary<String,Value> tbl) throws ExpressionEvaluationException, ADTException;

    IExpression deepCopy();
}
