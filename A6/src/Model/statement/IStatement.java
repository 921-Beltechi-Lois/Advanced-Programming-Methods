package Model.statement;
import Model.programState.ProgramState;
import Exception.ADTException;
import Exception.StatementExecutionException;
import Exception.ExpressionEvaluationException;
import Model.type.Type;
import Model.utils.MyIDictionary;

public interface IStatement {

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException;

    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException;
//which is the execution method for a Model.statement.
    IStatement deepCopy();
}
