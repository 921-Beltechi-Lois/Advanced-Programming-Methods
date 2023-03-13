package Repository;

import Model.programState.ProgramState;

import java.util.ArrayList;
import java.util.List;

public class Repository implements IRepository{
    private List<ProgramState> programStates;
    private int currentPosition;

    public Repository(ProgramState programState){
        this.programStates = new ArrayList<>();
        this.currentPosition = 0;
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

    @Override
    public ProgramState getCurrentPrgState() {
        return this.programStates.get(this.currentPosition);
    }

    @Override
    public void addProgram(ProgramState program) {
        this.programStates.add(program);
    }
}