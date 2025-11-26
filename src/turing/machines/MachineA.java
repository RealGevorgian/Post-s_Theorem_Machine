package turing.machines;

import turing.StepwiseTuringMachine;
import turing.Tape;

public class MachineA implements StepwiseTuringMachine{

    private Tape tape;
    private boolean halted;
    private boolean initialized;

    public MachineA(){
        this.tape = new Tape();
        this.halted = false;
        this.initialized = false;
    }

    @Override
    public void initialize(String w){
        this.tape = new Tape(w);
        this.tape.setHead(0);
        this.halted = false;
        this.initialized = true;
    }

    @Override
    public void step(){
        if(!initialized){
            initialize("");
        }
        if(halted){
            return;
        }

        char current = tape.read();
        tape.write(current);

        halted = true;
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
