package Model.statement;
import Model.programState.ProgramState;
import Exception.ADTException;
import Exception.StatementExecutionException;
import Exception.ExpressionEvaluationException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException;
//which is the execution method for a Model.statement.
    IStatement deepCopy();
}
