package Model.statement;

import Model.programState.ProgramState;
import Exception.InterpreterException;
import Model.type.Type;
import Model.utils.MyDictionary;
import Model.utils.MyIDictionary;
import Model.utils.MyIStack;
import Model.utils.MyStack;
import Model.value.Value;

import java.util.Map;

public class ForkStatement implements IStatement{
    private final IStatement statement;

    public ForkStatement(IStatement statement) {
        this.statement = statement;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        statement.typeCheck(typeEnv.deepCopy());
        return typeEnv;
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> newStack = new MyStack<>();
        newStack.push(statement);
        MyIDictionary<String, Value> newSymTable = new MyDictionary<>();
        for (Map.Entry<String, Value> entry: state.getSymTable().getContent().entrySet()) {
            newSymTable.put(entry.getKey(), entry.getValue().deepCopy());
        }

        return new ProgramState(newStack, newSymTable, state.getOut(), state.getFileTable(), state.getHeap());
    }



    @Override
    public IStatement deepCopy() {
        return new ForkStatement(statement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("Fork(%s)", statement.toString());
    }
}
