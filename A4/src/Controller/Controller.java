package Controller;

import Model.programState.ProgramState;
import Model.statement.IStatement;
import Model.utils.MyIStack;
import Model.value.RefValue;
import Model.value.Value;
import Repository.IRepository;
import Exception.ExpressionEvaluationException;
import Exception.StatementExecutionException;
import Exception.ADTException;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

        display(); ///

        while (!program.getExeStack().isEmpty()) {
            oneStep(program);
            this.repository.logPrgStateExec();

            program.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(
                    getAddrFromSymTable(program.getSymTable().getContent().values())  // adresele
                    ,getAddrFromHeap(program.getHeap().getContent().values()),program.getHeap().getContent()));

            this.repository.logPrgStateExec();

            display(); ///
        }
    }
    private void display() {
        if (displayFlag) {
            System.out.println(this.repository.getCurrentPrgState().toString());
        }
    }

    public Map<Integer, Value> safeGarbageCollector(List<Integer> symTableAddr, List<Integer> heapAddr, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> ( symTableAddr.contains(e.getKey()) || heapAddr.contains(e.getKey())))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public List<Integer> getAddrFromSymTable(Collection<Value> symTableValues) {
        return symTableValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }


    public List<Integer> getAddrFromHeap(Collection<Value> heapValues) {
        return heapValues.stream()
                .filter(v -> v instanceof RefValue)
                .map(v -> {RefValue v1 = (RefValue) v; return v1.getAddress();})
                .collect(Collectors.toList());
    }


     /*Map<Integer, Value> safeGarbageCollector(List<Integer> addressesFromSymbolTable, Map<Integer,Value> heap)
    {
        return heap.entrySet()
                .stream()
                .filter(e->addressesFromSymbolTable.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
     */

    //List<Integer> getAddrFromSymTable(Collection<Value> symTableValues, Collection<Value> heap){
    //    return  Stream.concat(
     //                   heap.stream()
      //                          .filter(v-> v instanceof RefValue)
      //                          .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();}),
      //                  symTableValues.stream()
       //                         .filter(v-> v instanceof RefValue)
       //                         .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
       //         )
       //         .collect(Collectors.toList());
        /*return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddress();})
                .collect(Collectors.toList());*/
    //}

}
