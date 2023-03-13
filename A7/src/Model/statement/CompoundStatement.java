package Model.statement;

import Model.programState.ProgramState;

import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIStack;
import Exception.InterpreterException;

public class CompoundStatement implements IStatement {
    IStatement firstStatement;
    IStatement secondStatement;

    public CompoundStatement(IStatement firstStatement, IStatement secondStatement) {
        this.firstStatement = firstStatement;
        this.secondStatement = secondStatement;
    }

    public String toString() {
        return "(" + firstStatement.toString() + "|" + secondStatement.toString() + ")";
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException{
        //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
        //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
        //return typEnv2;
        return secondStatement.typeCheck(firstStatement.typeCheck(typeEnv));
    }

    @Override
    public ProgramState execute(ProgramState state) //throws InterpreterException, ExpressionEvaluationException, ADTException
    {
        MyIStack<IStatement> stack = state.getExeStack();
        stack.push(secondStatement);
        stack.push(firstStatement);
        state.setExeStack(stack);   // optional
        //return state;
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new CompoundStatement(firstStatement.deepCopy(), secondStatement.deepCopy());
    }
}
