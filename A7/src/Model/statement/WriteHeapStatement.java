package Model.statement;

import Model.expression.IExpression;
import Model.programState.ProgramState;
import Model.type.RefType;
import Model.type.Type;
import Model.utils.MyIDictionary;
import Model.utils.MyIHeap;
import Model.value.RefValue;
import Model.value.Value;
import Exception.InterpreterException;

public class WriteHeapStatement implements IStatement{
    private final String varName;
    private final IExpression expression;

    public WriteHeapStatement(String varName, IExpression expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws InterpreterException {
        if (typeEnv.lookUp(varName).equals(new RefType(expression.typeCheck(typeEnv))))
            return typeEnv;
        else
            throw new InterpreterException("WriteHeap: right hand side and left hand side have different types.");
    }

    @Override
    public ProgramState execute(ProgramState state) throws InterpreterException{
        MyIDictionary<String, Value> symTable = state.getSymTable();
        MyIHeap heap = state.getHeap();
        if (!symTable.isDefined(varName))
            throw new InterpreterException(String.format("%s not present in the symTable", varName));
        Value value = symTable.lookUp(varName);
        if (!(value instanceof RefValue))
            throw new InterpreterException(String.format("%s not of RefType", value));
        RefValue refValue = (RefValue) value;
        Value evaluated = expression.eval(symTable, heap);
        if (!evaluated.getType().equals(refValue.getLocationType()))
            throw new InterpreterException(String.format("%s not of %s", evaluated, refValue.getLocationType()));
        heap.update(refValue.getAddress(), evaluated);
        state.setHeap(heap);
        //return state;
        return null;

    }

    @Override
    public IStatement deepCopy() {
        return new WriteHeapStatement(varName, expression.deepCopy());
    }

    @Override
    public String toString() {
        return String.format("WriteHeap(%s, %s)", varName, expression);
    }
}
