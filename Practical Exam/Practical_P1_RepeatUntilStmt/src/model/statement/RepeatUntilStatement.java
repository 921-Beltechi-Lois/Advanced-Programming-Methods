package model.statement;

import model.expression.IExpression;
import model.expression.NotExpression;
import model.programState.ProgramState;
import model.type.BoolType;
import model.type.Type;
import exceptions.InterpreterException;
import model.utils.MyIDictionary;
import model.utils.MyIStack;

public class RepeatUntilStatement implements IStatement{
    private final IStatement stmt1;
    private final IExpression exp2;

    public RepeatUntilStatement(IStatement stmt1, IExpression exp2) {
        this.stmt1 = stmt1;
        this.exp2 = exp2;
    }

    //The typecheck method of repeat statement verifies if exp2 has the type bool and also typecheck the statement stmt1
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        Type type_exp2=exp2.typeCheck(typeEnv);
        //Type type_stmt1= stmt1.typeCheck(typeEnv);
        if(type_exp2.equals(new BoolType())){
            stmt1.typeCheck(typeEnv.deepCopy()); //Deep!
            return typeEnv;
        }
        else{
            throw new InterpreterException("Expression in the repeat statement must be of Bool type!");
        }
    }

    /*
    stmt1;
	(while(!exp2) stmt1)
     */
    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException {
        MyIStack<IStatement> exeStack=state.getExeStack();
        //exp2.eval();--> faci NotExpression sa ii dai evaluare la exp2
        //IStatement converted= new WhileStatement(new NotExpression(exp2),stmt1);
        IStatement converted= new CompoundStatement(stmt1,new WhileStatement(new NotExpression(exp2),stmt1));
        exeStack.push(converted);
        state.setExeStack(exeStack);
        return null;
    }

    @Override
    public IStatement deepCopy() {
        return new RepeatUntilStatement(stmt1.deepCopy(), exp2.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("repeat %s until %s",stmt1, exp2);
    }
}
