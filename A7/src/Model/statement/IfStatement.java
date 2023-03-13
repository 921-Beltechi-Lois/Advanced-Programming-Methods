package Model.statement;

import Model.expression.IExpression;
import Model.programState.ProgramState;
import Model.type.BoolType;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIStack;
import Model.value.BoolValue;
import Model.value.Value;
import Exception.InterpreterException;

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
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type typeExpr = expression.typeCheck(typeEnv);
        if (typeExpr.equals(new BoolType())) {
            thenStatement.typeCheck(typeEnv.deepCopy()); //clone
            elseStatement.typeCheck(typeEnv.deepCopy());
            return typeEnv;
        } else
            throw new InterpreterException("The condition of IF does not have the type Bool.");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        Value result = this.expression.eval(state.getSymTable(), state.getHeap());
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
            //return state;
            return null;

        } else {
            throw new InterpreterException("Please provide a boolean expression in an if statement.");
        }
    }
    @Override
    public IStatement deepCopy() {
        return new IfStatement(expression.deepCopy(), thenStatement.deepCopy(), elseStatement.deepCopy());
    }
}