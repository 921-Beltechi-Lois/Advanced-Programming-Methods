package Controller;

import Model.programState.ProgramState;
import Model.value.RefValue;
import Model.value.Value;
import Repository.IRepository;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import Exception.InterpreterException;

class Pair {
    final ProgramState first;
    final InterpreterException second;

    public Pair(ProgramState first, InterpreterException second) {
        this.first = first;
        this.second = second;
    }
}

public class Controller {
    IRepository repository;
    boolean displayFlag = false;

    ExecutorService executorService;



    public void setDisplayFlag(boolean value) {
        this.displayFlag = value;
    }

    public Controller(IRepository repository) {
        this.repository = repository;
    }

    public List<ProgramState> removeCompletedPrg(List<ProgramState> inPrgList) {
        return inPrgList.stream().filter(p -> !p.isNotCompleted()).collect(Collectors.toList());
    }
    public void setProgramStates(List<ProgramState> programStates) {
        this.repository.setProgramStates(programStates);
    }


    public List<ProgramState> getProgramStates() {
        return this.repository.getProgramList();
    }


    //added again
    public void oneStep() throws InterpreterException, InterruptedException {
        executorService = Executors.newFixedThreadPool(2);
        List<ProgramState> programStates = removeCompletedPrg(repository.getProgramList());
        oneStepForAllPrograms(programStates);
        conservativeGarbageCollector(programStates);
        //programStates = removeCompletedPrg(repository.getProgramList());
        executorService.shutdownNow();
        //repository.setProgramStates(programStates);
    }

    //NOW:REMOVED; mutata in PrgState Class
//    public ProgramState oneStep(ProgramState state) throws StatementExecutionException, ADTException, ExpressionEvaluationException{
//        MyIStack<IStatement> stack = state.getExeStack();
//        if (stack.isEmpty())
//            throw new StatementExecutionException("Program state stack is empty.");
//        IStatement currentStatement = stack.pop();
//        state.setExeStack(stack);
//        return currentStatement.execute(state);
//    }

    public void conservativeGarbageCollector(List<ProgramState> programStates) {
        List<Integer> symTableAddresses = Objects.requireNonNull(programStates.stream()
                        .map(p -> getAddrFromSymTable(p.getSymTable().values()))
                        .map(Collection::stream)
                        .reduce(Stream::concat).orElse(null))
                .collect(Collectors.toList());
        programStates.forEach(p -> {
            p.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(symTableAddresses, getAddrFromHeap(p.getHeap().getContent().values()), p.getHeap().getContent()));
        });
    }

    // replaced
    public void allStep() throws InterpreterException, InterruptedException{
        executorService = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<ProgramState> programStates = removeCompletedPrg(repository.getProgramList());
        while (programStates.size() > 0) {
            ///conservativeGarbageCollector(programStates); era aici
            oneStepForAllPrograms(programStates);
            conservativeGarbageCollector(programStates);

            //remove the completed programs
            ///programStates = removeCompletedPrg(repository.getProgramList()); era aici
        }
        executorService.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository
        // update the repository state
        //repository.setProgramStates(programStates); //sters


    }

    public void oneStepForAllPrograms(List<ProgramState> programStates) throws InterpreterException,InterruptedException{
        programStates.forEach(programState -> {
            try {
                repository.logPrgStateExec(programState);
                display(programState);
            } catch (IOException | InterpreterException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        });
        List<Callable<ProgramState>> callList = programStates.stream()
                .map((ProgramState p) -> (Callable<ProgramState>) (p::oneStep))
                .collect(Collectors.toList());

        List<Pair> newProgramList;
        newProgramList = executorService.invokeAll(callList).stream()
                .map(future -> {
                    try {
                        return new Pair(future.get(), null);
                    } catch (ExecutionException | InterruptedException e) {
                        if (e.getCause() instanceof InterpreterException)
                            return new Pair(null, (InterpreterException) e.getCause());
                        System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
                        return null;
                    }
                }).filter(Objects::nonNull)
                .filter(pair -> pair.first != null || pair.second != null)
                .collect(Collectors.toList());

        for (Pair error: newProgramList)
            if (error.second != null)
                throw error.second;
        programStates.addAll(newProgramList.stream().map(pair -> pair.first).collect(Collectors.toList()));

        programStates.forEach(programState -> {
            try {
                repository.logPrgStateExec(programState);
            } catch (IOException | InterpreterException e) {
                System.out.println("\u001B[31m" + e.getMessage() + "\u001B[0m");
            }
        });
        repository.setProgramStates(programStates);
    }

    //REMOVED.... rescrisa mai sus la oneStepForAllPrograms si la allStep()
//    public void allSteps() throws ExpressionEvaluationException, ADTException, StatementExecutionException, IOException
//    {
//        ProgramState program = this.repository.getCurrentPrgState();
//
//        this.repository.logPrgStateExec();
//
//        display(); ///
//
//        while (!program.getExeStack().isEmpty()) {
//            oneStep(program);
//            this.repository.logPrgStateExec();
//
//            program.getHeap().setContent((HashMap<Integer, Value>) safeGarbageCollector(
//                    getAddrFromSymTable(program.getSymTable().getContent().values())  // adresele
//                    ,getAddrFromHeap(program.getHeap().getContent().values()),program.getHeap().getContent()));
//
//            this.repository.logPrgStateExec();
//
//            display(); ///
//        }
//    }
    private void display(ProgramState programState) {
        if (displayFlag) {                     // (), .getCurrentPrgState().toString
            System.out.println(programState.toString());
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
