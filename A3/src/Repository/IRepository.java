package Repository;

import Model.programState.ProgramState;

import java.io.IOException;
import java.util.List;
import Exception.ADTException;
public interface IRepository {
    List<ProgramState> getProgramList();
    void setProgramStates(List<ProgramState> programStates);//?
    ProgramState getCurrentPrgState();
    void addProgram(ProgramState program);


    void logPrgStateExec() throws IOException, ADTException;
    //emtpylogfile?
    void emptyLogFile() throws IOException;
}



