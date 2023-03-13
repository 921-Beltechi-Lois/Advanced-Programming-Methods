package Model.statement;

import Model.expression.IExpression;

import java.io.BufferedReader;
import java.io.IOException;

import Exception.InterpreterException;
import Model.programState.ProgramState;
import Model.type.StringType;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.value.StringValue;
import Model.value.Value;

public class CloseReadFile implements IStatement{
    private final IExpression expression;

    public CloseReadFile(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        if (expression.typeCheck(typeEnv).equals(new StringType()))
            return typeEnv;
        else
            throw new InterpreterException("CloseReadFile requires a string expression.");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException{
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType()))
            throw new InterpreterException(String.format("%s does not evaluate to StringValue", expression));
        StringValue fileName = (StringValue) value;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue()))
            throw new InterpreterException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.lookUp(fileName.getValue());
        try {
            br.close();
        } catch (IOException e) {
            throw new InterpreterException(String.format("Unexpected error in closing %s", value));
        }
        fileTable.remove(fileName.getValue());
        state.setFileTable(fileTable);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CloseReadFile(expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("CloseReadFile(%s)", expression.toString());
    }
}
