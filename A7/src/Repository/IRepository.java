package Repository;

import Model.programState.ProgramState;

import java.io.IOException;
import java.util.List;
import Exception.InterpreterException;
public interface IRepository {
    List<ProgramState> getProgramList();

    //setPrgList
    void setProgramStates(List<ProgramState> programStates);//?
//    ProgramState getCurrentPrgState(); REMOVED
    void addProgram(ProgramState program);

    void logPrgStateExec(ProgramState programState) throws IOException, InterpreterException;

    void emptyLogFile() throws IOException;
}



