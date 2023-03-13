package Model.statement;

import Model.expression.IExpression;
import Model.programState.ProgramState;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIList;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
import Exception.StatementExecutionException;
public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    public String toString(){ return "print(" +expression.toString()+")";}

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, ADTException {
        MyIList<Value> out = state.getOut();
        out.add(expression.eval(state.getSymTable(), state.getHeap()));
        state.setOut(out);
        //return state;
        return null;

    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }
}
