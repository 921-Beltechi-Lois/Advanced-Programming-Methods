package Model.programState;

import Model.statement.IStatement;
import Model.utils.MyIDictionary;
import Model.utils.MyIList;
import Model.utils.MyIStack;
import Model.value.Value;
import Exception.ADTException;

import java.util.List;

public class ProgramState {
    private MyIStack<IStatement> exeStack;
    private MyIDictionary<String, Value> symTable;
    private MyIList<Value> out;
    private IStatement originalProgram; // opt

    public ProgramState(MyIStack<IStatement> stack, MyIDictionary<String,Value> symTable, MyIList<Value> out, IStatement program) {

        this.exeStack = stack;
        this.symTable = symTable;
        this.out = out;

        this.originalProgram = program.deepCopy();
        this.exeStack.push(this.originalProgram);
    }

    public void setExeStack(MyIStack<IStatement> newStack) {
        this.exeStack = newStack;
    }

    public void setSymTable(MyIDictionary<String, Value> newSymTable) {
        this.symTable = newSymTable;
    }

    public void setOut(MyIList<Value> newOut) {
        this.out = newOut;
    }

    public MyIStack<IStatement> getExeStack() {
        return exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return symTable;
    }

    public MyIList<Value> getOut() {
        return out;
    }

    @Override
    public String toString() {
        return "Execution stack: \n" + exeStack.getReversed() + "\nSymbol table: \n" + symTable.toString() + "\nOutput list: \n" + out.toString() + "\n";

    }
}