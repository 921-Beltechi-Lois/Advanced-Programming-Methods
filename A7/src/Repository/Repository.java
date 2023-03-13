package Repository;

import Model.programState.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import Exception.InterpreterException;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private int currentPosition;

    private final String logFilePath;

    public Repository(ProgramState programState, String logFilePath){
        this.programStates = new ArrayList<>();
        this.currentPosition = 0;
        this.logFilePath=logFilePath;
        this.addProgram(programState);
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public List<ProgramState> getProgramList() {
        return this.programStates;
    }

    @Override
    public void setProgramStates(List<ProgramState> programStates) {
        this.programStates = programStates;
    }

//    @Override
//    public ProgramState getCurrentPrgState() {
//        return this.programStates.get(this.currentPosition);
//    }

    @Override
    public void addProgram(ProgramState program) {
        this.programStates.add(program);
    }

    @Override
    public void logPrgStateExec(ProgramState programState) throws IOException, InterpreterException {
        PrintWriter logFile; // try{... fara close} catch ioexception
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));
        logFile.println(programState.programStateToString());
        logFile.close();
    }

    @Override
    public void emptyLogFile() throws IOException {
        PrintWriter logFile;
        logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, false)));
        logFile.close();
    }
}