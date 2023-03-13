package Model.statement;

import Model.expression.IExpression;

import java.io.BufferedReader;
import java.io.IOException;
import Exception.ADTException;
import Exception.ExpressionEvaluationException;
import Exception.StatementExecutionException;
import Model.programState.ProgramState;
import Model.type.StringType;
import Model.utils.MyIDictionary;
import Model.value.StringValue;
import Model.value.Value;

public class CloseReadFile implements IStatement{
    private final IExpression expression;

    public CloseReadFile(IExpression expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(new StringType()))
            throw new StatementExecutionException(String.format("%s does not evaluate to StringValue", expression));
        StringValue fileName = (StringValue) value;
        MyIDictionary<String, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(fileName.getValue()))
            throw new StatementExecutionException(String.format("%s is not present in the FileTable", value));
        BufferedReader br = fileTable.lookUp(fileName.getValue());
        try {
            br.close();
        } catch (IOException e) {
            throw new StatementExecutionException(String.format("Unexpected error in closing %s", value));
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
