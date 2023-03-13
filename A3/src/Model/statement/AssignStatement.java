package Model.statement;

import Model.expression.IExpression;
import Model.programState.ProgramState;

import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIStack;
import Model.value.Value;
import Exception.StatementExecutionException;
import Exception.ExpressionEvaluationException;
import Exception.ADTException;

public class AssignStatement implements IStatement {
    private final String key; //id
    private final IExpression expression;

    public AssignStatement(String key, IExpression expression) {
        this.key = key;
        this.expression = expression;
    }

    public String toString(){ return key+"="+ expression.toString();}

    @Override
    public ProgramState execute(ProgramState state) throws StatementExecutionException, ExpressionEvaluationException, ADTException {
        MyIStack<IStatement> stk=state.getExeStack(); // el

        MyIDictionary<String, Value> symbolTable = state.getSymTable();

        if (symbolTable.isDefined(key)) {
            Value value = expression.eval(symbolTable);

            Type typeId = (symbolTable.lookUp(key)).getType();
            if (value.getType().equals(typeId)) {   //Type Value din SymbolTabel == Type key curente
                symbolTable.update(key, value);     // Exista deja variabila, update
            } else {
                throw new StatementExecutionException("Declared type of variable " + key + " and type of the assigned expression do not match.");
            }
        } else {
            throw new StatementExecutionException("The used variable " + key + " was not declared before.");
        }
        state.setSymTable(symbolTable);
        return state;
    }


    @Override
    public IStatement deepCopy() {
        return new AssignStatement(key, expression.deepCopy());
    }
}
