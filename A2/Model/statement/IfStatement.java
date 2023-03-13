package Model.statement;

import Model.expression.IExpression;
import Model.programState.ProgramState;
import Model.utils.MyIStack;
import Model.value.BoolValue;
import Model.value.Value;
import Exception.StatementExecutionException;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;
public class IfStatement implements IStatement {
    IExpression expression;
    IStatement thenStatement;
    IStatement elseStatement;

    public IfStatement(IExpression expression, IStatement thenStatement, IStatement elseStatement) {
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public String toString(){ return "(IF("+ expression.toString()+") THEN(" +thenStatement.toString() +")ELSE("+elseStatement.toString()+"))";}

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        Value result = this.expression.eval(state.getSymTable());
        if (result instanceof BoolValue boolResult) {
            IStatement statement;
            if (boolResult.getValue()) {
                statement = thenStatement;
            } else {
                statement = elseStatement;
            }
            MyIStack<IStatement> stack = state.getExeStack();
            stack.push(statement);
            state.setExeStack(stack);
            return state;
        } else {
            throw new StatementExecutionException("Please provide a boolean expression in an if statement.");
        }
    }
    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }
}