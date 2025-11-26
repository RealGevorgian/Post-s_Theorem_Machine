package turing;

import turing.machines.MachineA;
import turing.machines.MachineB;

import java.util.Scanner;

public class M{

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input word w (over {0,1}) => ");
        String w = scanner.nextLine().trim();
        if (w.isEmpty()) {
            w = String.valueOf(Tape.BLANK);
        }

        T1Formatter t1 = new T1Formatter();
        Tape formattedTape = t1.formatInput(w);
        System.out.println("After T1 (formatted tape) => " + formattedTape);

        MachineA A = new MachineA();
        MachineB B = new MachineB();

        T2Simulator t2 = new T2Simulator();
        int result = t2.runOnFormattedTape(formattedTape, A, B);

        System.out.println("Result of T2 (turing.M(w)):");
        System.out.println("  0 → A(w) halted first (w ∈ L)");
        System.out.println("  1 → B(w) halted first (w ∈ complement(L))");
        System.out.println("turing.M(w) = " + result);

        T3EraserAndRestorer t3 = new T3EraserAndRestorer();
        t3.eraseAndRestore(formattedTape);
        System.out.println("After T3 (tape erased, first symbol restored) => " + formattedTape);
    }
}
