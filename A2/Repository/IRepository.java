package Repository;

import Model.programState.ProgramState;

import java.util.List;

public interface IRepository {
    List<ProgramState> getProgramList();
    void setProgramStates(List<ProgramState> programStates);
    ProgramState getCurrentPrgState();
    void addProgram(ProgramState program);
}



