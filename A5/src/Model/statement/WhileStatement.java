package Model.statement;

import Model.expression.IExpression;
import Model.programState.ProgramState;
import Model.type.BoolType;
import Model.utils.MyIStack;
import Model.value.BoolValue;
import Model.value.Value;
import Exception.ExpressionEvaluationException;
import Exception.StatementExecutionException;
import Exception.ADTException;

public class WhileStatement implements IStatement{
    private final IExpression expression;
    private final IStatement statement;

    public WhileStatement(IExpression expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value value = expression.eval(state.getSymTable(), state.getHeap());
        MyIStack<IStatement> stack = state.getExeStack();
        if (!value.getType().equals(new BoolType()))
            throw new StatementExecutionException(String.format("%s is not of BoolType", value));
        BoolValue boolValue = (BoolValue) value;
        if (boolValue.getValue()) {
            stack.push(this);
            stack.push(statement);
        }
        //return state;
        return null;

    }

    @Override
    public IStatement deepCopy() {
        return new WhileStatement(expression.deepCopy(), statement.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("while(%s){%s}", expression, statement);
    }
}
