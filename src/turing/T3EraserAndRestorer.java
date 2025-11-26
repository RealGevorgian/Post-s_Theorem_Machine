package turing;

public class T3EraserAndRestorer{

    public void eraseAndRestore(Tape tape) {
        char originalFirstSymbol = tape.getCell(1);

        tape.clearAndWriteSingle(originalFirstSymbol);
    }
}
