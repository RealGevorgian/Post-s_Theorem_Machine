package turing;

public interface StepwiseTuringMachine{

    void initialize(String w);

    void step();

    boolean isHalted();

    Tape getTape();
}
