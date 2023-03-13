package Model.statement;

import Model.programState.ProgramState;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Exception.InterpreterException;

public class NopStatement implements IStatement {
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
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
