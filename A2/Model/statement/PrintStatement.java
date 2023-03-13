package Model.statement;

import Model.expression.IExpression;
import Model.programState.ProgramState;
import Model.utils.MyIList;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public class PrintStatement implements IStatement {
    IExpression expression;

    public PrintStatement(IExpression expression) {
        this.expression = expression;
    }

    public String toString(){ return "print(" +expression.toString()+")";}
    @Override
    public ProgramState execute(ProgramState state) throws ExpressionEvaluationException, ADTException {
        MyIList<Value> out = state.getOut();
        out.add(expression.eval(state.getSymTable()));
        state.setOut(out);
        return state;
    }

    @Override
    public IStatement deepCopy() {
        return new PrintStatement(expression.deepCopy());
    }
}
