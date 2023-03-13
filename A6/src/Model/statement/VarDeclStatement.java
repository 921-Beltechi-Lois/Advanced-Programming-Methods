package Model.statement;

import Model.programState.ProgramState;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.value.Value;
import Exception.StatementExecutionException;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public class VarDeclStatement implements IStatement {
    String name;
    Type type;

    public VarDeclStatement(String name, Type type) {
        this.name = name;
        this.type = type;
    }
    public String toString() {
        return String.format("%s %s", type.toString(), name);
    }

    @Override
    public IStatement deepCopy() {
        return new VarDeclStatement(name, type);
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        typeEnv.put(name, type);
        return typeEnv;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException {
        MyIDictionary<String, Value> symTable = state.getSymTable();
        if (symTable.isDefined(name)) {
            throw new StatementExecutionException("Variable " + name + " already exists in the symTable.");
        }
        symTable.put(name, type.defaultValue());
        state.setSymTable(symTable);
        //return state;
        return null;

    }
}
