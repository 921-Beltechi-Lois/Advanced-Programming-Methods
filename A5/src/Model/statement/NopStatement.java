package Model.statement;

import Model.programState.ProgramState;

public class NopStatement implements IStatement {
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
