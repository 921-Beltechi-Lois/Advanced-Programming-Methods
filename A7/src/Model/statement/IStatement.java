package Model.statement;
import Model.programState.ProgramState;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Exception.InterpreterException;
public interface IStatement {

    MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException;

    ProgramState execute(ProgramState state) throws InterpreterException;
//which is the execution method for a Model.statement.
    IStatement deepCopy();
}
