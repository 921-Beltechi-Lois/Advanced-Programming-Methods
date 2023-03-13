package Model.expression;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.Value;
import Exception.InterpreterException;
public interface IExpression {

    Type typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException;
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap heap) throws InterpreterException;

    IExpression deepCopy();


}
