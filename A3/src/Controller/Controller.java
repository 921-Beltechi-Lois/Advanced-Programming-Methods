package Controller;

import Model.programState.ProgramState;
import Model.statement.IStatement;
import Model.utils.MyIStack;
import Repository.IRepository;
import Exception.ExpressionEvaluationException;
import Exception.StatementExecutionException;
import Exception.ADTException;

import java.io.IOException;

public class Controller {
    IRepository repository;
    boolean displayFlag = false;

    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public ProgramState oneStep(ProgramState state) throws StatementExecutionException, ADTException, ExpressionEvaluationException{
        MyIStack<IStatement> stack = state.getExeStack();
        if (stack.isEmpty())
            throw new StatementExecutionException("Program state stack is empty.");
        IStatement currentStatement = stack.pop();
        state.setExeStack(stack);
        return currentStatement.execute(state);
    }

    public void allSteps() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException
    {
        ProgramState program = this.repository.getCurrentPrgState();

        this.repository.logPrgStateExec();

        display();

        while (!program.getExeStack().isEmpty()) {
            oneStep(program);
            this.repository.logPrgStateExec();
            display();
        }
    }

    private void display() {
        if (displayFlag) {
            System.out.println(this.repository.getCurrentPrgState().toString());
        }
    }
}
