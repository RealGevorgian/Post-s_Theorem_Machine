package turing.machines;

import turing.StepwiseTuringMachine;
import turing.Tape;

public class MachineB implements StepwiseTuringMachine{

    private Tape tape;
    private boolean halted;
    private boolean initialized;
    private boolean moveRightPhase;

    public MachineB(){
        this.tape = new Tape();
        this.halted = false;
        this.initialized = false;
        this.moveRightPhase = true;
    }

    @Override
    public void initialize(String w){
        this.tape = new Tape(w);
        this.tape.setHead(0);
        this.halted = false;
        this.initialized = true;
        this.moveRightPhase = true;
    }

    @Override
    public void step(){
        if(!initialized){
            initialize("");
        }
        if(halted){
            return;
        }

        if(moveRightPhase){
            tape.moveRight();
        } else{
            tape.moveLeft();
        }
        moveRightPhase = !moveRightPhase;
    }

    @Override
    public boolean isHalted(){
        return halted;
    }

    @Override
    public Tape getTape(){
        return tape;
    }
}
