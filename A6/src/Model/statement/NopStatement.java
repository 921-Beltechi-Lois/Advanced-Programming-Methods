package Model.statement;

import Model.programState.ProgramState;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Exception.StatementExecutionException;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public class NopStatement implements IStatement {
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        return typeEnv;
    }

    @Override
    public ProgramState execute(ProgramState state) {
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new NopStatement();
    }

    public String toString() {
        return "NopStatement";
    }
}
